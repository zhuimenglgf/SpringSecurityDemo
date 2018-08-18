package com.zhuimeng.config;
import com.zhuimeng.controller.code.MyAuthenticationFailureHandle;
import com.zhuimeng.controller.code.MyAuthenticationSuccessHandle;
import com.zhuimeng.controller.code.SecurityProperties;
import com.zhuimeng.controller.imagecode.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

/**
 * Created by Administrator on 2018/8/9.
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private MyAuthenticationSuccessHandle myAuthenticationSuccessHandle;

    @Autowired
    private MyAuthenticationFailureHandle myAuthenticationFailureHandle;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter validateCodeFilter=new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandle);
        http.addFilterBefore(validateCodeFilter, AbstractPreAuthenticatedProcessingFilter.class)
                .formLogin() //用自己的登入界面进行登入
                .loginPage("/authentication/require") //指定登入的url
                .loginProcessingUrl("/authentication/from")//指定登入要提交的from表单
                .successHandler(myAuthenticationSuccessHandle)
                .failureHandler(myAuthenticationFailureHandle)
                .and()
                .authorizeRequests() //方法有很多子方法，每个子匹配器将会按照声明的顺序起作用。
                .antMatchers("/authentication/require","/login","/code/image",securityProperties.getBrowser().getLoginPage()).permitAll()
                                    //过滤这些页面不做验证
                .anyRequest().authenticated() //任何没有被过滤的url，在登入完成后都可以被访问
                .and()
                .csrf().disable();  //先禁止csrf认证
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
