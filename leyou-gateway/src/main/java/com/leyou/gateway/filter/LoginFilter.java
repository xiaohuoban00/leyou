package com.leyou.gateway.filter;

import com.leyou.common.utils.CookieUtils;
import com.leyou.common.utils.JwtUtils;
import com.leyou.gateway.config.FilterProperties;
import com.leyou.gateway.config.JwtProperties;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: zmq
 * Date: 2020/1/3
 */
@Component
@EnableConfigurationProperties({JwtProperties.class, FilterProperties.class})
public class LoginFilter extends ZuulFilter {

    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private FilterProperties filterProperties;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        List<String> allowPaths = filterProperties.getAllowPaths();
        //初始化运行上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        //获取request对象
        HttpServletRequest request = currentContext.getRequest();
        String url = request.getRequestURL().toString();
        for (String allowPath :allowPaths){
            if(StringUtils.contains(url,allowPath)){
                return false;
            }
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //初始化运行上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        //获取request对象
        HttpServletRequest request = currentContext.getRequest();
        HttpServletResponse response = currentContext.getResponse();
        String token = CookieUtils.getCookieValue(request, jwtProperties.getCookieName());
        try {
            JwtUtils.getInfoFromToken(token,jwtProperties.getPublicKey());
        } catch (Exception e) {
            e.printStackTrace();
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
        }
        return null;
    }
}
