package com.squeaker.games.squeakerapi.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "admin")
public class AdminController {

    @GetMapping(path = "ping", produces = MediaType.TEXT_PLAIN_VALUE)
    public @ResponseBody String healthCheck() {
        return "pong";
    }
}
