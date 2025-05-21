package com.vip.dto;

import lombok.Data;

import java.util.List;
@Data
public class PicCountryDTO {
    List<String> urls;
    String comment;
    int rate;
}
