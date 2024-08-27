package com.arbiter.goodsmanager.service.category.impl;

import com.arbiter.goodsmanager.mapper.CategoryMapper;
import com.arbiter.goodsmanager.pojo.Category;
import com.arbiter.goodsmanager.service.category.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
