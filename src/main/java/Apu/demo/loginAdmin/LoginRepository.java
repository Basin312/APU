package Apu.demo.login;


import java.util.List;

public interface LoginRepository{
    List<Login> findAdmin(String username);
    List<Login> findUsers(String username);
}
