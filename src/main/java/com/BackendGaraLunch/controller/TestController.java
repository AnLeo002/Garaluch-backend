package com.BackendGaraLunch.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
    @GetMapping("/get")
    public String helloGet(){
        return "Hello World - GET";
    }
    @PostMapping("/post")
    //@PreAuthorize("hasAuthority('CREATE') or hasAuthority('READ')")
    public String helloPost(){
        return "Hello World - POST";
    }
    @PutMapping("/put")
    //@PreAuthorize("hasAuthority('UPDATE')")
    public String helloPut(){
        return "Hello World - PUT";
    }
    @DeleteMapping("/delete")
    public String helloDelete(){
        return "Hello World - DELETE";
    }
    @PatchMapping("/patch")
    public String helloPatch(){
        return "Hello World - PATCH";
    }
}
