package Apu.demo.Admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("login")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/")
    public String showLoginForm() {
        return "redirect:/login"; // Mengarahkan ke halaman login
    }

    @PostMapping("/admin")
    public String authenticateUser(@RequestParam String username, @RequestParam String password, Model model) {
        List<Admin> admins = adminRepository.findAdmin(username, password);

        if (!admins.isEmpty()) {
            return "redirect:/login"; // Ganti dengan URL dashboard
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login"; // Kembali ke halaman login dengan pesan error
        }
    }
}
