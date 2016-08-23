package com.money.service;

import com.money.dao.ProductMapper;
import com.money.factory.MyBatisFactory;
import com.money.model.Product;
import org.apache.ibatis.session.SqlSession;

/**
 * Created by mfg on 16/08/23.
 */
public class ProductService {
    public ProductService() {

    }

    public Product queryByPK(long id) {
        SqlSession session = MyBatisFactory.getSession();
        ProductMapper dao = session.getMapper(ProductMapper.class);
        Product res = dao.selectByPrimaryKey(id);
        return res;
    }

    public int insert(Product entity) {
        SqlSession session = MyBatisFactory.getSession();
        ProductMapper dao = session.getMapper(ProductMapper.class);
        int res = dao.insertSelective(entity);
        session.commit();
        return res;
    }

    public int update(Product entity) {
        SqlSession session = MyBatisFactory.getSession();
        ProductMapper dao = session.getMapper(ProductMapper.class);
        int res = dao.updateByPrimaryKey(entity);
        session.commit();
        return res;
    }

    public int delete(long id) {
        SqlSession session = MyBatisFactory.getSession();
        ProductMapper dao = session.getMapper(ProductMapper.class);
        int res = dao.deleteByPrimaryKey(id);
        session.commit();
        return res;
    }
}
