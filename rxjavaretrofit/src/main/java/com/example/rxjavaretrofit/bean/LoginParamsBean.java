package com.example.rxjavaretrofit.bean;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author Ke
 * @version [版本号，2017/8/7 14:22]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */

public class LoginParamsBean {

    private String username;
    private String password;
    private String ip;
    private String ifCheckIp;
    private String ifCheckUnique;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIfCheckIp() {
        return ifCheckIp;
    }

    public void setIfCheckIp(String ifCheckIp) {
        this.ifCheckIp = ifCheckIp;
    }

    public String getIfCheckUnique() {
        return ifCheckUnique;
    }

    public void setIfCheckUnique(String ifCheckUnique) {
        this.ifCheckUnique = ifCheckUnique;
    }
}
