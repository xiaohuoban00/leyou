package com.leyou.elasticsearch.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.leyou.common.PageResult;
import com.leyou.item.bo.SpuBo;
import com.leyou.repository.GoodsRepository;
import com.leyou.search.client.GoodsClient;
import com.leyou.search.pojo.Goods;
import com.leyou.search.service.SearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ElasticsearchTest {

    @Autowired
    private ElasticsearchTemplate template;
    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private GoodsClient client;
    @Autowired
    private SearchService searchService;
    @Test
    public void test(){
        template.createIndex(Goods.class);
        template.putMapping(Goods.class);
        Integer page = 1;
        Integer rows = 100;
        do {
            PageResult<SpuBo> result = client.querySpuByPage(null, null, page, rows);
            List<SpuBo> items = result.getItems();
            List<Goods> goods = items.stream().map(spuBo -> {
                try {
                    return searchService.buildGoods(spuBo);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                return null;
            }).collect(Collectors.toList());
            goodsRepository.saveAll(goods);
            rows=items.size();
            page++;
        } while (rows==100);
    }
}
