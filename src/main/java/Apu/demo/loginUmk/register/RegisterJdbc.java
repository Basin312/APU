package Apu.demo.loginUmk.register;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RegisterJdbc implements RegisterRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public void save(Register user) throws Exception {
        String sql = "INSERT INTO umk (nama, password, username, noHp ,namaPemilik, alamat, deskripsi, logo) VALUES (?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, user.getNama(), user.getPassword(), user.getUsername(), user.getNoHp(),user.getNamaPemilik(), user.getAlamat(), user.getDeksripsi(), user.getLogo());
    }

    public Optional<Register> findByUsername(String username) {
        String sql ="SELECT * FROM pasien WHERE email = ? ";
        List<Register> result = jdbcTemplate.query(sql, this::mapToUser, username);
        return result.size() == 0 ? Optional.empty() : Optional.of(result.get(0));
    }
    public Register mapToUser(ResultSet resultSet, int rowNum) throws SQLException{
            return new Register(
            resultSet.getString("nama"),
            resultSet.getString("username"),
            resultSet.getString("password"),
            resultSet.getString("password"),
            resultSet.getString("namaPemilik"),
            resultSet.getString("deskripsi"),
            resultSet.getString("noHp"),
            resultSet.getString("alamat"),
            resultSet.getString("logo")
            );    
    }
}
