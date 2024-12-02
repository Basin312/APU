package Apu.demo.umkOverview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@Repository
public class JdbcUmkOverview implements UmkRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    
    @Override
    public List<UmkOverview> findAll() {
        String query = "SELECT "
        + "umk.idumk, nama, deskripsi, logo, transaksiumk, approveumk "
        + "FROM "
        + "umk "
        + "FULL OUTER JOIN "
        + "(SELECT "
        + "idumk, SUM(totaltransaksi) AS transaksiumk "
        + "FROM "
        + "(SELECT "
        + "idumk, transaksi.idtransaksi, totalTransaksi "
        + "FROM "
        + "transaksi "
        + "JOIN "
        + "(SELECT "
        + "idtransaksi, SUM(totalbelanja) AS totalTransaksi "
        + "FROM "
        + "(SELECT "
        + "idtransaksi, jumlah * harga AS totalbelanja "
        + "FROM "
        + "detailtransaksi "
        + "JOIN "
        + "produk "
        + "ON produk.idproduk = detailtransaksi.idproduk) AS belanja "
        + "GROUP BY "
        + "idtransaksi) AS detail "
        + "ON detail.idtransaksi = transaksi.idtransaksi) AS transaksi_detail "
        + "GROUP BY "
        + "idumk) AS transaksiumk "
        + "ON transaksiumk.idumk = umk.idumk "
        + "WHERE "
        + "approveumk = true";

       return this.jdbcTemplate.query(query, this::mapRow);
    }

    public UmkOverview mapRow(ResultSet result, int rownum)throws SQLException{
        return new UmkOverview(
            result.getInt("idUmk"),
            result.getString("logo"),
            result.getString("nama"),
            result.getString("deskripsi"),
            result.getDouble("transaksiumk")
        );
    }

    @Override
    public List<UmkOverview> sortir(String type) {
        // Validate sorting type
        Set<String> allowedSortColumns = Set.of("nama", "transaksiumk");
        if (!allowedSortColumns.contains(type.toLowerCase())) {
            throw new IllegalArgumentException("Invalid sorting type");
        }

        String sql = "SELECT "
                + "umk.idumk, nama, deskripsi, logo, transaksiumk, approveumk "
                + "FROM "
                + "umk "
                + "FULL OUTER JOIN "
                + "(SELECT "
                + "idumk, SUM(totaltransaksi) AS transaksiumk "
                + "FROM "
                + "(SELECT "
                + "idumk, transaksi.idtransaksi, totalTransaksi "
                + "FROM "
                + "transaksi "
                + "JOIN "
                + "(SELECT "
                + "idtransaksi, SUM(totalbelanja) AS totalTransaksi "
                + "FROM "
                + "(SELECT "
                + "idtransaksi, jumlah * harga AS totalbelanja "
                + "FROM "
                + "detailtransaksi "
                + "JOIN "
                + "produk "
                + "ON produk.idproduk = detailtransaksi.idproduk) AS belanja "
                + "GROUP BY "
                + "idtransaksi) AS detail "
                + "ON detail.idtransaksi = transaksi.idtransaksi) AS transaksi_detail "
                + "GROUP BY "
                + "idumk) AS transaksiumk "
                + "ON transaksiumk.idumk = umk.idumk "
                + "WHERE "
                + "approveumk = true "
                + "ORDER BY " + type + (type.equals("transaksiumk") ? " DESC NULLS LAST" : "");

        return this.jdbcTemplate.query(sql, this::mapRow);
    }

    

    @Override
    public List<UmkOverview> findByName(String nama) {
        String sql = "SELECT "
        + "umk.idumk, nama, deskripsi, logo, transaksiumk, approveumk "
        + "FROM "
        + "umk "
        + "FULL OUTER JOIN "
        + "(SELECT "
        + "idumk, SUM(totaltransaksi) AS transaksiumk "
        + "FROM "
        + "(SELECT "
        + "idumk, transaksi.idtransaksi, totalTransaksi "
        + "FROM "
        + "transaksi "
        + "JOIN "
        + "(SELECT "
        + "idtransaksi, SUM(totalbelanja) AS totalTransaksi "
        + "FROM "
        + "(SELECT "
        + "idtransaksi, jumlah * harga AS totalbelanja "
        + "FROM "
        + "detailtransaksi "
        + "JOIN "
        + "produk "
        + "ON produk.idproduk = detailtransaksi.idproduk) AS belanja "
        + "GROUP BY "
        + "idtransaksi) AS detail "
        + "ON detail.idtransaksi = transaksi.idtransaksi) AS transaksi_detail "
        + "GROUP BY "
        + "idumk) AS transaksiumk "
        + "ON transaksiumk.idumk = umk.idumk "
        + "WHERE "
        + "approveumk = true "
        + "AND nama ilike ?";
        return this.jdbcTemplate.query(sql, this::mapRow, nama);
    }

   

   
}