package com.leyou.item.api;

import com.leyou.common.PageResult;
import com.leyou.item.bo.SpuBo;
import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.Spu;
import com.leyou.item.pojo.SpuDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface GoodsApi {
    /**
     * 根据条件分页查询Spu
     * @param key
     * @param saleable
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("spu/page")//key=&saleable=true&page=1&rows=5
    public PageResult<SpuBo> querySpuByPage(
            @RequestParam(value = "key",required = false)String key,
            @RequestParam(value = "saleable",required = false)Boolean saleable,
            @RequestParam(value = "page",required = true,defaultValue = "1")Integer page,
            @RequestParam(value = "rows",required = true,defaultValue = "5")Integer rows
    );

    /**
     * 新增商品
     * @param spuBo
     * @return
     */
    @PostMapping("goods")
    public Void saveGoods(@RequestBody SpuBo spuBo);

    /**
     * 更新商品信息
     * @param spuBo
     * @return
     */
    @PutMapping("goods")
    public Void updateGoods(@RequestBody SpuBo spuBo);

    /**
     * 根据SpuId查询SpuDetail
     * @param spuId
     * @return
     */
    @RequestMapping("spu/detail/{spuId}")
    public SpuDetail querySpuDetailBySpuId(@PathVariable("spuId") Long spuId);

    /**
     * 根据SpuId查询Sku
     * @param id
     * @return
     */
    @RequestMapping("sku/list")
    List<Sku> querySkuBySpuId(@RequestParam("id") Long id);

    @GetMapping("{id}")
    Spu querySpuById(@PathVariable("id") Long id);
}
