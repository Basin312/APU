package Apu.demo.loginUmk.register;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Register {
    @NotNull
    private String nama;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String Repassword;
    @NotNull
    private String noHp;
    @NotNull
    private String namaPemilik;
    @NotNull
    private String alamat;
    @NotNull
    private String deksripsi;
    private String logo;
}
