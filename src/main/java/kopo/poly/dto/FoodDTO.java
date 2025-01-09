package kopo.poly.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodDTO {
    // 학식 날짜(월요일부터 ~ 금요일까지)
    private String day;
    // 학식 메뉴 이름
    private String food_nm;
    // 데이터 최초 등록자 ID
    private String reg_id;
    // 데이터 최초 등록 일시
    private String reg_dt;
    // 데이터 수정자 ID
    private String chg_id;
    // 데이터 최종 수정 일시
    private String chg_dt;

}
