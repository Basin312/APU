package Apu.demo.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AdminJdbc implements AdminRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Admin> findAdmin(String username, String password) {
        // Query yang benar dengan parameter
        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
        // Menggunakan query dengan parameter username dan password
        return jdbcTemplate.query(sql, this::mapRowToAdmin,username,password);
    }

    private Admin mapRowToAdmin(ResultSet rs, int rowNum) throws SQLException {
        return new Admin(
            rs.getString("username"),
            rs.getString("password")
        );
    }
}
