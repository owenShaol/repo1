package com.owen.dao;

import com.github.pagehelper.Page;
import com.owen.pojo.CheckGroup;
import com.owen.pojo.CheckItem;

import java.util.List;
import java.util.Map;

public interface CheckGroupDao {
    Page<CheckGroup> findPage(String QueryString);

    void add(CheckGroup checkGroup);
    void addItemAndGroup(Map map);

    List<Integer> findItemIds(Integer id);

    void deleteGroup(Integer id);

    void deleteItemId(Integer id);

    void update(CheckGroup checkGroup);

    List<CheckGroup> findAll();


    List<CheckItem> findItems(Integer id);
}
