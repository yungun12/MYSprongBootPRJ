package kopo.poly.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherDailyDTO {

    private String day; // 기준 일자
    private String sunrise; // 일출 시간
    private String sunset; // 일몰 시간
    private String moonrise; // 월출 시간
    private String moonset; // 월몰 시간
    private String dayTemp; // 평균 기온
    private String dayTempMax; // 최고 기온
    private String dayTempMin; // 최저기온
}
