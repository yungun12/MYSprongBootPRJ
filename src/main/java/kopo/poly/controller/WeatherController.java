package kopo.poly.controller;

import jakarta.servlet.http.HttpServletRequest;
import kopo.poly.dto.WeatherDTO;
import kopo.poly.service.IWeatherService;
import kopo.poly.util.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping(value = "/weather")
@RequiredArgsConstructor
@RestController
public class WeatherController {

    private final IWeatherService weatherService;

    @GetMapping(value = "getWeather")
    public WeatherDTO getWeather(HttpServletRequest request) throws Exception {

        // 로그 찍기
        log.info(this.getClass().getName(), ".getWeather Start!");

        String lat = CmmUtil.nvl(request.getParameter("lat")); // 자바스크립트로부터 받은 위도, 경도 값
        String lon = CmmUtil.nvl(request.getParameter("lon")); // 자바스크립트로부터 받은 위도, 경도 값

        WeatherDTO pDTO = new WeatherDTO();
        pDTO.setLat(lat);
        pDTO.setLon(lon);

        // 내가 필요한 정보만 추출한 날씨 정보 가져오기
        WeatherDTO rDTO = weatherService.getWeather(pDTO);

        if (rDTO == null) {
            rDTO = new WeatherDTO();

        }

        // 로그 찍기
        log.info(this.getClass().getName(), "getWeather End!");

        return rDTO;
    }
}
