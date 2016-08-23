package com.money.service;

import com.money.dao.SellerMapper;
import com.money.factory.MyBatisFactory;
import com.money.model.Seller;
import org.apache.ibatis.session.SqlSession;

/**
 * Created by mfg on 16/08/23.
 */
public class SellerService {
    public SellerService() {

    }

    public Seller queryByPK(long id) {
        SqlSession session = MyBatisFactory.getSession();
        SellerMapper dao = session.getMapper(SellerMapper.class);
        Seller res = dao.selectByPrimaryKey(id);
        return res;
    }

    public int insert(Seller entity) {
        SqlSession session = MyBatisFactory.getSession();
        SellerMapper dao = session.getMapper(SellerMapper.class);
        int res = dao.insertSelective(entity);
        session.commit();
        return res;
    }

    public int update(Seller entity) {
        SqlSession session = MyBatisFactory.getSession();
        SellerMapper dao = session.getMapper(SellerMapper.class);
        int res = dao.updateByPrimaryKey(entity);
        session.commit();
        return res;
    }

    public int delete(long id) {
        SqlSession session = MyBatisFactory.getSession();
        SellerMapper dao = session.getMapper(SellerMapper.class);
        int res = dao.deleteByPrimaryKey(id);
        session.commit();
        return res;
    }
}
