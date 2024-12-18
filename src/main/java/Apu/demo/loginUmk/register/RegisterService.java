package Apu.demo.loginUmk.register;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    @Autowired
    private RegisterRepository registerRepository; 

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public boolean register(Register register ){
        try{

            Optional<Register> existingPasien = registerRepository.findByUsername(register.getUsername());
            if(existingPasien.isPresent()){
                System.out.println("Username already exists: " + register.getUsername());
                return false;
            }

            register.setPassword(passwordEncoder.encode(register.getPassword()));
            System.out.println("password encrption");
            registerRepository.save(register);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
