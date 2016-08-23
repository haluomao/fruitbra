package com.money.service;

import com.money.dao.BuyerMapper;
import com.money.factory.MyBatisFactory;
import com.money.model.Buyer;
import org.apache.ibatis.session.SqlSession;

/**
 * Created by mfg on 16/08/22.
 */
public class BuyerService {
    public BuyerService() {

    }

    public Buyer queryByPK(long id) {
        SqlSession session = MyBatisFactory.getSession();
        BuyerMapper dao = session.getMapper(BuyerMapper.class);
        Buyer res = dao.selectByPrimaryKey(id);
        return res;
    }

    public int insert(Buyer entity) {
        SqlSession session = MyBatisFactory.getSession();
        BuyerMapper dao = session.getMapper(BuyerMapper.class);
        int res = dao.insertSelective(entity);
        session.commit();
        return res;
    }

    public int update(Buyer entity) {
        SqlSession session = MyBatisFactory.getSession();
        BuyerMapper dao = session.getMapper(BuyerMapper.class);
        int res = dao.updateByPrimaryKey(entity);
        session.commit();
        return res;
    }

    public int delete(long id) {
        SqlSession session = MyBatisFactory.getSession();
        BuyerMapper dao = session.getMapper(BuyerMapper.class);
        int res = dao.deleteByPrimaryKey(id);
        session.commit();
        return res;
    }

}
