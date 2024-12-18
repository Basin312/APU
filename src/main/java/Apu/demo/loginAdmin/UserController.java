// package Apu.demo.loginAdmin;

// import java.util.List;

// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// @Controller
// @RequestMapping("user")
// public class UserController {
    
//     private LoginRepository loginRepository;

//     @GetMapping("/")
//     public String userLogin() {
//         return "umk/loginuser";
//     }
//     @PostMapping("/")
//     public String authenticateUser(@RequestParam String username, @RequestParam String password, Model model) {
//         List<Login> user = loginRepository.findUsers(username);

//         if (!user.isEmpty()&& user !=null) {
//             if(user.get(0).getPassword().equals(password)){
//                 return "redirect:/user/homepage"; // Ganti dengan URL dashboard
//             }
//             return "/user/loginuser";
//         } else {
//             model.addAttribute("error", "Invalid username or password");
//             return "/user/loginuser"; // Kembali ke halaman login dengan pesan error
//         }
//     }
//     @GetMapping("/homepage")
//     public String userHomepage() {
//         return "umk/homepage";
//     }
// }
