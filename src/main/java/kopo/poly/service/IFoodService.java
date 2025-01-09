package kopo.poly.service;

import kopo.poly.dto.FoodDTO;

import java.util.List;

public interface IFoodService {

    // 한국 폴리텍 대학 강서캠퍼스 학식 정보 가져오기
    List<FoodDTO> toDayFood() throws Exception;

}
