package cn.stians.vblog.config.wsConfig;

import cn.stians.vblog.entity.ChatMsg;
import cn.stians.vblog.utils.JSONChange;

import cn.stians.vblog.utils.RespBean;
import com.fasterxml.jackson.core.JsonProcessingException;


import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;


public class ChatMsgEncoder implements Encoder.Text<ChatMsg>{

    @Override
    public String encode(ChatMsg chatMsg) {
        try {
            return JSONChange.objToJson(chatMsg);
        } catch (JsonProcessingException e) {
            System.out.println("json转化失败~");
        }
        return null;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
