package Apu.demo.product;

import java.util.List;
import java.util.Optional;

public interface ProdukRepository {
    List<Produk> listProduk(int idUmk);
    boolean addProduk(Produk produk);
    Optional<Produk> findProduk(int idProduk);
    boolean editProduk(int idProduk, Produk produk);
}
