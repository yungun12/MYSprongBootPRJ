package kopo.poly.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDTO {

    private String collectTime; // 수집시간
    private String seq; // 수집된 데이터 순번
    private String movieRank; // 영화 순위
    private String movieNm; // 제목
    private String movieReserve; // 얘매율
    private String score; // 평점
    private String openDay; // 개봉일
    private String regId; // 데이터 최초 등록자 ID
    private String regDt; // 데이터 최초 등록 일시
    private String chgId; // 데이터 수정자 ID
    private String chgDt; // 데이터 최종 수정 일시

}
