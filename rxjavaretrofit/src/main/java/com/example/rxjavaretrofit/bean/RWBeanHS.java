package com.example.rxjavaretrofit.bean;

import java.io.Serializable;

/**
 * Created by zhaoke on 2018/1/18.
 */

public class RWBeanHS implements Serializable {

    /**
     * id : c210e7a2b3254006a3b89aaaf6ae4e72
     * name : 南京测试 红太阳
     * displayName : 南京测试 红太阳南京测试 红太阳
     * discription : 南京测试 红太阳南京测试 红太阳南京测试 红太阳南京测试 红太阳
     * appType : 3
     * imageThumb : /group1/M02/03/02/CiWVZlouY1uAY3CgAATmMzob-a4564.png
     * image : null
     * latitude : 45
     * longitude : 103
     * mapLevel : 3
     * startTime : 2017-12-04
     * endTime : 2017-12-23
     * available : 1
     * cityCode : 320100
     * createUser : null
     * createTime : null
     * updateUser : null
     * updateTime : null
     * delFlag : 0
     */

    private String id;
    private String name;
    private String displayName;
    private String discription;
    private String appType;
    private String imageThumb;
    private Object image;
    private double latitude;
    private double longitude;
    private int mapLevel;
    private String startTime;
    private String endTime;
    private String available;
    private int cityCode;
    private Object createUser;
    private Object createTime;
    private Object updateUser;
    private Object updateTime;
    private String delFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getImageThumb() {
        return imageThumb;
    }

    public void setImageThumb(String imageThumb) {
        this.imageThumb = imageThumb;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getMapLevel() {
        return mapLevel;
    }

    public void setMapLevel(int mapLevel) {
        this.mapLevel = mapLevel;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public Object getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Object createUser) {
        this.createUser = createUser;
    }

    public Object getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Object createTime) {
        this.createTime = createTime;
    }

    public Object getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Object updateUser) {
        this.updateUser = updateUser;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
