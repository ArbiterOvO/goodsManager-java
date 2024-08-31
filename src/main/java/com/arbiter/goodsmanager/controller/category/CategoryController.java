package com.arbiter.goodsmanager.controller.category;

import com.alibaba.fastjson.JSONObject;
import com.arbiter.goodsmanager.pojo.Category;
import com.arbiter.goodsmanager.result.Result;
import com.arbiter.goodsmanager.service.category.CategoryService;
import com.arbiter.goodsmanager.util.JsonUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {
    final CategoryService categoryService;

    @GetMapping("/all")
    public Result<List<JSONObject>> getAllCategory() {
        List<Category> list = categoryService.list();
        return Result.success(JsonUtil.ListToJsonList(list));
    }
}
