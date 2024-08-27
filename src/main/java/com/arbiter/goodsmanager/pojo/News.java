package com.arbiter.goodsmanager.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class News {
    Integer id;
    String title;
    String content;
    String img;
    Date createTime;
}
