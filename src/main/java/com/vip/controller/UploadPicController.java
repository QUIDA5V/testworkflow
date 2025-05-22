package com.vip.controller;

import com.vip.dto.PresignDTO;
import com.vip.dto.ReqPresignedDTO;
import com.vip.service.UploadPicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/vip")
public class UploadPicController {
    @Autowired
    private UploadPicService uploadPicService;

    @GetMapping(path = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCardAddress() {
        return new ResponseEntity<>("hello word", HttpStatus.OK);
    }

    @PostMapping(path = "/upload", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PresignDTO> cardRegistrationThales(@RequestBody ReqPresignedDTO reqPresignedDTO) throws Exception {
        //log.info(LOG_MESSAGE_CARD_UUID_START, CLASS_NAME, "cardRegistrationThales", cardUUID);
        PresignDTO response = uploadPicService.getPresignedURL(reqPresignedDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
        //log.info(LOG_MESSAGE_CARD_UUID_END, CLASS_NAME, "cardRegistrationThales", cardUUID);
        //return new ResponseEntity<>(response, HttpStatus.valueOf(response.getHttpCode()));
    }


}
