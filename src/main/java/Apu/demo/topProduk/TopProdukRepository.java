package Apu.demo.topProduk;

import java.util.List;
import java.util.Optional;

public interface TopProdukRepository {
    List<TopProduk> findAll();
    Optional<TopProduk> findbyname();
}
