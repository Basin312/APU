package Apu.demo.loginUmk.Loginuser;

import java.util.Optional;

public interface UserRepository{
    Optional<User> findByUsername(String username);
}
