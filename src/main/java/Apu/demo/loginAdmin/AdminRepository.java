package Apu.demo.loginAdmin;


import java.util.List;

public interface AdminRepository{
    List<Admin> findAdmin(String username);
}
