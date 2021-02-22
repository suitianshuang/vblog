package cn.stians.vblogadmin.model;

import java.util.List;

public class RespPageBean {
    private List<?> data;
    private Integer total;

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
