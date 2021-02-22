package cn.stians.vblog.config.wsConfig;

import cn.stians.vblog.entity.ChatMsg;
import cn.stians.vblog.model.Support;
import cn.stians.vblog.model.Users;
import cn.stians.vblog.service.ChatService;
import cn.stians.vblog.utils.CurrentTimeUtil;
import cn.stians.vblog.utils.RespBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.LinkedHashSet;

/**
 * webSocket 服务器
 */
//@ServerEndpoint(value = "/socketServer/{userName}")
@ServerEndpoint(value = "/ws/{userName}/{userId}",
//                ,decoders ={ChatMsgDecoder.class},
        encoders = {ChatMsgEncoder.class}
//                configurator = ChatMsgServerConfigurator.class
)
@Component
public class SocketServer {

    @Autowired
    ChatService chatService;
    private static final Logger logger = LoggerFactory.getLogger(SocketServer.class);


    private static LinkedHashSet<Users> onlineUsers = new LinkedHashSet<>();

    private Session session;

//    private ServerEndpointConfig endpointConfig;

    @OnOpen
    public void open(Session session, @PathParam(value = "userName") String userName, @PathParam(value = "userId") Integer userId) {
        this.session = session;
//       加入新的用户进存储到集合中，这些用户就是在线用户，将来用来判断用户是否在线
//        如果在线就直接将消息发送给他，不在线就将将消息存到数据库中，
// 等用户上线第一时间到数据库里根据用户名来查找自己的未读消息，然后删除这条数据
        onlineUsers.add(new Users(userId,userName, session));
        logger.info("客户端:【{}】连接成功", userName);
    }

    /**
     * 因为使用的是post请求发送暂时不需要这个方法
     */
    @OnMessage
    public void onMessage(String username) {

//        Users users = socketServers.stream().filter( cli -> cli.getSession() == session)
//                .collect(Collectors.toList()).get(0);
//        sendMessage(client.getUserName()+"<--"+message,SYS_USERNAME);
//
//        logger.info("客户端:【{}】发送信息:{}",client.getUserName(),message);
    }

    /**
     * 连接关闭触发，通过sessionId来移除
     * socketServers中客户端连接信息
     */
    @OnClose
    public void onClose() {

        onlineUsers.forEach(users -> {
//            如果在线用户里的session id 对应的上现在这个用户的session id 就将其从在线用户中移除
            if (users.getSession().getId().equals(this.session.getId())) {
                logger.info("客户端:【{}】断开连接", users.getUsername());
                onlineUsers.remove(users);
            }
        });
    }
    /**
     * 发生错误时触发
     *
     * @param error
     */
    @OnError
    public void onError(Throwable error) {
        onlineUsers.forEach(users -> {
            if (users.getSession().getId().equals(this.session.getId())) {
                onlineUsers.remove(users);
                logger.error("客户端"+users.getUsername()+"发生异常"  );
                try {
                    this.session.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                error.printStackTrace();
            }
        });
    }

    /**
     * 信息发送的方法，通过客户端的userName
     * 拿到其对应的session，调用信息推送的方法
     */
//    要发的信息 和要发的用户
    public synchronized RespBean sendMessage(ChatMsg chatMsg) throws IOException, EncodeException {
        String fromUsername = chatMsg.getFromUsername();
        String toName = chatMsg.getToName();
        String message = chatMsg.getData();
        chatMsg.setTime(CurrentTimeUtil.getCurrentTime());
        for (Users users : onlineUsers) {
            System.out.println(">>>>>>>>>.遍历在线用户" + users.getUsername());
//            要发送的用户在线，直接发送
            if (toName.equals(users.getUsername())) {
                    users.getSession().getBasicRemote().sendObject(chatMsg);
                logger.info(fromUsername + "给" + users.getUsername() + "发了一句" + message);
                return RespBean.ok("消息已发送");
            }
        }
        //     要发送的用户不在，将用chatMsg的信息放入数据库中
            if (chatService.saveNoReadMessage(chatMsg)==1){
                return RespBean.ok("用户不在线，聊天记录存入数据库");
            }
            return RespBean.error("ws服务器出错");
    }


    /**
     * 获取服务端当前客户端的连接数量，
     * 因为服务端本身也作为客户端接受信息，
     * 所以连接总数还要减去服务端
     * 本身的一个连接数
     * <p>
     * 这里运用三元运算符是因为客户端第一次在加载的时候
     * 客户端本身也没有进行连接，-1 就会出现总数为-1的情况，
     * 这里主要就是为了避免出现连接数为-1的情况
     *
     * @return
     */
    public synchronized static int getOnlineNum() {
        return onlineUsers.size();
    }


    /**
     * 信息群发，我们要排除服务端自己不接收到推送信息
     * 所以我们在发送的时候将服务端排除掉
     *
     * @param message
     */
    public synchronized static void sendAll(String message, String username) {
        //群发，不能发给自己
        onlineUsers.stream().filter(users -> users.getUsername() != username)
                .forEach(users -> {
                    try {
                        users.getSession().getBasicRemote().sendText(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        logger.info(username + "对所有人说：" + message);
    }


}
