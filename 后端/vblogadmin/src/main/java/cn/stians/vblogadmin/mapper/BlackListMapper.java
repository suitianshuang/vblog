package cn.stians.vblogadmin.mapper;

import cn.stians.vblogadmin.model.BlackList;
import org.apache.ibatis.annotations.Param;

public interface BlackListMapper {

    int addBlackList(BlackList blackList);

    int checkBlackList(BlackList blackList);

    int removeBlacklist(BlackList blackList);

    int findRelationBlack(@Param("blackId") int userId, @Param("userId") int visitId);
}
