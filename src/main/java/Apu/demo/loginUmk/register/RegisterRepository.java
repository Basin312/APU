package Apu.demo.loginUmk.register;

import java.util.Optional;

public interface RegisterRepository {
    void save(Register user) throws Exception;
    Optional<Register> findByUsername(String username);
}
