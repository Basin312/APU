package Apu.demo.product;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/umk")
public class productController {
    
    @Autowired
    private ProdukRepository jdbc;

    private final String path="src/main/resources/static/produk_image";
    
    private final String pathEdit="src/main/resources/static";

    @GetMapping("/produk")
    public String halamanProduct(Model model){
        List<Produk> listProdukUmk = jdbc.listProduk(1);
        model.addAttribute("produk", listProdukUmk);
        return "umk/product";
    }

    @GetMapping("/addproduk")
    public String halamanAddProduk(){
        return "umk/addProduk";
    }

    @PostMapping("/add")
    public String addProduk(
        @RequestParam("nama") String nama, 
        @RequestParam("harga") Double harga,
        @RequestParam("deskripsi") String deskripsi,
        @RequestParam("satuan") String satuan,
        @RequestParam("stock") int stock,
        @RequestParam("file")MultipartFile file,
        RedirectAttributes redirectAttributes ){
        
        try{
            Path uploadpath = Paths.get(path);
            Files.createDirectories(uploadpath);

            String filenama = UUID.randomUUID().toString()+"_"+file.getOriginalFilename();

            Path destinationFile = uploadpath.resolve(filenama);

            Files.copy(file.getInputStream(), destinationFile);

            jdbc.addProduk(new Produk(nama, harga, "/produk_image/"+filenama, deskripsi, satuan, 1, stock));
            return "redirect:/umk/produk";
        }catch(Exception e){
            e.printStackTrace();

            return "redirect:/umk/addproduk";
        }
       
    }

    @PostMapping("/editProduk")
    public String halamanEditProduk(@RequestParam("idProduk") int idProduk, Model model){
        Produk produk = jdbc.findProduk(idProduk).get();

        model.addAttribute("nama", produk.getNamaProduk());
        model.addAttribute("harga", produk.getHarga());
        model.addAttribute("satuan", produk.getSatuan());
        model.addAttribute("stock", produk.getStock());
        model.addAttribute("deskripsi", produk.getDeskripsi());
        model.addAttribute("idProduk", produk.getIdProduk());

        return "/umk/edit";
    }

    @PostMapping("/change")
    public String perubahanProduct(
        @RequestParam("nama") String nama, 
        @RequestParam("harga") Double harga,
        @RequestParam("deskripsi") String deskripsi,
        @RequestParam("satuan") String satuan,
        @RequestParam("stock") int stock,
        @RequestParam("file")MultipartFile file,
        @RequestParam("idProduk") int id,
        RedirectAttributes redirectAttributes 
    ){


        try {
            // Retrieve the existing product from the database
            Produk existingProduk = jdbc.findProduk(id).get();
    
            // Check if a new file is uploaded
            if (!file.isEmpty()) {
                // Get the existing file path and delete the old file
                String oldFilePath = existingProduk.getFoto();
                Path oldFile = Paths.get(pathEdit, oldFilePath);
                Files.deleteIfExists(oldFile);
    
                // Save the new file
                String newFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                Path uploadPath = Paths.get(path);
                Files.createDirectories(uploadPath); // Ensure directory exists
                Path newFilePath = uploadPath.resolve(newFileName);
                Files.copy(file.getInputStream(), newFilePath);
    
                // Update the file path in the database
                existingProduk.setFoto("/produk_image/" + newFileName);
            }
    
            // Update the other fields
            existingProduk.setNamaProduk(nama);;
            existingProduk.setHarga(harga);
            existingProduk.setDeskripsi(deskripsi);
            existingProduk.setSatuan(satuan);
            existingProduk.setStock(stock);
    
            // Save the updated product to the database
            jdbc.editProduk(id, existingProduk);
    
            return "redirect:/umk/produk";
    
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Failed to update the product.");
            return "redirect:/umk/editProduk";
        }
    }
}
