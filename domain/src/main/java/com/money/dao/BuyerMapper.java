package com.money.dao;

import com.money.model.Buyer;

public interface BuyerMapper {
    int deleteByPrimaryKey(Long buyerId);

    int insert(Buyer record);

    int insertSelective(Buyer record);

    Buyer selectByPrimaryKey(Long buyerId);

    int updateByPrimaryKeySelective(Buyer record);

    int updateByPrimaryKey(Buyer record);
}