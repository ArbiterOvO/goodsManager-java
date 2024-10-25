package com.arbiter.goodsmanager.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UploadImageDTO {
    Integer goodId;
    String type;
}
