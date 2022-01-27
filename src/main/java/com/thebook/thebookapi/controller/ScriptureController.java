package com.thebook.thebookapi.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/scripture")
public class ScriptureController {

    @GetMapping("test")
    public String test() {
        return "Hello from the-book-api";
    }
}
