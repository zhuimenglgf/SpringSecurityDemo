package com.zhuimeng.controller.code;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Administrator on 2018/8/11.
 */
@ConfigurationProperties(prefix = "zhuimeng.security")
public class SecurityProperties {
    BrowserProperties browser=new BrowserProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}
