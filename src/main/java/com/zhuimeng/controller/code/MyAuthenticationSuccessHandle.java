package com.zhuimeng.controller.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhuimeng.enumstatus.LoginType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2018/8/11.
 */
@Component("myAuthenticationSuccessHandle")
//public class MyAuthenticationSuccessHandle implements AuthenticationSuccessHandler {
public class MyAuthenticationSuccessHandle extends SimpleUrlAuthenticationSuccessHandler {
    private Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        if (LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())){
            logger.info("登入成功");
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(authentication));
        }else {
            super.onAuthenticationSuccess(httpServletRequest,httpServletResponse,authentication);
        }

    }
}
