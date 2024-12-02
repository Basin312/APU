package Apu.demo.request;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/admin")
public class RequestControlling {
    
    @Autowired
    private RequestRepository jdbc;
    
    @GetMapping("/request")
    public String halamanRequest(Model model, @RequestParam(name="sorting",required = false) String name, @RequestParam(name="filter", required = false) String filter){

        List<UmkRequest> list = null;
        if (filter != null && !filter.isEmpty()) {
            list = jdbc.searchByName(filter);
        } else if (name != null && !name.isEmpty()) {
            list = jdbc.sortir(name);
        } else {
            list = jdbc.findAll();
        }
        
        model.addAttribute("results", list);
        return "/admin/request";
    }

    @GetMapping("/request/detail")
    public String halamanDetail(Model model,@RequestParam String nama){
        List<UmkRequest> user = jdbc.findByName(nama);
        if(user!=null){
            model.addAttribute("umkRequest", user.get(0));
            return "/admin/request-detail";
        }else{
            return "redirect:/admin/request";
        }
    }

    @PostMapping("/request/approve")
    public String approveRequest(@RequestParam(required = true) String nama) {
        jdbc.updateApprove(nama, true); // Update the `approve` field to true
        return "redirect:/admin/request"; // Redirect back to the detail page
    }

}