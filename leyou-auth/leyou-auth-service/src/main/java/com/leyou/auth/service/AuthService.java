package com.leyou.auth.service;

import com.leyou.auth.client.UserClient;
import com.leyou.auth.config.JwtProperties;
import com.leyou.common.pojo.UserInfo;
import com.leyou.common.utils.JwtUtils;
import com.leyou.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: zmq
 * Date: 2020/1/1
 */
@Service
public class AuthService {

    @Autowired
    private UserClient userClient;
    @Autowired
    private JwtProperties jwtProperties;

    public String accredit(String username, String password){
        User user = userClient.queryUser(username, password);
        if(user==null){
            return null;
        }
        try {
            UserInfo userInfo=new UserInfo();
            userInfo.setId(user.getId());
            userInfo.setUsername(user.getUsername());
            String token = JwtUtils.generateToken(userInfo, jwtProperties.getPrivateKey(), jwtProperties.getExpire());
            return token;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
