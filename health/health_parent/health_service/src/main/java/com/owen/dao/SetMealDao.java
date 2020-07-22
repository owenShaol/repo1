package com.owen.dao;

import com.github.pagehelper.Page;
import com.owen.pojo.CheckGroup;
import com.owen.pojo.SetMeal;

import java.util.List;
import java.util.Map;

public interface SetMealDao {
    Page<SetMeal> findPage(String queryString);

    void add(SetMeal setMeal);

    void addSetMealAndGroup(Map<String, Integer> map);

    void delete(Integer setMealId);

    void deleteSetMealAndGroup(Integer setMealId);

    List<SetMeal> findAll();

    SetMeal findById(Integer id);

    List<CheckGroup> findGroups(Integer setMeal);
}
