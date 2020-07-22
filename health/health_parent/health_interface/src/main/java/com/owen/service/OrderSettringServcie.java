package com.owen.service;

import com.owen.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

public interface OrderSettringServcie {

    void add(List<OrderSetting> data);

    List<Map> getOrderSettingByMonth(String date);

    void editNumberByDate(OrderSetting orderSetting);
}
