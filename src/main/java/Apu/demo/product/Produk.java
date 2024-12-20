package Apu.demo.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor 
@NoArgsConstructor
@Data
public class Produk {
    private Integer idProduk;
    private String namaProduk;
    private Double harga;
    private String foto;
    private String deskripsi;
    private String satuan;
    private int idUmk;
    private int stock;
    
    public Produk(String namaProduk, Double harga, String foto, String deskripsi, String satuan, int idUmk,int stock) {
        this.namaProduk = namaProduk;
        this.harga = harga;
        this.foto = foto;
        this.deskripsi = deskripsi;
        this.satuan = satuan;
        this.idUmk = idUmk;
        this.stock = stock;
    }
}
