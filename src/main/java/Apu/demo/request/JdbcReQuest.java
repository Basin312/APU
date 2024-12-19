package Apu.demo.request;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
<<<<<<< HEAD
public class JdbcReQuest implements RequestRepository{
=======
public class JdbcReQuest implements RequestRepository {
>>>>>>> 1c22eda28d6da27508d7fd1176ea820e399106df

    @Autowired
    private JdbcTemplate jdbc;

<<<<<<< HEAD
    @Override
    public List<UmkRequest> findAll() {
       String sql ="select nama, namapemilik, nohp, alamat, deskripsi, logo, approveumk FROM umk WHERE approveumk = false";

       return jdbc.query(sql,this::mapRowToUmkRequest);
=======
    private static final String BASE_QUERY = "SELECT nama, namapemilik, nohp, alamat, deskripsi, logo, approveumk FROM umk WHERE approveumk = false";

    @Override
    public List<UmkRequest> findAll() {
        return jdbc.query(BASE_QUERY, this::mapRowToUmkRequest);
    }

    @Override
    public List<UmkRequest> findByName(String nama) {
        String sql = BASE_QUERY + " AND nama = ?";
        return jdbc.query(sql, this::mapRowToUmkRequest, nama);
    }

    @Override
    public void updateApprove(String nama, boolean approve) {
        String sql = "UPDATE umk SET approveumk = ? WHERE nama = ?";
        jdbc.update(sql, approve, nama);
    }

    @Override
    public List<UmkRequest> searchByName(String nama) {
        String sql = BASE_QUERY + " AND nama ILIKE ?";
        return jdbc.query(sql, this::mapRowToUmkRequest,  nama);
    }

    @Override
    public List<UmkRequest> sortir(String sortDirection) {
        String sql;
        if ("asc".equalsIgnoreCase(sortDirection)) {
            sql = BASE_QUERY + " ORDER BY nama ASC";
        } else if ("desc".equalsIgnoreCase(sortDirection)) {
            sql = BASE_QUERY + " ORDER BY nama DESC";
        } else {
            throw new IllegalArgumentException("Invalid sorting order: " + sortDirection);
        }
        return jdbc.query(sql, this::mapRowToUmkRequest);
>>>>>>> 1c22eda28d6da27508d7fd1176ea820e399106df
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
<<<<<<< HEAD

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

    
=======
>>>>>>> 1c22eda28d6da27508d7fd1176ea820e399106df
}
