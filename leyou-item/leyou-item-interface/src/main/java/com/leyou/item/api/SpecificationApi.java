package com.leyou.item.api;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("spec")
public interface SpecificationApi {


    /**
     * 根据分类id查询参数组
     * @param cid
     * @return
     */
    @RequestMapping("groups/{cid}")
    public List<SpecGroup> queryGroupsByCid(@PathVariable("cid") Long cid);

    @RequestMapping("params")
    List<SpecParam> queryParamsByGid(
            @RequestParam(value = "gid",required = false) Long gid,
            @RequestParam(value = "cid",required = false) Long cid,
            @RequestParam(value = "generic",required = false) Boolean generic,
            @RequestParam(value = "searching",required = false) Boolean searching
            );

    @RequestMapping("group")
    Void saveGroup(@RequestBody SpecGroup specGroup);
    @GetMapping("group/param/{cid}")
    List<SpecGroup> queryGroupsWithParam(@PathVariable("cid") Long cid);
}
