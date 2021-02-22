package cn.stians.vblogadmin.mapper;

import cn.stians.vblogadmin.model.Advertising;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvertisingMapper extends JpaRepository<Advertising,Integer> {
//    List<Advertising> getAdvertising();

}