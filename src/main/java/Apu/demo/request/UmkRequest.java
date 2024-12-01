package Apu.demo.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UmkRequest {
    private String nama;
    private String namapemilik;
    private String noHp;
    private String alamat;
    private String deskripsi;
    private String logo;
    private boolean approve;
}
