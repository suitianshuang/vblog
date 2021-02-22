package cn.stians.vblog.controller.wsController;
import cn.stians.vblog.config.loginConfig.UserLoginToken;
import cn.stians.vblog.config.wsConfig.SocketServer;
import cn.stians.vblog.entity.ChatMsg;
import cn.stians.vblog.service.ChatService;
import cn.stians.vblog.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.EncodeException;
import java.io.IOException;
import java.util.List;

@RestController
public class WebSocketController {

    @Autowired
    SocketServer socketServer;
    @Autowired
    ChatService chatService;

    @UserLoginToken
    @PostMapping("/user/logout")
    public RespBean logout(){
        return RespBean.ok("退出登录成功");
    }

    @UserLoginToken
    @GetMapping("/chat/getNoReadMessage")
    public RespBean getUnReadMessage(int userId){
        System.out.println(userId+"获取未读消息");
        List<ChatMsg> chatMsg = chatService.findUnReadMessage(userId);
        if (chatMsg!=null){
            return RespBean.ok("有未读消息",chatMsg);
        }
        return RespBean.ok("没有未读消息");
    }

    @PostMapping("/chat/send")
    public void sendMessage(@RequestBody ChatMsg chatMsg){
//        调用socket服务里的发送信息方法，根据传递进来的chatMsg里面的username进行发送
//        判断在不在线，在线就直接发送，不在线就先存到数据库中，每个用户上线时，
//        都会去数据库差询一次有没有未读消息
        System.out.println(chatMsg.toString());
        try {
            socketServer.sendMessage(chatMsg);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EncodeException e) {
            e.printStackTrace();
        }
    }

}
