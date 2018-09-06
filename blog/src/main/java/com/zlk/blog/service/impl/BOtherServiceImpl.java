package com.zlk.blog.service.impl;

import com.zlk.blog.entity.BOther;
import com.zlk.blog.mapper.BOtherMapper;
import com.zlk.blog.service.BOtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BOtherServiceImpl implements BOtherService {
    @Autowired
    BOtherMapper bOtherMapper;

    @Override
    public String addBrowse(String bid) {
        BOther bOther=bOtherMapper.selectByPrimaryKey(bid);
        bOther.setBrowse(bOther.getBrowse()+1);
        int key=bOtherMapper.updateByPrimaryKeySelective(bOther);
        if (key>0)
            return "TRUE";
        return "FALSE";
    }

    @Override
    public String addPraise(String bid) {
        BOther bOther=bOtherMapper.selectByPrimaryKey(bid);
        bOther.setGreat(bOther.getGreat()+1);
        int key=bOtherMapper.updateByPrimaryKeySelective(bOther);
        if (key>0)
            return "TRUE";
        return "FALSE";
    }

    @Override
    public String addDiss(String bid) {
        BOther bOther=bOtherMapper.selectByPrimaryKey(bid);
        bOther.setDiss(bOther.getDiss()+1);
        int key=bOtherMapper.updateByPrimaryKeySelective(bOther);
        if (key>0)
            return "TRUE";
        return "FALSE";
    }

    @Override
    public BOther selectBother(String bid) {
        return bOtherMapper.selectByPrimaryKey(bid);
    }

    @Override
    public String delete(String bid) {
        int key=bOtherMapper.deleteByPrimaryKey(bid);
        if (key>0)
            return "TRUE";
        return "FALSE";
    }

    @Override
    public String insert(BOther bOther) {
        int key=bOtherMapper.insert(bOther);
        if (key>0)
            return "TRUE";
        return "FALSE";
    }
}
