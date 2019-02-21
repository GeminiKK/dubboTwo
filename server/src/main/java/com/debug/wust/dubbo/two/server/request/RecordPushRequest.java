package com.debug.wust.dubbo.two.server.request;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class RecordPushRequest implements Serializable {

    private Integer itemId;

    private Integer total;

    private String customerName;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
