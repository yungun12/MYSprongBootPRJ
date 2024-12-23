package kopo.poly.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MailDTO {

    String toMail; // 받는 사람

    String title; // 보내는 메일 제목

    String contents; // 보내는 메일 내용

    String seq; // 메일 발송 ID 또는 고유 식별자

    String fromMail; // 발신자 이메일 주소

    String sendDt; // 발송 시간

}
