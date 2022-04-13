package com.muke.onlineedu.common.info;

public class LoginInfo {
    private String sign;
    private String account;
    private String password;

    public LoginInfo(String sign, String account, String password) {
        this.sign = sign;
        this.account = account;
        this.password = password;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
