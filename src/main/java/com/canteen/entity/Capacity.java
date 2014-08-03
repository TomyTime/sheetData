package com.canteen.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * User: lixy
 * Mail: admin@TomyTime.com
 * Date: 14-2-16 下午6:22
 */
@Entity
@Table(name="capacity")
public class Capacity extends BaseEntity{
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy="uuid")
    @Column(length=32)
    private String id;
    @Column(length = 32)
    private String gid; //采购编号
    @Column(length=12)
    private String price;    //单价
    @Column(length=8)
    private String amount;    //库存数量
    @Column(length=16)
    private String subtotal;   //库存金额

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }
}
