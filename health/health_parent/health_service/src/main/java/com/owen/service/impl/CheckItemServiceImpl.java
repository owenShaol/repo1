package com.owen.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.owen.dao.CheckItemDao;
import com.owen.entity.PageResult;
import com.owen.entity.QueryPageBean;
import com.owen.pojo.CheckItem;
import com.owen.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl  implements CheckItemService {

    @Autowired
    private CheckItemDao checkItemDao;
    @Override
    public void add(CheckItem checkItem) {

        checkItemDao.add(checkItem);
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        /*
        1.用PageHelper的分页方法，将其进行绑定
        2.执行分页的语句，只用写查询的条件
        3.返回的类型必须是Page类型，泛型的类型就是对应的需要分页数据的类型
        4.返回的page可以直接返回total和result结果集合
         */
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<CheckItem> page = checkItemDao.findPage(queryPageBean.getQueryString());
        long total = page.getTotal();
        List<CheckItem> rows = page.getResult();
        return new PageResult(total,rows);
    }

    @Override
    public void deleteById(Integer id) {
        Integer count = checkItemDao.findCount(id);
        if (count!=0){
            throw new RuntimeException();
        }
        checkItemDao.deleteById(id);
    }

    @Override
    public void update(CheckItem checkItem) {


        checkItemDao.update(checkItem);
    }

    @Override
    public ArrayList<CheckItem> findAll() {
        return checkItemDao.findAll();
    }
}
