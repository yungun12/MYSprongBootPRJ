package kopo.poly.controller;

import kopo.poly.dto.OcrDTO;
import kopo.poly.service.IOcrService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.DateUtil;
import kopo.poly.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequestMapping(value = "/ocr")
@RequiredArgsConstructor
@Controller
public class OcrController {

    private final IOcrService ocrService;

    // 업로드되는 파일이 저장되는 기본 폴더 설정(자바에서 경로는 /로 표현함)
    final private String FILE_UPLOAD_SAVE_PATH = "c:/upload"; // C:\\upload 폴더에 저장

    /**
     * 이미지 인식을 위한 파일업로드 화면 호출
     */
    @GetMapping(value = "uploadImage")
    public String uploadImage() {
        log.info("{}.uploadImage!", this.getClass().getName());

        return "ocr/uploadImage";
    }

    /**
     * 파일업로드 및 이미지 인식
     */
    @PostMapping(value = "readImage")
    public String readImage(ModelMap model, @RequestParam(value = "fileUpload") MultipartFile mf)
            throws Exception {

        log.info("{}.readImage Start!", this.getClass().getName());

        // OCR 실행 결과
        String res;

        // 업로드하는 실제 파일명
        String originalFileName = mf.getOriginalFilename();

        // 파일 확장자 가져오기
        String ext = Objects.requireNonNull(originalFileName).substring(originalFileName.lastIndexOf(".") + 1).toLowerCase();

        // 이미지 파일만 실행되도록 함
        if (ext.equals("jpeg") || ext.equals("jpg") || ext.equals("gif") || ext.equals("png")) {

            // 저장될 파일명 생성
            String saveFileName = DateUtil.getDateTime("HHmmss") + "." + ext;

            // 파일 저장 경로 생성
            String saveFilePath = FileUtil.mkdirForDate(FILE_UPLOAD_SAVE_PATH);

            // 전체 파일 경로
            String fullFileInfo = saveFilePath + "/" + saveFileName;

            // 파일을 서버에 저장
            mf.transferTo(new File(fullFileInfo));

            // OCR 처리 및 DB 저장용 DTO 생성
            OcrDTO pDTO = new OcrDTO();
            pDTO.setFileName(saveFileName);
            pDTO.setFilePath(saveFilePath);
            pDTO.setExt(ext);
            pDTO.setOrgFileName(originalFileName);
            pDTO.setRegId("admin");

            // Tesseract를 통해 텍스트 추출
            OcrDTO rDTO = Optional.ofNullable(ocrService.getReadforImageText(pDTO)).orElseGet(OcrDTO::new);

            // 추출된 텍스트를 DTO에 저장
            String textFromImage = CmmUtil.nvl(rDTO.getTextFromImage());
            pDTO.setTextFromImage(textFromImage);

            // DB 저장
            int insertResult = ocrService.insertOcrInfo(pDTO);
            if (insertResult > 0) {
                log.info("OCR 데이터 DB 저장 성공");
            } else {
                log.error("OCR 데이터 DB 저장 실패");
            }

            res = textFromImage; // 인식 결과를 반환
            rDTO = null;
            pDTO = null;

        } else {
            res = "이미지 파일이 아니라서 인식이 불가능합니다.";
        }

        // 이미지로부터 인식된 문자를 JSP에 전달
        model.addAttribute("res", res);

        log.info("{}.readImage End!", this.getClass().getName());

        return "ocr/readImage";
    }
}
