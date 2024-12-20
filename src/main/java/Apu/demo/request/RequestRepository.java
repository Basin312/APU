package Apu.demo.request;

import java.util.List;

public interface RequestRepository {
    List<UmkRequest> findAll();
    List<UmkRequest> findByName(String nama);
    public void updateApprove(String nama, boolean approve);
    List<UmkRequest> searchByName(String nama);
    List<UmkRequest> sortir(String nama);
}
