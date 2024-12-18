package Apu.demo.loginUmk.Loginuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/")
    public String userLogin() {
        return "umk/loginuser";
    }
    
    @PostMapping("/")
    public String Loginuser(@RequestParam String username, @RequestParam String password, HttpSession session, RedirectAttributes redirectAttributes){
        User user = userService.loginUser(username, password);
        // redirectAttributes.addFlashAttribute("error", "password salah atau email tidak terdaftar");
        if(user==null){
            return "redirect:/user/";
        }
            return "redirect:/user/homepage";   //edit loc
    }
    @GetMapping("/homepage")
    public String index(){
        return "umk/homepage";
    }

}
