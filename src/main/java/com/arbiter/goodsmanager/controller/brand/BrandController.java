package com.arbiter.goodsmanager.controller.brand;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.arbiter.goodsmanager.pojo.Brand;
import com.arbiter.goodsmanager.pojo.Good;
import com.arbiter.goodsmanager.pojo.Source;
import com.arbiter.goodsmanager.result.Result;
import com.arbiter.goodsmanager.service.brand.BrandService;
import com.arbiter.goodsmanager.service.good.GoodService;
import com.arbiter.goodsmanager.service.source.SourceService;
import com.arbiter.goodsmanager.util.JsonUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
    private final GoodService goodService;
    @GetMapping("/all")
    public Result<List<JSONObject>> getAllBrands()
    {
        List<Brand> list = brandService.list();
        List<JSONObject> jsonObjects = new ArrayList<>();
        for (Brand brand : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", brand.getId());
            jsonObject.put("name", brand.getName());
            jsonObject.put("goodNumber",goodService.count(
                    new QueryWrapper<Good>()
                            .eq("brand_id",brand.getId())
            ));
            jsonObject.put("soldNumber",goodService.getMap(
                    new QueryWrapper<Good>()
                            .eq("brand_id",brand.getId())
                            .select("ifnull(sum(sold_number),0) as sold_number")
            ).get("sold_number"));
            jsonObject.put("description",brand.getDescription());
            jsonObjects.add(jsonObject);
        }
        return Result.success(jsonObjects);
    }

}
