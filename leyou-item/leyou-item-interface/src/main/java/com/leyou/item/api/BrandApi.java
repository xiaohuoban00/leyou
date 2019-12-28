package com.leyou.item.api;

import com.leyou.common.PageResult;
import com.leyou.item.pojo.Brand;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@RequestMapping("brand")
public interface BrandApi {


    /**
     * 根据查询条件分页查询品牌信息
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @return
     */
    @RequestMapping("page")
    public PageResult<Brand> queryBrandsByPage(
            @RequestParam(value = "key",required = false) String key,
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "rows",defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy",required = false) String sortBy,
            @RequestParam(value = "desc",required = false) Boolean desc
    );

    /**
     * 新增品牌信息
     * @param brand
     * @param cids
     */
    @RequestMapping
    public Void saveBrand(Brand brand,@RequestParam("cids") List<Long> cids);

    /**
     * 根据分类id查询品牌
     * @param cid
     * @return
     */
    @RequestMapping("cid/{cid}")
    public List<Brand> queryBrandByCid(@PathVariable("cid") Long cid);

    @GetMapping("{id}")
    public Brand queryBrandById(@PathVariable("id") Long id);
}
