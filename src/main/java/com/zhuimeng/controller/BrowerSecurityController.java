package com.zhuimeng.controller;


import com.sun.deploy.net.HttpResponse;
import com.zhuimeng.controller.code.SecurityProperties;
import com.zhuimeng.controller.support.SimpleResponse;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2018/8/11.
 */
@Controller
public class BrowerSecurityController {
    private Logger logger= LoggerFactory.getLogger(getClass());

    private RequestCache requestCache=new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    /*@RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest!=null){
            String targetUrl = savedRequest.getRedirectUrl();
            logger.info("引发跳转的是："+targetUrl);
            if (StringUtils.endsWithIgnoreCase(targetUrl,".html")){
                redirectStrategy.sendRedirect(request,response,securityProperties.getBrowser().getLoginPage());
            }
        }
        return new SimpleResponse("你访问的内容需要身份认证，请引导用户到登入界面");
    }*/

    @RequestMapping("/authentication/require")
    public String requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest!=null){
            String targetUrl = savedRequest.getRedirectUrl();
            logger.info("引发跳转的是："+targetUrl);
            if (StringUtils.endsWithIgnoreCase(targetUrl,".html")){
                redirectStrategy.sendRedirect(request,response,securityProperties.getBrowser().getLoginPage());
            }
        }
        return "login";
    }
}
