package com.leyou.auth.client;

import com.leyou.user.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by IntelliJ IDEA.
 * User: zmq
 * Date: 2020/1/1
 */
@FeignClient("leyou-user-service")
public interface UserClient extends UserApi {
}
