package cn.stians.vblog.service;

import cn.stians.vblog.mapper.AdvertisingMapper;
import cn.stians.vblog.model.Advertising;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertisingService {
    @Autowired
    AdvertisingMapper advertisingMapper;
    public List<Advertising> getAdvertising(){
        return advertisingMapper.getAdvertising();
    }
}
