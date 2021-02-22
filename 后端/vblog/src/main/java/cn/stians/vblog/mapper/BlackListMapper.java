package cn.stians.vblog.mapper;

import cn.stians.vblog.model.BlackList;
import cn.stians.vblog.utils.RespBean;
import org.apache.ibatis.annotations.Param;

public interface BlackListMapper {

    int addBlackList(BlackList blackList);

    int checkBlackList(BlackList blackList);

    int removeBlacklist(BlackList blackList);

    int findRelationBlack(@Param("blackId") int userId,@Param("userId") int visitId);
}
