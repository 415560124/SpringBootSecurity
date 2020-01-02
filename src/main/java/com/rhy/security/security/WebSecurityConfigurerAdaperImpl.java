package com.rhy.security.security;

import com.rhy.security.entity.Function;
import com.rhy.security.entity.MenuFunction;
import com.rhy.security.entity.Role;
import com.rhy.security.entity.RoleMenu;
import com.rhy.security.mapper.IFunctionMapper;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Auther: Herion_Rhy
 * @Description: 安全规则配置类
 * @Date: Created in 2019/12/28 16:46
 * @Modified By:
 * @Version: 1.0.0
 */
@Configuration
public class WebSecurityConfigurerAdaperImpl extends WebSecurityConfigurerAdapter {


    @Value("${system.user.password.secret}")
    private String secret = null;
    /**
     * 用户信息实现类
     */
    @Qualifier("userDetailServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;
    /**
     * 密码编码器实现类
     */
    @Qualifier("passwordEncoderImpl")
    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * 异常自定义处理
     */
    @Autowired
    private AuthenticationEntryPointImpl authenticationEntryPoint;
    /**
     * 异常自定义处理
     */
    @Autowired
    private RestAccessDeniedHandler restAccessDeniedHandler;
    /**
     * 认证失败实现类
     */
    @Qualifier("authenticationFailureHandlerImpl")
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    /**
     * 认证成功实现类
     */
    @Qualifier("authenticationSuccessHandlerImpl")
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    /**
     * JWT验证过滤器
     */
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    /**
     * 自己注入provider
     * 否则无法抛出UserNameNotFound异常
     */
    @Bean
    public DaoAuthenticationProvider iniDaoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setHideUserNotFoundExceptions(false);
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }
    /**
     * 用户验证配置
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(iniDaoAuthenticationProvider());
    }

    /**
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
    @Autowired
    IFunctionMapper iFunctionMapper;
    /**
     * http请求验证配置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * 在 UsernamePasswordAuthenticationFilter 之前添加 JwtAuthenticationTokenFilter
         */
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        //初始化加载配置的服务权限
        initFunctionSetting(http);
        /** 第一段 **/
        http.authorizeRequests()
        //限定 role_user/role_admin角色访问
//        .antMatchers("/user/welcome","/user/details").hasAnyRole("USER","ADMIN")
        //限定 /admin 下所有请求权限赋予角色 ADMIN
//        .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
        //还可以使用正则 (.*任意字符匹配多次)
//        .regexMatchers("/admin/.*").hasAuthority("ROLE_ADMIN")
        //access 使用Spring表达式设置权限
        //要求是完整登录（非记住我登录）
//        .antMatchers("/adminspring/welcome").access("hasAnyAuthority('ROLE_ADMIN') && isFullyAuthenticated()")
        //允许记住我功能
//        .antMatchers("/adminspring/welcome1").access("hasAnyAuthority('ROLE_ADMIN')")
        //其他路径允许签名后访问
        .anyRequest().permitAll()
        //使用记住我功能
        .and().rememberMe()

        /** 第二段 **/
        /** and代表连接词 **/
        //对于没有配置权限的其他请求允许匿名访问
        .and().anonymous()

        /** 第三段 **/
        //使用spring security默认的登录页面
        .and().formLogin()
        //启用http基础验证
        .and().httpBasic().realmName("my-basic-name")
        /** 自定义失败成功处理器 **/
        .and().formLogin().failureHandler(authenticationFailureHandler)
        .and().formLogin().successHandler(authenticationSuccessHandler)
        //禁用csrf
        .and().csrf().disable()
        //ALWAYS：总是创建HttpSession
        //IF_REQUIRED：Spring Security只会在需要时创建一个HttpSession
        //NEVER：Spring Security不会创建HttpSession，但如果它已经存在，将可以使用HttpSession
        //STATELESS：Spring Security永远不会创建HttpSession，它不会使用HttpSession来获取SecurityContext
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).accessDeniedHandler(restAccessDeniedHandler);
    }
    void initFunctionSetting(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry httpObj = http.authorizeRequests();
        //获得所有服务列表
        List<Function> functions = iFunctionMapper.selectAll();
        Set<String> set = new HashSet<>();
        for(Function function : functions){
            //服务关联的菜单
            for(MenuFunction menuFunction : function.getMenuFunctionList()){
                //菜单关联的role
                for(RoleMenu roleMenu : menuFunction.getMenu().getRoleMenus()){
                    Role role = roleMenu.getRole();
                    set.add(role.getRoleName());
                }
            }
            int len = set.size();
            String[] roles = new String[len];
            set.toArray(roles);
            httpObj.antMatchers(function.getPath()).hasAnyAuthority(roles);
        }
    }
}
