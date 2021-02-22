package cn.stians.vblog.controller;

import cn.stians.vblog.service.AdvertisingService;
import cn.stians.vblog.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/advertising")
public class AdvertisingController {

    @Autowired
    AdvertisingService advertisingService;
    @GetMapping("/advertising")
    public RespBean getAllAdvertising(){
        if (advertisingService.getAdvertising()!=null){
            return RespBean.ok("获取成功",advertisingService.getAdvertising());
        }
        return RespBean.error("获取失败");
    }
}
