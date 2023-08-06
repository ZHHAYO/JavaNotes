package com.hiyongz.config;

import com.hiyongz.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    //创建BCryptPasswordEncoder注入容器
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;


//    @Autowired
//    private AuthenticationEntryPoint authenticationEntryPoint;
//
//    @Autowired
//    private AccessDeniedHandler accessDeniedHandler;
//
//    @Bean
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                //关闭csrf
//                .csrf().disable()
//                //不通过Session获取SecurityContext
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                // 对于登录接口 允许匿名访问
//                .antMatchers("/user/login").anonymous()
////                .antMatchers("/testCors").hasAuthority("system:dept:list222")
//                // 除上面外的所有请求全部需要鉴权认证
//                .anyRequest().authenticated();
//
//        //添加过滤器
//        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
//
//        //配置异常处理器
//        http.exceptionHandling()
//                //配置认证失败处理器
//                .authenticationEntryPoint(authenticationEntryPoint)
//                .accessDeniedHandler(accessDeniedHandler);
//
//        //允许跨域
//        http.cors();
//    }

//    private String[] loadExcludePath() {
//        return new String[]{
//                "/static/**",
//                "/templates/**",
//                "/img/**",
//                "/js/**",
//                "/css/**",
//                "/lib/**"
//        };
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(AuthenticationManager authenticationManager, HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf().disable()
//                .authorizeRequests()
//                //放通所有静态资源
//                .antMatchers(loadExcludePath()).permitAll()
//                //放通注册
//                .antMatchers(HttpMethod.POST,"/user/add").permitAll()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/logger/**").hasAnyRole("ADMIN","LOGGER")
//                .antMatchers("/user/**").hasAnyRole("ADMIN","LOGGER","USER")
//                //其余请求都需要认证后访问
//                .anyRequest()
//                .authenticated()
//                .and()
//                .addFilter(new JwtLoginFilter(authenticationManager,rsaKeyProperties, jedisPool))
//                .addFilter(new JwtVerifyFilter(authenticationManager, rsaKeyProperties,jedisPool))
//                //已认证但是权限不够
//                .exceptionHandling().accessDeniedHandler(new UserAuthAccessDeniedHandler())
//                .and()
//                //未能通过认证，也就是未登录
//                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//禁用session
//        return httpSecurity.build();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //关闭csrf
                .csrf().disable()
                //不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 对于登录接口 允许匿名访问
                .antMatchers("/user/login").anonymous()
//                .antMatchers("/testCors").hasAuthority("system:dept:list222")
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();

        //添加过滤器
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        //配置异常处理器
        http.exceptionHandling()
                //配置认证失败处理器
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .accessDeniedHandler(new UserAuthAccessDeniedHandler());

        //允许跨域
        http.cors();
        return http.build();
    }

    /**
     * 配置跨源访问(CORS)
     * @return
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}