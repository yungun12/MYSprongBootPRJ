package kopo.poly.mapper;

import kopo.poly.dto.OcrDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IOcrMapper {

    // 이미지로부터 인식된 택스트 내용 DB에 등록
    int insertOcrInfo(OcrDTO pDTO) throws Exception;
}