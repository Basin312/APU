package Apu.demo.umkOverview;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminControlling implements ErrorController{
     
    @Autowired
    private UmkRepository jdbc;

    @GetMapping("/")
    public String halamanLogin(Model model, @RequestParam(name ="filter",required = false) String filter){
        List<UmkOverview> umk;
        if(filter!=null && !filter.isEmpty()){
            umk=jdbc.findByName(filter);
        }else{
            umk = jdbc.findAll();
        }
       
        model.addAttribute("results", umk);
        return "/admin/umkOverview";
    }

    
}
