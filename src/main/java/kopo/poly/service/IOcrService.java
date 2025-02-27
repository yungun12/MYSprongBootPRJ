package kopo.poly.service;

import kopo.poly.dto.OcrDTO;

public interface IOcrService {

    // 이미지 파일로부터 문자 읽어 오기
    OcrDTO getReadforImageText(OcrDTO pDTO) throws Exception;

    // OCR 결과를 데이터베이스에 저장
    int insertOcrInfo(OcrDTO pDTO) throws Exception;
}
