package Apu.demo.request;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcReQuest implements RequestRepository{

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public List<UmkRequest> findAll() {
       String sql ="select nama, namapemilik, nohp, alamat, deskripsi, logo, approveumk FROM umk WHERE approveumk = false";

       return jdbc.query(sql,this::mapRowToUmkRequest);
    }

    private UmkRequest mapRowToUmkRequest(ResultSet rs, int rowNum) throws SQLException {
        return new UmkRequest(
            rs.getString("nama"),
            rs.getString("namapemilik"),
            rs.getString("nohp"),
            rs.getString("alamat"),
            rs.getString("deskripsi"),
            rs.getString("logo"),
            rs.getBoolean("approveumk")
        );
    }

    @Override
    public List<UmkRequest> findByName(String nama) {
        String sql = "select nama, namapemilik, nohp, alamat, deskripsi, logo, approveumk from umk WHERE approveumk = false AND nama = ?";
        List<UmkRequest> user = jdbc.query(sql, this::mapRowToUmkRequest,nama);
        return user.get(0) !=null ? user:null;
    }

    @Override
    public void updateApprove(String nama, boolean approve) {
        String sql = "UPDATE umk SET approveumk = ? WHERE nama = ?";
        jdbc.update(sql, approve, nama);
    }

    
}
