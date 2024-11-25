package Apu.demo.umkOverview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcUmkOverview implements UmkRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    
    @Override
    public List<UmkOverview> findAll() {
        // String url = "select\n" + //
        //                 "\tumk.idumk, nama, deskripsi, logo, transaksiumk\n" + //
        //                 "from\n" + //
        //                 "\t\tumk\n" + //
        //                 "\tfull outer join\n" + //
        //                 "\t\t(select\n" + //
        //                 "\t\t\tidUmk, sum(totalBiayaTransaksi) as transaksiUmk\n" + //
        //                 "\t\tfrom\n" + //
        //                 "\t\t\ttransaksi\n" + //
        //                 "\t\t\tjoin\n" + //
        //                 "\t\t\t\t(select\n" + //
        //                 "\t\t\t\t\tidtransaksi, sum(totalBiaya) as totalBiayaTransaksi\n" + //
        //                 "\t\t\t\tFrom\n" + //
        //                 "\t\t\t\t\t(select\n" + //
        //                 "\t\t\t\t\t\tidtransaksi, jumlah*total as totalBiaya\n" + //
        //                 "\t\t\t\t\tfrom\n" + //
        //                 "\t\t\t\t\t\tdetailtransaksi) as data1\n" + //
        //                 "\t\t\t\tgroup by\n" + //
        //                 "\t\t\t\t\tidtransaksi)as data2\n" + //
        //                 "\t\t\ton data2.idtransaksi = transaksi.idtransaksi\n" + //
        //                 "\t\t\tgroup by\n" + //
        //                 "\t\t\t\tidUmk)as data3\n" + //
        //                 "\t\ton data3.idumk = umk.idumk";
        String sql = "select nama, logo, deskripsi from umk ";
       return this.jdbcTemplate.query(sql, this::mapRow);
    }

    public UmkOverview mapRow(ResultSet result, int rownum)throws SQLException{
        return new UmkOverview(
            // result.getInt("idUmk"),
            result.getString("logo"),
            result.getString("nama"),
            result.getString("deskripsi")
            // result.getDouble("transaksiumk")
        );
    }

    @Override
    public List<UmkOverview> sortTopTransaksi() {
        String sql = "select\n" + //
                    "\tumk.idumk, nama, deskripsi, logo, transaksiumk\n" + //
                    "from\n" + //
                    "\t\tumk\n" + //
                    "\tfull outer join\n" + //
                    "\t\t(select\n" + //
                    "\t\t\tidUmk, sum(totalBiayaTransaksi) as transaksiUmk\n" + //
                    "\t\tfrom\n" + //
                    "\t\t\ttransaksi\n" + //
                    "\t\t\tjoin\n" + //
                    "\t\t\t\t(select\n" + //
                    "\t\t\t\t\tidtransaksi, sum(totalBiaya) as totalBiayaTransaksi\n" + //
                    "\t\t\t\tFrom\n" + //
                    "\t\t\t\t\t(select\n" + //
                    "\t\t\t\t\t\tidtransaksi, jumlah*total as totalBiaya\n" + //
                    "\t\t\t\t\tfrom\n" + //
                    "\t\t\t\t\t\tdetailtransaksi) as data1\n" + //
                    "\t\t\t\tgroup by\n" + //
                    "\t\t\t\t\tidtransaksi)as data2\n" + //
                    "\t\t\ton data2.idtransaksi = transaksi.idtransaksi\n" + //
                    "\t\t\tgroup by\n" + //
                    "\t\t\t\tidUmk)as data3\n" + //
                    "\t\ton data3.idumk = umk.idumk\n" + //
                    "ORDER BY transaksiumk DESC NULLS LAST;";
        
        return this.jdbcTemplate.query(sql, this::mapRow);
    
    }

    @Override
    public List<UmkOverview> findByName(String nama) {
        String sql = "select nama, logo, deskripsi from umk where nama ilike ? ";
        return this.jdbcTemplate.query(sql, this::mapRow, nama);
    }

}