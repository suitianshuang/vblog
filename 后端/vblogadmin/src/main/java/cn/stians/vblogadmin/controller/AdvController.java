package cn.stians.vblogadmin.controller;

import cn.stians.vblogadmin.model.Advertising;
import cn.stians.vblogadmin.service.AdvService;
import cn.stians.vblogadmin.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adv/basic")
public class AdvController {
    @Autowired
    AdvService advService;
    @GetMapping("/")
    public RespBean getAdvList(){
        List<Advertising> advList = advService.getAdvList();
        if(advList!=null){
            return RespBean.ok(advList);
        }
        return RespBean.error("最近没有广告");
    }
    @DeleteMapping("/{id}")
    public RespBean deleteAdvById(@PathVariable Integer id){
         advService.deleteAdvById(id);
         return RespBean.ok("删除成功");
    }
    @PostMapping("/")
    public RespBean updateAdv(@RequestBody Advertising advertising){
         advService.updateAdv(advertising);
         return RespBean.ok("修改成功");
    }

    @PostMapping("/insert")
    public RespBean insertAdv(@RequestBody Advertising advertising){
        Advertising advertising1 = advService.insetAdv(advertising);
        if (advertising1!=null){
            return RespBean.ok("新增广告成功",advertising1);
        }
        return RespBean.error("新增广告失败！");
    }
}
