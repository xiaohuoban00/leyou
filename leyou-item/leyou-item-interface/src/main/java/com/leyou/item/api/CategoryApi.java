package com.leyou.item.api;

import com.leyou.item.pojo.Category;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@RequestMapping("category")
public interface CategoryApi {


    /**
     * 根据父节点的id查询子节点
     * @param pid
     * @return
     */
    @RequestMapping("list")
    public List<Category> queryCategoriesByPid(@RequestParam(value = "pid",defaultValue = "0") Long pid);

    /**
     * 根据Id查询商品名称
     * @param Ids
     * @return
     */
    @GetMapping
    public List<String> queryNameByIds(@RequestParam("Ids") List<Long> Ids);
}
