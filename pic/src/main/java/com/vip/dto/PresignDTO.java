package com.vip.dto;

import lombok.Data;

import java.util.List;

@Data
public class PresignDTO {
    List<String> urls;
    String time;
}
