package com.owen.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.owen.dao.CheckGroupDao;
import com.owen.entity.PageResult;
import com.owen.entity.QueryPageBean;
import com.owen.pojo.CheckGroup;
import com.owen.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupDao checkGroupDao;

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        Page<CheckGroup> page = checkGroupDao.findPage(queryPageBean.getQueryString());

        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void add(Integer[] checkItemIds, CheckGroup checkGroup) {
        checkGroupDao.add(checkGroup);

        Map<String, Integer> map = new HashMap<>();
        if (checkItemIds != null && checkItemIds.length > 0) {
            for (Integer checkItemId : checkItemIds) {
                map.put("checkGroupId", checkGroup.getId());
                map.put("checkItemId", checkItemId);
                checkGroupDao.addItemAndGroup(map);
            }
        }

    }

    @Override
    public List<Integer> findItemIds(Integer id) {
        return checkGroupDao.findItemIds(id);
    }

    @Override
    public void delete(Integer id) {
        checkGroupDao.deleteItemId(id);
        checkGroupDao.deleteGroup(id);
    }

    @Override
    public void update(Integer[] checkItemIds, CheckGroup checkGroup) {
//        先修改检查组信息，之后将里面的数据都抹除，之后写入新的数据
        checkGroupDao.update(checkGroup);
        checkGroupDao.deleteItemId(checkGroup.getId());
        Map<String,Integer> map=new HashMap<>();
        for (Integer checkItemId : checkItemIds) {
            map.put("checkGroupId",checkGroup.getId());
            map.put("checkItemId",checkItemId);
            checkGroupDao.addItemAndGroup(map);
        }
    }

    @Override
    public List<CheckGroup> findAll() {

        return checkGroupDao.findAll();
    }
}
