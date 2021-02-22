package cn.stians.vblogadmin.controller.chat;


import cn.stians.vblogadmin.model.Admins;
import cn.stians.vblogadmin.model.admin.ChatMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class WsController {
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
    @MessageMapping("/ws/chat")
    public void handleMsg(Authentication authentication, ChatMsg chatMsg) {
        Admins admin= (Admins) authentication.getPrincipal();
        chatMsg.setFromNickname(admin.getUsername());
        chatMsg.setDate(new Date());
        chatMsg.setFrom(admin.getUsername());
        simpMessagingTemplate.convertAndSendToUser(chatMsg.getTo(), "/queue/chat", chatMsg);
    }
}
