package com.arbiter.goodsmanager.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchGoodDTO {
    Integer id;
    String searchContent;
    Integer categoryId;
    Integer status;
}
