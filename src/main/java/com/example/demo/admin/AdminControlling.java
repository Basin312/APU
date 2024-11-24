package com.example.demo.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminControlling {
     
    @GetMapping("/admin")
    public String showAdmin(){
        return "admin/topProduk";
    }
    @GetMapping("/topProduk")
    public String showTopProduk(){
        return "admin/topProduk";
    }

    @GetMapping("/umkOverview")
    public String showUmkOverview() {
        return "admin/umkOverview";
    }

    @GetMapping("/index")
    public String showindex() {
        return "index";
    }

    @GetMapping("/topUmk")
    public String showTopUmk() {
        return "admin/TopUMK";
    }
}
