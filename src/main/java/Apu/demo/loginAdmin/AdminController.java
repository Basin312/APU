package Apu.demo.loginAdmin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/")
    public String showLoginForm() {
        return "/admin/login"; // Mengarahkan ke halaman login
    }

    @PostMapping("/")
    public String authenticateUser(@RequestParam String username, @RequestParam String password, Model model) {
        List<Admin> admins = adminRepository.findAdmin(username);

        if (!admins.isEmpty()&& admins !=null) {
            if(admins.get(0).getPassword().equals(password)){
                return "redirect:/admin/umkOverview"; // Ganti dengan URL dashboard
            }
            return "/admin/login";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "/admin/login"; // Kembali ke halaman login dengan pesan error
        }
    }
}