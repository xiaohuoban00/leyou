package com.leyou.item.service;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;

import java.util.List;

public interface SpecificationService {
    List<SpecGroup> queryGroupsByCid(Long cid);

    List<SpecParam> queryParamsByGid(Long gid, Long cid, Boolean generic, Boolean searching);

    void saveGroup(SpecGroup specGroup);


    List<SpecGroup> queryGroupsWithParam(Long cid);
}
