package Apu.demo.topProduk;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
@RequestMapping("/admin")
public class TopProdukControlling {
    
    @Autowired
    private TopProdukRepository jdbc;

    @GetMapping("/topProduk")
    public String halamanProduk(Model model, @RequestParam(name = "filter", required = false) String filter) {
        List<TopProduk> umk = null; 

        if (filter != null && !filter.isEmpty()) {
            
            umk = jdbc.findbyname(filter); 
        } else {
            
            umk = jdbc.findAll();  
        }

        model.addAttribute("results", umk);  
        return "/admin/topProduk"; 
}

}
