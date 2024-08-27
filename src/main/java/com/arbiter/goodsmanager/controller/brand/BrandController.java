package com.arbiter.goodsmanager.controller.brand;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.arbiter.goodsmanager.pojo.Brand;
import com.arbiter.goodsmanager.result.Result;
import com.arbiter.goodsmanager.service.brand.BrandService;
import com.arbiter.goodsmanager.util.JsonUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/brand")
public class BrandController {

    private final BrandService brandService;

    @GetMapping("/all")
    public Result<List<JSONObject>> getAllBrands()
    {
        List<Brand> list = brandService.list();
        List<JSONObject> jsonObjects = JsonUtil.ListToJsonList(list);
        return Result.success(jsonObjects);
    }

}
