package cn.xinhua.bike.domain;

import java.io.Serializable;

/**
 * 单车买卖实体类
 */
public class Bicycle implements Serializable {

    private int tasknumber;//买卖任务号
    private String brand;//单车品牌
    private String colour;//单车颜色
    private String freetime;//单车空闲时间
    private String address;   //单车停放地址
    private String price;   //买卖价格
    private String name;//卖家姓名
    private int phone;//卖家手机号
    private String remarks;//备注
    private String wechat;//卖家微信
    private String r_uid;//卖家用户名
    private String releasedata;//发布时间
    private  String bl_uid;//买家用户名
    private int cid;//所属分类(租或卖)

    /**
     * 无参构造方法
     */
    public Bicycle() {
    }

    /**
     * 有参构造方法
     * @param tasknumber
     * @param brand
     * @param colour
     * @param freetime
     * @param address
     * @param price
     * @param name
     * @param phone
     * @param remarks
     * @param wechat
     * @param r_uid
     * @param releasedata
     * @param bl_uid
     * @param cid
     */
    public Bicycle(int tasknumber, String brand, String colour, String freetime, String address, String price, String name, int phone, String remarks, String wechat, String r_uid, String releasedata, String bl_uid, int cid) {
        this.tasknumber = tasknumber;
        this.brand = brand;
        this.colour = colour;
        this.freetime = freetime;
        this.address = address;
        this.price = price;
        this.name = name;
        this.phone = phone;
        this.remarks = remarks;
        this.wechat = wechat;
        this.r_uid = r_uid;
        this.releasedata = releasedata;
        this.bl_uid = bl_uid;
        this.cid = cid;
    }

    public int getTasknumber() {
        return tasknumber;
    }

    public void setTasknumber(int tasknumber) {
        this.tasknumber = tasknumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getFreetime() {
        return freetime;
    }

    public void setFreetime(String freetime) {
        this.freetime = freetime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getR_uid() {
        return r_uid;
    }

    public void setR_uid(String r_uid) {
        this.r_uid = r_uid;
    }

    public String getReleasedata() {
        return releasedata;
    }

    public void setReleasedata(String releasedata) {
        this.releasedata = releasedata;
    }

    public String getBl_uid() {
        return bl_uid;
    }

    public void setBl_uid(String bl_uid) {
        this.bl_uid = bl_uid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
}

