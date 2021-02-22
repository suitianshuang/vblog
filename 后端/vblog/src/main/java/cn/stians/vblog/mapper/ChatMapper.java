package cn.stians.vblog.mapper;

import cn.stians.vblog.entity.ChatMsg;

import java.util.List;

public interface ChatMapper {

    //    添加未读消息
    int saveNoReadMessage(ChatMsg chatMsg);

    List<ChatMsg> findUnReadMessage(int userId);

    void deleteUnReadMessage(int userId);
}
