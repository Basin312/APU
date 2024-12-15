package Apu.demo.login;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginJdbc implements LoginRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Login> findAdmin(String username) {
        // Query yang benar dengan parameter
        String sql = "SELECT username, password FROM admin WHERE username = ?";
        // Menggunakan query dengan parameter username dan password
        return jdbcTemplate.query(sql, this::mapRowToAdmin,username);
    }

    private Login mapRowToAdmin(ResultSet rs, int rowNum) throws SQLException {
        return new Login(
            rs.getString("username"),
            rs.getString("password")
        );
    }
    @Override
    public List<Login> findUsers(String username) {
        // Query yang benar dengan parameter
        String sql = "SELECT username, password FROM umk WHERE username = ?";
        // Menggunakan query dengan parameter username dan password
        return jdbcTemplate.query(sql, this::mapRowToUsers,username);
    }

    private Login mapRowToUsers(ResultSet rs, int rowNum) throws SQLException {
        return new Login(
            rs.getString("username"),
            rs.getString("password")
        );
    }
}
