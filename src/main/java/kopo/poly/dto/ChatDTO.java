package kopo.poly.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ChatDTO {
    private String name; // 이름

    private String msg; // 메세지

    private String date; // 발송 날짜

}
