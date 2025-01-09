package kopo.poly.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OcrDTO {

    // 이미지가 저장된 전체 파일 경로
    private String filePath;
    // 업로드된 이미지 파일의 이름
    private String fileName;
    // 업로드된 이미지에서 추출된 텍스트
    private String textFromImage;
    // 업로드된 이미지의 원본 파일 이름 (변경 전 파일 이름)
    private String orgFileName;
    // 파일 확장자 (예: jpg, png 등)
    private String ext;
    // 파일을 등록한 사용자의 ID
    private String regId;
    // 파일 최초 등록 일시
    private String regDT;
    // 파일을 수정한 사용자의 ID
    private String chdId;
    // 파일 최종 수정 일시
    private String chdDT;

}
