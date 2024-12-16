package Apu.demo.topProduk;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TopProduk {
    private String namaProduk;
    private Double harga;
    private String foto;
    private String deskripsi;
    private String satuan;
    private String namaUmk;
    private Double total;
    private Integer terjual;
}
