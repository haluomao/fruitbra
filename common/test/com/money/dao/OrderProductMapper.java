package test.com.money.dao;

import test.com.money.model.OrderProduct;
import test.com.money.model.OrderProductKey;

public interface OrderProductMapper {
    int deleteByPrimaryKey(OrderProductKey key);

    int insert(OrderProduct record);

    int insertSelective(OrderProduct record);

    OrderProduct selectByPrimaryKey(OrderProductKey key);

    int updateByPrimaryKeySelective(OrderProduct record);

    int updateByPrimaryKey(OrderProduct record);
}