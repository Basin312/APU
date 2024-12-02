package Apu.demo.topProduk;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TopProdukJdbc implements TopProdukRepository {

    @Autowired
    private JdbcTemplate jdbc;  // Pastikan Anda menggunakan jdbc yang sudah di-autowired

    private final String query = "SELECT umk.nama AS umk, produk.namaproduk AS produk, SUM(detailtransaksi.jumlah)*produk.harga AS hargajual, produk.deskripsi AS Deskripsi, produk.satuan AS satuan, SUM(detailtransaksi.jumlah) AS jumlah, produk.harga AS harga  FROM umk INNER JOIN produk ON umk.idumk=produk.id_umk INNER JOIN detailtransaksi ON detailtransaksi.idproduk=produk.idproduk GROUP BY produk.namaproduk, umk.nama, produk.harga, produk.deskripsi, produk.satuan ORDER BY jumlah DESC";

    @Override
    public List<TopProduk> findAll() {
        return jdbc.query(query, this::mapRowToTopProduk);
    }

    private TopProduk mapRowToTopProduk(ResultSet rs, int rowNum) throws SQLException {
        return new TopProduk(
            rs.getString("umk"),
            rs.getString("produk"),        
            rs.getDouble("hargajual"),                    
            rs.getString("Deskripsi"),     
            rs.getString("satuan"),        
            rs.getInt("jumlah"),
            rs.getDouble("harga")            
        );
    }

    @Override
    public List<TopProduk> findbyname(String nama) {
        String sql = "SELECT umk.nama AS umk, produk.namaproduk AS produk, SUM(detailtransaksi.jumlah)*produk.harga AS hargajual, produk.deskripsi AS Deskripsi, produk.satuan AS satuan, SUM(detailtransaksi.jumlah) AS jumlah, produk.harga AS harga  FROM umk INNER JOIN produk ON umk.idumk=produk.id_umk INNER JOIN detailtransaksi ON detailtransaksi.idproduk=produk.idproduk WHERE umk.nama like ? GROUP BY produk.namaproduk, umk.nama, produk.harga, produk.deskripsi, produk.satuan ORDER BY jumlah DESC";  // Filter berdasarkan nama produk

        // Menggunakan jdbc.query untuk mengeksekusi query dan memetakan hasilnya ke dalam objek TopProduk
        return jdbc.query(sql, this::mapRowToTopProduk, "%" + nama + "%");  // Menambahkan tanda % agar bisa melakukan pencarian bagian nama
    }
}
