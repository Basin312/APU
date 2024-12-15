package Apu.demo.loginAdmin;


import java.util.List;

public interface LoginRepository{
    List<Login> findAdmin(String username);
    List<Login> findUsers(String username);
}
