package Apu.demo.loginUmk.register;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("user")
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @Autowired
    private RegisterRepository registerRepository;

    private static final String UPLOAD_DIR = "src/main/resources/static/logo";
    
    @GetMapping("/register")
    public String registerPage() {
        return "umk/daftarUMK";
    }
    @PostMapping("/register")
    public String RegsterData(@RequestParam("namaUsaha") String nama,
                             @RequestParam("namaPemilik") String namaPemilik,
                             @RequestParam("alamat-usaha") String alamat,
                             @RequestParam("deskripsi") String deskripsi,
                             @RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam("noHp") String noHp,
                             @RequestParam("retype-password") String retypePassword,
                             @RequestParam("logo") MultipartFile image){
        try {
            // Ensure the "dokumen" folder exists
            // System.out.println("create path");
            Path uploadPath = Paths.get(UPLOAD_DIR);
            Files.createDirectories(uploadPath);

            // System.out.println("get filename");
            // Generate a unique filename to avoid overwriting files
            String filename =  UUID.randomUUID().toString() + "_" + image.getOriginalFilename();

            // System.out.println("set destination");
            // Resolve the file path in "static/dokumen" folder
            Path destFile = uploadPath.resolve(filename);

            // System.out.println("try save file");
            // Save the file to the target path
            Files.copy(image.getInputStream(), destFile);
            if(!password.equals(retypePassword)){
                return "redirect:/user/register";
            }
            registerRepository.save(new Register(nama, username, password, retypePassword, noHp, namaPemilik, alamat, deskripsi, "/logo/" + filename));
        } catch (Exception e) {
        }
        return "redirect:/user/";
    }

}
