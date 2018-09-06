package com.zlk.blog.service;

import com.zlk.blog.entity.BGroupKey;

public interface BGroupService {
    /**
     * 删除博客分组信息
     * @param key
     * @return
     */
    String deleteByPrimaryKey(BGroupKey key);

    /**
     * 插入博客分组信息
     * @param record
     * @return
     */
    String insert(BGroupKey record);

    /**
     * 修改博客分组信息
     * @param bGroupKey
     * @return
     */
   String update(BGroupKey bGroupKey);
}
