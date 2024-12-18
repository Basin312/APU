package Apu.demo.loginUmk.Loginuser;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService{

    @Autowired
    private UserRepository userRepository;
    
    public User loginUser(String username, String password){
        try{
            Optional<User> user = userRepository.findByUsername(username);
            // if(passwordEncoder.matches(password, pasien.get().getPassword())){
            //     return  pasien.get();
            // }
            if(user.get().getPassword().equals(password)){
                return  user.get();
            }
            return null;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}