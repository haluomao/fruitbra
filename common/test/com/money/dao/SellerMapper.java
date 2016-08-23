package test.com.money.dao;

import test.com.money.model.Seller;

public interface SellerMapper {
    int deleteByPrimaryKey(Long sellerId);

    int insert(Seller record);

    int insertSelective(Seller record);

    Seller selectByPrimaryKey(Long sellerId);

    int updateByPrimaryKeySelective(Seller record);

    int updateByPrimaryKey(Seller record);
}