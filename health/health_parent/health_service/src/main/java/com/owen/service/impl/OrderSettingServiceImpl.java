package com.owen.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.owen.dao.OrderSettingDao;
import com.owen.pojo.OrderSetting;
import com.owen.service.OrderSettringServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = OrderSettringServcie.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettringServcie {
    @Autowired
    private OrderSettingDao orderSettingDao;

    @Override
    public void add(List<OrderSetting> data) {
        if (data != null || data.size() > 0) {

            for (OrderSetting orderSetting : data) {
                Integer count = orderSettingDao.findByDate(orderSetting.getOrderDate());
                if (count > 0) {
//                    以及存在了，进行修改
                    orderSettingDao.edit(orderSetting);

                } else {
//                    没存在插入
                    orderSettingDao.add(orderSetting);
                }

            }
        }
    }

    @Override
    public List<Map> getOrderSettingByMonth(String date) {
        String beginDate = date + "-1";
        String endDate = date + "-31";
        Map<String, String> map = new HashMap<>();
        map.put("beginDate", beginDate);
        map.put("endDate", endDate);
        List<Map> list = new ArrayList<>();
        List<OrderSetting> orderSettings = orderSettingDao.getOrderSettingByMonth(map);
        if (orderSettings != null && orderSettings.size() > 0) {
            for (OrderSetting orderSetting : orderSettings) {
                Map<String, Object> m = new HashMap<>();
                m.put("date", orderSetting.getOrderDate().getDate());
                m.put("number", orderSetting.getNumber());
                m.put("reservations", orderSetting.getReservations());
                list.add(m);
            }
        }
        return list;

    }

    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        Integer index = orderSettingDao.findByDate(orderSetting.getOrderDate());
        if (index>0){
            orderSettingDao.edit(orderSetting);
        }
        else {
            orderSettingDao.add(orderSetting);
        }
    }
}
