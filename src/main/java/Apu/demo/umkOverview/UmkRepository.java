package Apu.demo.umkOverview;

import java.util.List;

public interface UmkRepository {
     List<UmkOverview> findAll();
     List<UmkOverview> sortir(String type);
     List<UmkOverview> findByName(String nama);
     
}
