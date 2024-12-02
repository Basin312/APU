package Apu.demo.topProduk;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TopProduk {
    private String umk;
    private String produk;
    private Double hargajual;
    private String deskripsi;
    private String satuan;
    private Integer jumlah;
    private Double harga;
}
