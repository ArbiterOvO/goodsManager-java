package com.arbiter.goodsmanager.controller.good;

import com.alibaba.fastjson.JSONObject;
import com.arbiter.goodsmanager.pojo.Good;
import com.arbiter.goodsmanager.result.Result;
import com.arbiter.goodsmanager.service.good.GoodService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/good")
public class GoodController {

    private final GoodService goodService;

    @GetMapping("/all")
    public Result<List<JSONObject>> getAllGoods()
    {
        List<Good> allGoods = goodService.getAllGoods();
        List<JSONObject> jsonList = new ArrayList<>();
        for (Good good : allGoods) {
            JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(good));
            jsonList.add(jsonObject);
        }
        return Result.success(jsonList);
    }
}
