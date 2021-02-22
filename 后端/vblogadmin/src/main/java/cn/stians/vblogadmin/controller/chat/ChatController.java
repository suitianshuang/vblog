package cn.stians.vblogadmin.controller.chat;

import cn.stians.vblogadmin.model.Admins;
import cn.stians.vblogadmin.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    ChatService chatService;
    @GetMapping("/admin")
    public List<Admins> getAllAdminsExceptCurrentAdmin(){
        return chatService.getAllAdminsExceptCurrentAdmin();
    }

}
