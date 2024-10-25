package com.arbiter.goodsmanager.controller.good;

import com.alibaba.fastjson.JSONObject;
import com.arbiter.goodsmanager.DTO.SearchGoodDTO;
import com.arbiter.goodsmanager.DTO.UploadImageDTO;
import com.arbiter.goodsmanager.VO.GoodVO;
import com.arbiter.goodsmanager.configuration.UploadConfig;
import com.arbiter.goodsmanager.exception.FileUploadException;
import com.arbiter.goodsmanager.mapper.GoodMapper;
import com.arbiter.goodsmanager.pojo.Good;
import com.arbiter.goodsmanager.result.Result;
import com.arbiter.goodsmanager.service.brand.BrandService;
import com.arbiter.goodsmanager.service.category.CategoryService;
import com.arbiter.goodsmanager.service.file.FileService;
import com.arbiter.goodsmanager.service.good.GoodService;
import com.arbiter.goodsmanager.service.source.SourceService;
import com.arbiter.goodsmanager.util.JsonUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/good")
public class GoodController {

    private final GoodService goodService;
    private final BrandService brandService;
    private final CategoryService categoryService;
    private final SourceService sourceService;
    private final FileService fileService;
    @GetMapping("/all")
    public Result<List<JSONObject>> getAllGoods()
    {
        List<Good> goods = goodService.getAllGoods();
        List<GoodVO> goodVOList = toGoodVOList(goods);

        return Result.success(JsonUtil.ListToJsonList(goodVOList));
    }

    @GetMapping("/{id}")
    public Result<JSONObject> getGoodById(@PathVariable int id)
    {
        Good byId = goodService.getById(id);

        return Result.success(JsonUtil.toJson(byId));
    }

    @PostMapping("/add")
    public Result<String> addNewGoods(@RequestBody Good good)
    {
        goodService.save(good);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result<String> deleteOneGoods(@PathVariable int id)
    {
        goodService.removeById(id);
        return Result.success();
    }

    @PostMapping("/edit")
    public Result<String> updateOneGoods(@RequestBody Good good)
    {
        goodService.updateById(good);
        return Result.success();
    }

    @PostMapping("/search")
    public Result<List<JSONObject>> searchGood(@RequestBody SearchGoodDTO searchGoodDTO)
    {
        System.out.println("searchGoodDTO = " + searchGoodDTO);

        List<Good> goods = goodService.serchGood(searchGoodDTO);
        List<GoodVO> goodVOList = toGoodVOList(goods);
        return Result.success(JsonUtil.ListToJsonList(goodVOList));
    }
    /*上传图片*/
    @PostMapping("/upload/imgs")
    public Result<String> uploadImage(@RequestParam("file") MultipartFile file, UploadImageDTO imageDTO) throws FileUploadException {

        String fileExt = this.getExtName(file.getOriginalFilename());

        String fileName = imageDTO.getGoodId() + "." + fileExt;
        try {
            fileService.upload(file, fileName);
        }
        catch (IOException e) {
            throw new FileUploadException("文件上传失败");
        }
        String filePath = "/" + fileName;
        String url = UploadConfig.reUrl + filePath;
        System.out.println("返回地址:"+url);
        //一次性上传多张的时候最好加上这个
        //Thread.sleep(500);
        return Result.success(url);
    }

    //Good转GoodVO
    public List<GoodVO> toGoodVOList(List<Good> goods) {
        List<GoodVO> goodVOs = new ArrayList<>();
        for (Good good : goods) {
            GoodVO goodVO = new GoodVO();
            goodVO.setId(good.getId());
            goodVO.setGoodId(good.getGoodId());
            goodVO.setName(good.getName());
            goodVO.setCategory(categoryService.getById(good.getCategoryId()).getName());
            goodVO.setNumber(good.getNumber());
            goodVO.setSoldNumber(good.getSoldNumber());
            goodVO.setPrice(good.getPrice());
            goodVO.setStatus(good.getStatus()==1?"在售":"下架");
            goodVO.setSource(sourceService.getById(good.getSourceId()).getName());
            goodVO.setBrand(brandService.getById(good.getBrandId()).getName());
            goodVO.setImg(good.getImg());
            goodVOs.add(goodVO);
        }
        return goodVOs;
    }

    private String getExtName(String fileName) {
        String fullName = fileName.toLowerCase();
        int dotIndex = fullName.lastIndexOf(".");
        return dotIndex == -1 ? "" : fileName.substring(dotIndex + 1);
    }
}
