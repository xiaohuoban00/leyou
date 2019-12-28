package com.leyou.item.service.impl;

import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import com.leyou.item.service.SpecificationService;
import com.netflix.ribbon.Ribbon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private SpecGroupMapper specGroupMapper;
    @Autowired
    private SpecParamMapper specParamMapper;

    /**
     * 根据分类id查询参数组
     * @param cid
     * @return
     */
    @Override
    public List<SpecGroup> queryGroupsByCid(Long cid) {
        SpecGroup specGroup=new SpecGroup();
        specGroup.setCid(cid);
        return specGroupMapper.select(specGroup);
    }

    @Override
    public List<SpecParam> queryParamsByGid(Long gid,Long cid, Boolean generic, Boolean searching) {
        SpecParam specParam=new SpecParam();
        specParam.setGroupId(gid);
        specParam.setCid(cid);
        specParam.setSearching(searching);
        specParam.setGeneric(generic);
        return specParamMapper.select(specParam);
    }

    @Override
    public void saveGroup(SpecGroup specGroup) {
        specGroupMapper.insertSelective(specGroup);
    }

    public List<SpecParam> queryParams(Long gid,Long cid,Boolean generic, Boolean searching){
        SpecParam record = new SpecParam();
        record.setGroupId(gid);
        record.setCid(cid);
        record.setGeneric(generic);
        record.setSearching(searching);
        return specParamMapper.select(record);
    }

    @Override
    public List<SpecGroup> queryGroupsWithParam(Long cid) {
        List<SpecGroup> groups = queryGroupsByCid(cid);
        groups.forEach(group ->{
            List<SpecParam> specParams = queryParams(group.getId(), null, null, null);
            group.setParams(specParams);
        });
        return groups;
    }
}
