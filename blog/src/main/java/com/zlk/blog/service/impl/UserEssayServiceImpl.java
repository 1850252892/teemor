package com.zlk.blog.service.impl;

import com.zlk.blog.entity.UserEssay;
import com.zlk.blog.mapper.UserEssayMapper;
import com.zlk.blog.service.UserEssayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserEssayServiceImpl implements UserEssayService {
   @Autowired
    UserEssayMapper userEssayMapper;

    @Override
    public String insert(UserEssay userEssay) {
        int key=userEssayMapper.insert(userEssay);
        if (key>0)
            return "TRUE";
        return "FALSE";
    }

    @Override
    public String delete(UserEssay userEssay) {
        int key=userEssayMapper.deleteByPrimaryKey(userEssay);
        if (key>0)
            return "TRUE";
        return "FALSE";
    }
}
