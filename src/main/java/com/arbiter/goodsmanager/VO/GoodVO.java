package com.arbiter.goodsmanager.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GoodVO {
    private Integer id;
    private Integer goodId;
    private String name;
    private String category;
    private Integer number;
    private Integer soldNumber;
    private Integer price;
    private String status;
    private String source;
    private String brand;
    private String img;
}
