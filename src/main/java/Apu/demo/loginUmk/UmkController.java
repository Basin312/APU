// package Apu.demo.loginUmk;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// import Apu.demo.loginUmk.register.User;
// import Apu.demo.loginUmk.register.UserService;
// import jakarta.servlet.http.HttpSession;

// @Controller
// @RequestMapping("user")
// public class UmkController{
//     @Autowired
//     private UserService userService;

//     @GetMapping("/")
//     public String userLogin() {
//         return "umk/loginuser";
//     }
//     @PostMapping("/")
//     public String login(@RequestParam String username, @RequestParam String password, HttpSession session){
//         User user = userService.login(username, password);
//         if(user==null){
//             return "redirect:/user/";
//         }
//         session.setAttribute("username", user);
//             return "redirect:/homepage";
//     }
    
//     @GetMapping("/homepage")
//     public String index(HttpSession session){
//         User user = (User) session.getAttribute("username");
//         if(user==null){
//             return "redirect:/user/";
//         }
//         return "umk/homepage";
//     }
// }
