package com.zhuimeng.controller.code;

/**
 * Created by Administrator on 2018/8/11.
 */
public class BrowserProperties {
    private String loginPage="/login";

    private String loginType="JSON";

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
}
