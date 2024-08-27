package com.arbiter.goodsmanager.service.brand.impl;

import com.arbiter.goodsmanager.mapper.BrandMapper;
import com.arbiter.goodsmanager.pojo.Brand;
import com.arbiter.goodsmanager.service.brand.BrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper,Brand> implements BrandService {

}
