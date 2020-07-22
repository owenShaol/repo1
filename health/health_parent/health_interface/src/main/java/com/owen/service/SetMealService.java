package com.owen.service;

import com.owen.entity.PageResult;
import com.owen.entity.QueryPageBean;
import com.owen.pojo.SetMeal;

import java.util.List;

public interface SetMealService {
    PageResult findPage(QueryPageBean queryPageBean);

    void add(Integer[] checkGroupIds, SetMeal setMeal);

    void delete(Integer setMealId);

    List<SetMeal> findAll();

    SetMeal findById(Integer id);
}
