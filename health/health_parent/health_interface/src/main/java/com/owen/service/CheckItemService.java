package com.owen.service;

import com.owen.entity.PageResult;
import com.owen.entity.QueryPageBean;
import com.owen.pojo.CheckItem;

import java.util.ArrayList;

public interface CheckItemService {
    void add(CheckItem checkItem);

    PageResult findPage(QueryPageBean queryPageBean);

    void deleteById(Integer id);

    void update(CheckItem checkItem);


    ArrayList<CheckItem> findAll();
}
