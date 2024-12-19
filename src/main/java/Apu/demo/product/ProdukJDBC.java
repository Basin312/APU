package Apu.demo.product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProdukJDBC implements ProdukRepository{

    @Autowired
    private JdbcTemplate jdbc;

    private String sql= 
            """
            select
                *
            from
                produk
            """;;

    @Override
    public List<Produk> listProduk(int idUmk) {
        String query = sql + 
        """
        where
            id_umk = ?
        """;
        
        return jdbc.query(query, this::mapRowToProduk,idUmk);
    }

    @Override
    public boolean addProduk(Produk produk) {
       String sql = "Insert into produk(namaproduk, harga, foto, deskripsi, satuan, id_umk, stock) VALUES (?,?,?,?,?,?,?)";

       try{
            jdbc.update(sql, produk.getNamaProduk(), produk.getHarga(), produk.getFoto(), produk.getDeskripsi(), produk.getSatuan(), produk.getIdUmk(), produk.getStock());
            return true;
       }catch (Exception e){
            e.printStackTrace();
            return false;
       }
    }
    
    public Produk mapRowToProduk(ResultSet resultSet, int rownum) throws SQLException{
        return new Produk(
            resultSet.getInt("idproduk"), 
            resultSet.getString("namaproduk"), 
            resultSet.getDouble("harga"), 
            resultSet.getString("foto"), 
            resultSet.getString("deskripsi"), 
            resultSet.getString("satuan"), 
            resultSet.getInt("id_umk"), 
            resultSet.getInt("stock"));
    }

    @Override
    public Optional<Produk> findProduk(int idProduk) {
        String query = sql+
            """
                where
                    idproduk = ?
                    """;

        List<Produk> result = jdbc.query(query,this::mapRowToProduk, idProduk);

        return result.isEmpty()? Optional.empty() : Optional.of(result.get(0));
    }

    @Override
    public boolean editProduk(int idProduk, Produk produk) {
        String sql = "UPDATE produk SET namaproduk = ?, harga = ?, foto = ?, deskripsi = ?, satuan = ?, stock = ? WHERE idproduk = ?";
        try{
            jdbc.update(sql,
                produk.getNamaProduk(),
                produk.getHarga(),
                produk.getFoto(),
                produk.getDeskripsi(),
                produk.getSatuan(),
                produk.getStock(),
                produk.getIdProduk());
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
