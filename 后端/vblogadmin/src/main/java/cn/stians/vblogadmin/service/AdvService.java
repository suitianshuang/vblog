package cn.stians.vblogadmin.service;

import cn.stians.vblogadmin.mapper.AdvertisingMapper;
import cn.stians.vblogadmin.model.Advertising;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdvService {
    @Autowired
    AdvertisingMapper advertisingMapper;

    public List<Advertising> getAdvList(){
        return  advertisingMapper.findAll();
    }

    @Transactional
    public void deleteAdvById(Integer id) {
        advertisingMapper.deleteById(id);
    }

    public void updateAdv(Advertising advertising) {
         advertisingMapper.saveAndFlush(advertising);
    }

    public Advertising insetAdv(Advertising advertising) {
        return advertisingMapper.save(advertising);
    }
}
