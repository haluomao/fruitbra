package com.money.service;

import com.money.dao.OrderMapper;
import com.money.factory.MyBatisFactory;
import com.money.model.Order;
import org.apache.ibatis.session.SqlSession;

/**
 * Created by mfg on 16/08/23.
 */
public class OrderService {
    public OrderService() {

    }

    public Order queryByPK(long id) {
        SqlSession session = MyBatisFactory.getSession();
        OrderMapper dao = session.getMapper(OrderMapper.class);
        Order res = dao.selectByPrimaryKey(id);
        return res;
    }

    public int insert(Order entity) {
        SqlSession session = MyBatisFactory.getSession();
        OrderMapper dao = session.getMapper(OrderMapper.class);
        int res = dao.insertSelective(entity);
        session.commit();
        return res;
    }

    public int update(Order entity) {
        SqlSession session = MyBatisFactory.getSession();
        OrderMapper dao = session.getMapper(OrderMapper.class);
        int res = dao.updateByPrimaryKey(entity);
        session.commit();
        return res;
    }

    public int delete(long id) {
        SqlSession session = MyBatisFactory.getSession();
        OrderMapper dao = session.getMapper(OrderMapper.class);
        int res = dao.deleteByPrimaryKey(id);
        session.commit();
        return res;
    }
}
