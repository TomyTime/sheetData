package com.canteen.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * User: lixy
 * Mail: admin@TomyTime.com
 * Date: 14-2-16 下午6:22
 */
@Entity
@Table(name="purchase")
public class Purchase extends BaseEntity{
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy="uuid")
    @Column(length=32)
    private String id;      //采购编号
    @Column(length = 32)
    private String gid;    //商品编号
    @Column(length = 12)
    private String price;   //商品单价
    @Column(length = 8)
    private String amount;    //采购数量
    @Column(length = 16)
    private String subtotal;   //采购金额
    @Column(length = 14)
    private String daytime;    //采购日期
    @Column(length = 64)
    private String username;    //采购人
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLogtime() {
        return logtime;
    }

    public void setLogtime(String logtime) {
        this.logtime = logtime;
    }
}
