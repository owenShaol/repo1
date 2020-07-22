package com.owen.service;

import com.owen.entity.PageResult;
import com.owen.entity.QueryPageBean;
import com.owen.pojo.CheckGroup;

import java.util.List;

public interface CheckGroupService {
    public PageResult findPage(QueryPageBean queryPageBean);

    void add(Integer[] checkItemIds, CheckGroup checkGroup);

    List<Integer> findItemIds(Integer id);

    void delete(Integer id);

    void update(Integer[] checkItemIds, CheckGroup checkGroup);

    List<CheckGroup> findAll();
}
