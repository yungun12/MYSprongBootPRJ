package kopo.poly.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kopo.poly.dto.MsgDTO;
import kopo.poly.dto.NoticeDTO;
import kopo.poly.service.INoticeService;
import kopo.poly.util.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequestMapping(value = "/notice")
@RequiredArgsConstructor
@Controller
public class NoticeController {

    // @RequiredArgsConstructor 를 통해 메모리에 올라간 서비스 객체를 Controller에서 사용할 수 있게 주입한다.
    private final INoticeService noticeService;

    /**
     * 게시판 리스트 보여주기
     * <p>
     * GetMapping(value = "notice/noticeList") => GET 방식을 통해 접속되는 URL이 notice/noticeList 경우 아래 함수를 실행한다.
     */
    @GetMapping(value = "noticeList")
    public String noticeList(HttpSession session, ModelMap model)
        throws Exception {

        // 로그 찍기(추 후 찍은 로그를 통해 함수에 접근했는지 파악이 쉬움)
        log.info(this.getClass().getName() + ".noticeList Start!");

        //
        //
        //
        session.setAttribute("SESSION+USER_ID", "USER01");

        //
        //
        List<NoticeDTO> rList = Optional.ofNullable(noticeService.getNoticeList())
                .orElseGet(ArrayList::new);





        // 조회된 리스트 결과값 넣어주기
        model.addAttribute("rList", rList);

        // 로그 찍기
        log.info(this.getClass().getName() + ".noticeList End!");

        // 함수처리가 끝나고 보여줄 JSP 파일 이름
        // webapp/WEB-INF/views/notice/noticeList.jsp
        return "notice/noticeList";

    }

    /**
     * 게시판 작성 페이지 이동
     * <p>
     * 이 함수는 게시판 작성 페이지로 접근하기 위해 만듬
     * <p>
     * GetMapping(value = "notice/noticeReg") => GET방식을 통해 접속되는 URL이 notice/noticeReg 경우 아래 함수를 실행함
     */
    @GetMapping(value = "noticeReg")
    public String NoticeReg() {

        log.info(this.getClass().getName() + ".noticeReg Start!");

        log.info(this.getClass().getName() + ".noticeReg End!");

        // 함수 처리가 끝나고 보여줄 JSP 파일명
        // webapp/WEB-INF/views/notice/noticeReg.jsp
        return "notice/noticeReg";
    }

    /**
     * 게시판 글 등록
     * <p>
     *     게시글 등록은 Ajax로 호출되기 때문에 결과는 JSON 구조로 전달해야만 함
     *     JSON 구조로 결과 메세지를 전송하기 때문에 @ResponseBody 어노테이션 추가함
     */

    @ResponseBody
    @PostMapping(value = "noticeList")
    public MsgDTO noticeInsert(HttpServletRequest request, HttpSession session) {

        log.info(this.getClass().getName() + ".noticeInsert Start!");

        String msg = ""; // 메세지 내용
        MsgDTO dto; // 결과 메세지 구조

        try {
            //
            //
            String userId = CmmUtil.nvl((String) session.getAttribute("SESSION_USER_ID"));
            String title = CmmUtil.nvl(request.getParameter("title")); // 제목
            String noticeYn = CmmUtil.nvl(request.getParameter("noticeYn")); // 공지글 여부
            String contents = CmmUtil.nvl(request.getParameter("contents")); //내용

            log.info("session user_id : {} / title : {} / noticeYn : {} / contents : {}",
                    userId, title, noticeYn, contents);

            // 데이터 저장하기 위해 DTO에 저장하기
            NoticeDTO pDTO = new NoticeDTO();
            pDTO.setUserId(userId);
            pDTO.setTitle(title);
            pDTO.setNoticeYn(noticeYn);
            pDTO.setContents(contents);

            /*
            게시글 등록하기 위한 비즈니스 로직을 호출
             */
            noticeService.insertNoticeInfo(pDTO);

            // 저장이 완료되면 사용자에게 보여줄 메세지
            msg = "등록되었습니다.";

        } catch (Exception e) {

            // 저장이 실패하면 사용자에게 보여줄 메세지
            msg = "실패하였습니다. : " + e.getMessage();
            log.info(e.toString());

        } finally {
            // 결과 메세지 전달하기
            dto = new MsgDTO();
            dto.setMsg(msg);

            log.info(this.getClass().getName() + ".noticeInsert End!");
        }

        return dto;
    }

    /**
     * 게시판 상세보기
     */
    @GetMapping(value = "noticeInfo")
    public String noticeInfo(HttpServletRequest request, ModelMap model) {

        log.info(this.getClass().getName() + ".noticeInfo Start!");

        String nSeq = CmmUtil.nvl(request.getParameter("nSeq")); // 공지글 번호(PK)

        /*
        ########################################################
        반드시 값을 받았으면 꼭 로그를 찍어 서 값이 제대로 들어오는지 확인하기
        ########################################################
         */
        log.info("nSeq : {}", nSeq);

        /*
        값 전달은 반드시 DTO 객체를 이용해서 처리함 전달 받은 값을 DTO 객체에 넣는다.
         */
        NoticeDTO pDTO = new NoticeDTO();
        pDTO.setNoticeSeq(nSeq);

        // 공지사항 상세정보 가져오기
        // JAVA8 부타 제공되는 Optional 활용하여 NPE 처리
        NoticeDTO rDTO = Optional.ofNullable(noticeService.getNoticeInfo(pDTO, true))
                .orElseGet(NoticeDTO::new);

        // 조회된 리스트 결과 값 보여주기
        model.addAttribute("rDTO", rDTO);

        log.info(this.getClass().getName() + ".noticeInfo End!");

        return "notice/noticeInfo";
    }
}
