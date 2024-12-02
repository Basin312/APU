package Apu.demo.umkOverview;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UmkOverview {
    private Integer idUmk;
    private String logo;
    private String nama;
    private String deskripsi;
    private Double totaltransaksi;
}
