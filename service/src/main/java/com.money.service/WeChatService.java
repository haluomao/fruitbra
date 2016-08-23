package com.money.service;

import com.money.dao.WeChatMapper;
import com.money.factory.MyBatisFactory;
import com.money.model.WeChat;
import org.apache.ibatis.session.SqlSession;

/**
 * Created by mfg on 16/08/23.
 */
public class WeChatService {
    public WeChatService() {

    }

    public WeChat queryByPK(long id) {
        SqlSession session = MyBatisFactory.getSession();
        WeChatMapper dao = session.getMapper(WeChatMapper.class);
        WeChat res = dao.selectByPrimaryKey(id);
        return res;
    }

    public int insert(WeChat entity) {
        SqlSession session = MyBatisFactory.getSession();
        WeChatMapper dao = session.getMapper(WeChatMapper.class);
        int res = dao.insertSelective(entity);
        session.commit();
        return res;
    }

    public int update(WeChat entity) {
        SqlSession session = MyBatisFactory.getSession();
        WeChatMapper dao = session.getMapper(WeChatMapper.class);
        int res = dao.updateByPrimaryKey(entity);
        session.commit();
        return res;
    }

    public int delete(long id) {
        SqlSession session = MyBatisFactory.getSession();
        WeChatMapper dao = session.getMapper(WeChatMapper.class);
        int res = dao.deleteByPrimaryKey(id);
        session.commit();
        return res;
    }
}
