package Apu.demo.loginUmk;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UmkController {
    @GetMapping("/register")
    public String halamanRegistrasi(){
        return "/umk/daftarUMK";
    }
}
