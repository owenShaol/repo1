package com.owen.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.owen.constant.RedisConstant;
import com.owen.dao.CheckGroupDao;
import com.owen.dao.SetMealDao;
import com.owen.entity.PageResult;
import com.owen.entity.QueryPageBean;
import com.owen.pojo.CheckGroup;
import com.owen.pojo.CheckItem;
import com.owen.pojo.SetMeal;
import com.owen.service.SetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = SetMealService.class)
@Transactional
public class SetMealServiceImpl implements SetMealService {
    @Autowired
    private JedisPool jedisPool;
    @Autowired
    private SetMealDao setMealDao;
    @Autowired
    private CheckGroupDao checkGroupDao;


    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        Page<SetMeal> page = setMealDao.findPage(queryPageBean.getQueryString());
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void add(Integer[] checkGroupIds, SetMeal setMeal) {
        setMealDao.add(setMeal);
        Map<String, Integer> map = new HashMap<>();
        for (Integer checkGroupId : checkGroupIds) {
            map.put("setmealId", setMeal.getId());
            map.put("checkGroupId", checkGroupId);

            setMealDao.addSetMealAndGroup(map);
        }
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES, setMeal.getImg());
    }

    @Override
    public void delete(Integer setMealId) {
        setMealDao.deleteSetMealAndGroup(setMealId);
        setMealDao.delete(setMealId);
    }

    @Override
    public List<SetMeal> findAll() {
        return setMealDao.findAll();
    }

    @Override
    public SetMeal findById(Integer id) {
        SetMeal setMeal = setMealDao.findById(id);
        Integer setMealId = setMeal.getId();
        List<CheckGroup> groups = setMealDao.findGroups(setMealId);
        for (CheckGroup group : groups) {

            List<CheckItem> items = checkGroupDao.findItems(group.getId());
            group.setCheckItems(items);

        }
        setMeal.setCheckGroups(groups);
        return setMeal;
    }


}
