package Apu.demo.login;


import java.util.List;

public interface AdminRepository{
    List<Admin> findAdmin(String username);
}
