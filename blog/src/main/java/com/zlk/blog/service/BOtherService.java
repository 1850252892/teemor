package com.zlk.blog.service;

import com.zlk.blog.entity.BOther;

public interface BOtherService {
    String addBrowse(String bid);

    String addPraise(String bid);

    String addDiss(String bid);

    BOther selectBother(String bid);

    String delete(String bid);

    String insert(BOther bOther);
}
