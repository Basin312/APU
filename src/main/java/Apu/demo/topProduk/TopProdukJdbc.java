package Apu.demo.topProduk;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;



@Repository
public class TopProdukJdbc implements TopProdukRepository {
    
    @Autowired
    private JdbcTemplate jdbc;

    private final String query = "SELECT " +
    "    data6.idproduk, namaproduk, harga, foto, deskripsi, satuan, nama, total, terjual " +
    "FROM " +
    "    (SELECT " +
    "        idproduk, namaproduk, harga, foto, data3.deskripsi, satuan, nama, total " +
    "    FROM " +
    "        umk " +
    "    RIGHT JOIN " +
    "        (SELECT " +
    "            produk.idproduk, namaproduk, harga, foto, deskripsi, satuan, id_umk, total " +
    "        FROM " +
    "            produk " +
    "        LEFT JOIN " +
    "            (SELECT " +
    "                idproduk, SUM(totalHarga) AS total " +
    "            FROM " +
    "                (SELECT " +
    "                    produk.idproduk, harga * jumlah AS totalHarga " +
    "                FROM " +
    "                    detailtransaksi " +
    "                JOIN " +
    "                    produk " +
    "                ON produk.idproduk = detailtransaksi.idproduk) AS data1 " +
    "            GROUP BY " +
    "                idproduk) AS data2 " +
    "        ON " +
    "            data2.idproduk = produk.idproduk) AS data3 " +
    "    ON data3.id_umk = umk.idumk) AS data5 " +
    "FULL OUTER JOIN " +
    "    (SELECT " +
    "        idproduk, SUM(jumlah) AS terjual " +
    "    FROM " +
    "        detailtransaksi " +
    "    GROUP BY " +
    "        idproduk) AS data6 " +
    "ON " +
    "    data6.idproduk = data5.idproduk;";

    @Override
    public List<TopProduk> findAll() {
       return jdbc.query(query, this::mapRowToTopProduk);
    }

    private TopProduk mapRowToTopProduk(ResultSet rs, int rowNum) throws SQLException {
        return new TopProduk(
            rs.getString("namaproduk"), 
            rs.getDouble("harga"), 
            rs.getString("foto"), 
            rs.getString("deskripsi"),
            rs.getString("satuan"), 
            rs.getString("nama"), 
            rs.getDouble("total"),
            rs.getInt("terjual"));
    }
    @Override
    public Optional<TopProduk> findbyname() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findbyname'");
    }
    
}
