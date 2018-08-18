package com.zhuimeng.controller.imagecode;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2018/8/14.
 */
public class ValidateCodeFilter extends OncePerRequestFilter {
    private AuthenticationFailureHandler authenticationFailureHandler;
    private SessionStrategy sessionStrategy=new HttpSessionSessionStrategy();
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (StringUtils.equals("/authentication/from", uri)
                && StringUtils.equalsIgnoreCase(request.getMethod(),"post")){
            try{
                validate(new ServletWebRequest(request));
            }catch (ValidateException e){
                authenticationFailureHandler.onAuthenticationFailure(request,response,e);
                return;
            }
        }
        filterChain.doFilter(request,response);
    }

    private void validate(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {
        ImageCode imageCode=(ImageCode) sessionStrategy.getAttribute(servletWebRequest,ValidateCodeController.SESSION_KEY);
        String codeInRequest= ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(),"imageCode");
        if (StringUtils.isBlank(codeInRequest)){
            throw new ValidateException("验证码不能为空！");
        }
        if (imageCode==null){
            throw new ValidateException("验证码不存在！！");
        }
        if (imageCode.isExpried()){
            sessionStrategy.removeAttribute(servletWebRequest,ValidateCodeController.SESSION_KEY);
            throw new ValidateException("验证码已过期！！");
        }
        if (!StringUtils.equals(imageCode.getCode(),codeInRequest)){
            throw new ValidateException("验证码不匹配！！");
        }
        sessionStrategy.removeAttribute(servletWebRequest,ValidateCodeController.SESSION_KEY);
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }
}
