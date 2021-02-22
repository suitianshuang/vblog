package cn.stians.vblog.service;

import cn.stians.vblog.entity.ChatMsg;
import cn.stians.vblog.mapper.ChatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ChatService {

    @Autowired
    ChatMapper chatMapper;
    public  int saveNoReadMessage(ChatMsg chatMsg){
        return chatMapper.saveNoReadMessage(chatMsg);
    }
    public List<ChatMsg> findUnReadMessage(int userId){
        List<ChatMsg> unReadMessage = chatMapper.findUnReadMessage(userId);
        if (unReadMessage!=null){
//            将未读信息删除
            chatMapper.deleteUnReadMessage(userId);
            return unReadMessage;
        }
        return null;
    }
}
