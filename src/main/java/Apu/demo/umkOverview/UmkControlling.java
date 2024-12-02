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
public class UmkControlling implements ErrorController{
     
    @Autowired
    private UmkRepository jdbc;

    @GetMapping("/umkOverview")
    public String halamanLogin(Model model, @RequestParam(name ="filter",required = false) String filter,@RequestParam(name="sorting",required=false) String type){
        List<UmkOverview> umk = null;
        if(type!=null && !type.isEmpty() ){
            if(filter!=null && !filter.isEmpty()){
                umk = jdbc.findByName(filter);
            }else{
                umk = jdbc.sortir(type);
            }
        }else{
            if(filter!=null && !filter.isEmpty()){
                umk=jdbc.findByName(filter);
            }else{
                umk = jdbc.findAll();
            }
        }
       
       
        model.addAttribute("results", umk);
        model.addAttribute("sorting", type); // Add sorting to the model
        model.addAttribute("filter", filter); // Add filter to the model
        return "/admin/umkOverview";
    }

    
}
