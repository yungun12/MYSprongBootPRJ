package kopo.poly.controller;

import kopo.poly.dto.FoodDTO;
import kopo.poly.service.IFoodService;
import kopo.poly.service.impl.FoodService;
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
@RequiredArgsConstructor
@RequestMapping(value = "/food")
@Controller
public class FoodController {

    private final IFoodService foodService; // FoodService 객체 주입

    /**
     * 서울 강서 캐퍼스 식단 수집을 위한 URL 호출
     */
    @GetMapping(value = "toDayFood")
    public String collectFood(ModelMap model) throws Exception {

        log.info("{}.collectFood Start!", this.getClass().getName());

        // FoodService.toDayFood() 결과를 Null 값체크하여 rList 객체에 저장하기
        List<FoodDTO> rList = Optional.ofNullable(foodService.toDayFood()).orElseGet(ArrayList::new);

        // 크롤링 결과를 널어주기
        model.addAttribute("rList", rList);

        log.info("{}.collectFood End!", this.getClass().getName());

        return "/food/todayFood";
    }
}
