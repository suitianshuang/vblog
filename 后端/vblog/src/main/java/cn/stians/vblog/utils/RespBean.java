package cn.stians.vblog.utils;

public class RespBean {
    private Integer status;
    private String msg;
    private Object obj;
    private String errorMsg;

    public RespBean(Integer status, String msg, Object obj, String errorMsg) {
        this.status = status;
        this.msg = msg;
        this.obj = obj;
        this.errorMsg = errorMsg;
    }

    public static RespBean build() {
        return new RespBean();
    }

    public static RespBean ok(String msg) {
        return new RespBean(200, msg, null,null);
    }

    public static RespBean ok(String msg, Object obj) {
        return new RespBean(200, msg, obj,null);
    }

    public static RespBean error(String errorMsg) {
        return new RespBean(500, null, null,errorMsg);
    }

    public static RespBean error(String errorMsg, Object obj) {
        return new RespBean(500, null, obj,errorMsg);
    }

//    public static RespBean ok(String msg,)
    private RespBean() {
    }



    public Integer getStatus() {
        return status;
    }

    public RespBean setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public RespBean setMsg(String msg) {
        this.msg = msg;
        return this;
    }
    public String getErrorMsg() {
        return errorMsg;
    }

    public RespBean setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }
    public Object getObj() {
        return obj;
    }

    public RespBean setObj(Object obj) {
        this.obj = obj;
        return this;
    }
}
