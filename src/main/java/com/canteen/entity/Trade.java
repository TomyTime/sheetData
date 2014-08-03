package com.canteen.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * User: lixy
 * Mail: admin@TomyTime.com
 * Date: 14-2-16 下午6:22
 */
@Entity
@Table(name="trade")
public class Trade extends BaseEntity{
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy="uuid")
    @Column(length=32)
    private String id;      //交易编号
    @Column(length = 32)
    private String gid;    //采购编号
    @Column(length = 32)
    private String uid;   //用户编号
    @Column(length = 16)
    private String price;    //交易数量
    @Column(length = 8)
    private String amount;    //交易数量
    @Column(length = 16)
    private String subtotal;   //交易金额
    @Column(length = 14)
    private String daytime;    //交易日期
    @Column(length = 20)
    private String logtime;     //操作时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getDaytime() {
        return daytime;
    }

    public void setDaytime(String daytime) {
        this.daytime = daytime;
    }

    public String getLogtime() {
        return logtime;
    }

    public void setLogtime(String logtime) {
        this.logtime = logtime;
    }
}
