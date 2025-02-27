package kopo.poly.controller;

import kopo.poly.dto.MovieDTO;
import kopo.poly.service.IMovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequestMapping(value = "/movie")
@RequiredArgsConstructor
@Controller
public class MovieController {

    private final IMovieService movieService; // 영화 서비스 객체 주입하기

    /**
     * CGV 영화 수집을 위한 URL 호출
     */
    @GetMapping(value = "collectMovieRank")
    public String collectMovieRank(ModelMap model)
            throws Exception {

        log.info("{}.collectMovieRank Start!", this.getClass().getName());

        int res = movieService.collectMovieRank();

        // 크롤링 결과를 넣어주기
        model.addAttribute("msg", "CGV 홈페이지로부터 수집된 영화는 총 " + res + "건입니다.");

        log.info("{}.collectMovieRank End!", this.getClass().getName());

        return "/movie/collectMovieRank";
    }
    @GetMapping(value = "getMovieRank")
    public String getMovieRank(ModelMap model)
        throws Exception {

        log.info("{}.getMovieRank Start!", this.getClass().getName());

        // 수집된 영화 정보 조회하기
        List<MovieDTO> rList = Optional.ofNullable(movieService.getMovieInfo()).orElseGet(ArrayList::new);

        // 조회 결과를 JSP에 전달하기
        model.addAttribute("rList", rList);

        log.info("{}.getMovieRank End!", this.getClass().getName());

        return "/movie/movieList";
    }
}
