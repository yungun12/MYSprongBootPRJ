package kopo.poly.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class UserInfoDTO {

    // 회원 정보를 저장하는 DTO 클래스
    private String userId;   // 사용자 ID (고유 식별자)
    private String userName; // 사용자 이름
    private String password; // 사용자 비밀번호 (보안 처리 필요)
    private String email;    // 사용자 이메일 주소
    private String addr1;    // 사용자 기본 주소
    private String addr2;    // 사용자 상세 주소
    private String regId;    // 최초 등록자 ID
    private String regDt;    // 최초 등록 일시
    private String chgId;    // 마지막 수정자 ID
    private String chgDt;    // 마지막 수정 일시

    // 회원 가입시 중복가입을 방지하기 위해 사용할 변수
    // DB를 조회해서 회원이 존재하면 Y값을 반환함
    // DB테이블에 존재하지 않는 가상의 컬럼
    private String existsYn;

    // 이메일 중복체크를 위한 인증번호
    private int authNumber;

}
