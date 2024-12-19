package Apu.demo.loginUmk.Loginuser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserJdbc implements UserRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<User> findByUsername(String username){
        String sql = "SELECT * FROM umk WHERE username =?";
        List<User> results = jdbcTemplate.query(sql, this::mapRowToUser, username);
        return results.size() == 0 ? Optional.empty() : Optional.of(results.get(0));
    }

    private User mapRowToUser(ResultSet rs, int rowNum) throws SQLException {
        return new User(
            rs.getString("username"),
            rs.getString("password")
                
        );
    }
}
