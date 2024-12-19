package Apu.demo.request;

import java.util.List;

public interface RequestRepository {
    List<UmkRequest> findAll();
    List<UmkRequest> findByName(String nama);
    public void updateApprove(String nama, boolean approve);
<<<<<<< HEAD
=======
    List<UmkRequest> searchByName(String nama);
    List<UmkRequest> sortir(String nama);
>>>>>>> 1c22eda28d6da27508d7fd1176ea820e399106df
}
