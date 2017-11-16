package com.yoosal.zqmh.dao;

import com.yoosal.zqmh.pojo.Adv;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdvMapper extends CommonDao<Adv> {
   List<Adv> getAdvByPosition(@Param(value = "positionId") int positionId, @Param(value = "enable") int enable);
}