package com.arbiter.goodsmanager.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Good {
    @TableId(type= IdType.AUTO)
    Integer id;
    Integer goodId;
    String name;
    Integer categoryId;
    Integer number;
    Integer soldNumber;
    Integer price;
    Integer status;
    Integer sourceId;
    Integer brandId;
    String img;
}
