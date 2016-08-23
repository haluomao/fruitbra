package com.money.dao;

import com.money.model.WeChat;

public interface WeChatMapper {
    int deleteByPrimaryKey(Long wechatId);

    int insert(WeChat record);

    int insertSelective(WeChat record);

    WeChat selectByPrimaryKey(Long wechatId);

    int updateByPrimaryKeySelective(WeChat record);

    int updateByPrimaryKeyWithBLOBs(WeChat record);

    int updateByPrimaryKey(WeChat record);
}