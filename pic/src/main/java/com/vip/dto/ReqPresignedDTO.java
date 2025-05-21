package com.vip.dto;

import lombok.Data;

@Data
public class ReqPresignedDTO {
    private String userUUID;
    private int country;
    private String mmyyyy;

    private String placeId;
    private int nro;

}
