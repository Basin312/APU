package Apu.demo.request;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcReQuest implements RequestRepository {

    @Autowired
    private JdbcTemplate jdbc;

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
}
