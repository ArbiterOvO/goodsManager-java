package com.arbiter.goodsmanager.controller.category;

import com.arbiter.goodsmanager.result.Result;
import com.arbiter.goodsmanager.service.category.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {
    final CategoryService categoryService;

}
