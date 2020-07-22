package com.owen.dao;

import com.github.pagehelper.Page;
import com.owen.entity.QueryPageBean;
import com.owen.pojo.CheckItem;

import java.util.ArrayList;

public interface CheckItemDao {
    void add(CheckItem checkItem);

    Page<CheckItem> findPage(String QueryString);

    void deleteById(Integer id);

    void update(CheckItem checkItem);

    Integer findCount(Integer checkItemId);

    ArrayList<CheckItem> findAll();
}
