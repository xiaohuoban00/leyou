package com.leyou.goods.controller;

import com.leyou.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: zmq
 * Date: 2019/12/20
 */
@Controller
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("item/{id}.html")
    public String toItemPage(@PathVariable("id")Long id, Model model){
        Map<String, Object> map = goodsService.loadData(id);
        model.addAllAttributes(map);
        return "item";
    }
}
