package com.dummy.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dummy")
public class DummyController {

    @GetMapping(path = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getHello() {
        return new ResponseEntity<>("hello word", HttpStatus.OK);
    }
}
