package cn.stians.vblog.mapper;

import cn.stians.vblog.model.Advertising;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdvertisingMapper {


    List<Advertising> getAdvertising();
}