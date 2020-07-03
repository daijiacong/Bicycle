package cn.xinhua.bike.web.filter;

import cn.xinhua.bike.util.JedisUtil;
import redis.clients.jedis.Jedis;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录验证的过滤器
 */
@WebFilter("/a/*")
public class LoginFilter implements Filter {


    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
       // System.out.println(req);
        //0.强制转换
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response=(HttpServletResponse) resp;
        //1.获取资源请求路径
        String uri = request.getRequestURI();
        //2.判断是否包含登录相关资源路径,要注意排除掉 css/js/图片/验证码等资源
        if(uri.contains("/login.jsp") || uri.contains("/user/login") || uri.contains("/css/") || uri.contains("/js/") || uri.contains("/fonts/") || uri.contains("/checkCode")  ){
            //包含，用户就是想登录。放行
            chain.doFilter(req, resp);
        }else{
            //不包含，需要验证用户是否登录
            //3.从获取session中获取user
            /*Object user = request.getSession().getAttribute("user");

            if(user != null){
                System.out.println("您登录了");
                //登录了。放行
                chain.doFilter(req, resp);
            }else{
                //没有登录。跳转登录页面
                System.out.println("您尚未登录，请登录");

                //2.跳转登录页面
                request.getRequestDispatcher("/user/exit").forward(request,resp);
            }*/
           //获取Heather中的参数
           String token =request.getHeader("token");
            //System.out.println("Heather中的参数token"+token);
            //获取jedis客户端
            Jedis jedis = JedisUtil.getJedis();
            String user = jedis.get(String.valueOf(token));
            if(user != null){
                System.out.println("您登录了");
                //登录了。放行
                chain.doFilter(req, resp);
            }
        }


        // chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {
    }

}
