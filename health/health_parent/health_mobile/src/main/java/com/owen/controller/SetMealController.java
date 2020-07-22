package com.owen.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.owen.constant.MessageConstant;
import com.owen.entity.Result;
import com.owen.pojo.SetMeal;
import com.owen.service.SetMealService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/setMeal")
public class SetMealController {
    @Reference
    private SetMealService setMealService;

    @RequestMapping("/getSetMeal")
    public Result getSetMeal() {
        List<SetMeal> list = null;
        try {
            list = setMealService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_SETMEAL_LIST_FAIL);
        }
        return new Result(true, MessageConstant.GET_SETMEAL_LIST_SUCCESS, list);
    }
    @RequestMapping("/findById")
    public Result findById(Integer id){
        SetMeal setMeal= null;
        try {
            setMeal = setMealService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_SETMEAL_FAIL);
        }
        return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,setMeal);

    }

}
