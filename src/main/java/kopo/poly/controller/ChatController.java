package kopo.poly.controller;

import kopo.poly.chat.ChatHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Slf4j
@Controller
@RequestMapping(value = "/chat")
public class ChatController {

    /**
     * 채팅창 입장 전
     */
    @GetMapping(value = "intro")
    public String intro() {

        log.info(this.getClass().getName() + "/intro Start!");

        log.info(this.getClass().getName() + "/intro End!");

        return "/chat/intro";
    }

    /**
     * 채팅창 접속
     */
    @PostMapping(value = "chatroom")
    public String chatroom() {

        log.info(this.getClass().getName() + "room Start!");

        log.info(this.getClass().getName() + "room End!");

        return "/chat/chatroom";
    }

    /**
     * 채팅창 목록
     */
    @RequestMapping(value = "roomList")
    @ResponseBody
    public Set<String> roomList() {

        log.info(this.getClass().getName() + "roomList Start!");

        log.info(this.getClass().getName() + "roomList End!");

        return ChatHandler.roomInfo.keySet();
    }
}
