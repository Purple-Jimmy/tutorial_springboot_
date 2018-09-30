package com.tutorial.interceptor;

import com.tutorial.domain.Account;
import com.tutorial.service.AccountService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SignatureException;

/**
 * jwt token 拦截器
 */
@Component
public class JwtTokenInterceptor implements HandlerInterceptor {
    //jwt 加密key
    public static final String SIGNING_KEY="tutorial_jwt";

    @Autowired
    private AccountService accountService;
    /**
     * controller 执行之前调用
     * @param request
     * @param response
     * @param object
     * @return
     * @throws IOException
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws IOException {
        //剔除拦截路径,并且如果是options请求是cors跨域预请求,设置allow对应头信息
        if(request.getRequestURI().equals("/token") || RequestMethod.OPTIONS.toString().equals(request.getMethod())){
            return true;
        }
        //获取头信息
        final String authHeader = request.getHeader("X-YAuth-Token");
        //如果没有header信息
        if (authHeader == null || authHeader.trim() == "") {
           // throw new SignatureException("not found X-YAuth-Token.");
        }
        try{
            //获取jwt实体对象接口实例,获取jwt串里的subject参数,获取body以后可以获取到存入的信息
            final Claims claims = Jwts.parser().setSigningKey(JwtTokenInterceptor.SIGNING_KEY)
                    .parseClaimsJws(authHeader).getBody();

            String subject = claims.getSubject();
            //查询account
            Account account = accountService.queryById(Long.valueOf(subject));
            //数据库中的token值
            String tokenval = account.getToken();
            //如果内存中不存在,提示客户端获取token
            if(tokenval == null || tokenval.trim() == "") {
                throw new SignatureException("not found token info, please get token agin.");
            }
            //判断内存中的token是否与客户端传来的一致
            if(!tokenval.equals(authHeader))
            {
                throw new SignatureException("not found token info, please get token agin.");
            }
        }catch (SignatureException | MalformedJwtException e){
            //jwt 解析错误
            //输出对象
            PrintWriter writer = response.getWriter();

            //输出error消息
            writer.write("need refresh token");
            writer.close();
            return false;
        }catch (ExpiredJwtException e){
            //jwt 已经过期,在设置jwt的时候如果设置了过期时间,这里会自动判断jwt是否已经过期,如果过期则会抛出这个异常
            //输出对象
            PrintWriter writer = response.getWriter();

            //输出error消息
            writer.write("need refresh token");
            writer.close();
            return false;
        } catch (final Exception e){
            //输出对象
            PrintWriter writer = response.getWriter();

            //输出error消息
            writer.write("need refresh token");
            writer.close();
            return false;
        }
            return true;
    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
