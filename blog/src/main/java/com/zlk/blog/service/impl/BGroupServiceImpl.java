package com.zlk.blog.service.impl;

import com.zlk.blog.entity.BGroupKey;
import com.zlk.blog.mapper.BGroupMapper;
import com.zlk.blog.service.BGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BGroupServiceImpl implements BGroupService {
    @Autowired
    BGroupMapper bgm;

    @Override
    public String deleteByPrimaryKey(BGroupKey key) {
        int value=bgm.deleteByPrimaryKey(key);
        if (value>0)
            return "TRUE";
        return "FALSE";
    }

    @Override
    public String insert(BGroupKey record) {
        int value=bgm.insert(record);
        if (value>0)
            return "TRUE";
        return "FALSE";
    }

    @Override
    public String update(BGroupKey bGroupKey) {
        int value=bgm.update(bGroupKey);
        if (value>0)
            return "TRUE";
        return "FALSE";
    }
}
