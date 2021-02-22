package cn.stians.vblog.service;

import cn.stians.vblog.mapper.BlackListMapper;
import cn.stians.vblog.model.BlackList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BlackListService {

    @Autowired
    BlackListMapper blackListMapper;
    @Transactional
    public int  addBlackList(BlackList blackList) {
//        先判断用户是不是已经在黑名单中了
       int result =  blackListMapper.checkBlackList(blackList);
       if(result !=0){
           return 0;
       }
        return blackListMapper.addBlackList(blackList);
    }
    @Transactional
    public int removeBlacklist(BlackList blackList) {
//        检查用户是不是在黑名单中，如果不在直接返回
        int result =  blackListMapper.checkBlackList(blackList);
        if(result !=1){
            return 0;
        }
        return blackListMapper.removeBlacklist(blackList);
    }
}
