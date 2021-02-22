package cn.stians.vblog.entity;

public class ChatMsg {
//    to_id:1,      // 接收人
//    from_id:12,	  // 发送人
//    from_username:"某某",  // 发送人昵称
//    from_userpic:"http://pic136.nipic.com/file/20170725/10673188_152559977000_2.jpg",
//    type:"text",	 // 发送类型
//    data:"你好啊",	 // 发送内容
//    time:151235451   // 接收到的时间
    private Integer id;
    private Integer toId;
    private String  toName;
    private Integer fromId;
    private String  fromUsername;
    private String fromUserpic;
    private String type;
    private String data;
    private Long time;

    @Override
    public String toString() {
        return "ChatMsg{" +
                "id=" + id +
                ", toId=" + toId +
                ", toName='" + toName + '\'' +
                ", fromId=" + fromId +
                ", fromUsername='" + fromUsername + '\'' +
                ", fromUserpic='" + fromUserpic + '\'' +
                ", type='" + type + '\'' +
                ", data='" + data + '\'' +
                ", time=" + time +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public String getFromUsername() {
        return fromUsername;
    }

    public void setFromUsername(String fromUsername) {
        this.fromUsername = fromUsername;
    }

    public String getFromUserpic() {
        return fromUserpic;
    }

    public void setFromUserpic(String fromUserpic) {
        this.fromUserpic = fromUserpic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
