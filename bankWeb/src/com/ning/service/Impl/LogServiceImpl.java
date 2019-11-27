package com.ning.service.Impl;

import com.ning.pojo.Log;
import com.ning.pojo.PageInfo;
import com.ning.service.LogService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogServiceImpl implements LogService {
    @Override
    public PageInfo showPage(int pageSize, int pageNumber) throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = factory.openSession();
        Map<String,Object> param = new HashMap<>();
        param.put("pageStart",pageSize*(pageNumber-1));
        param.put("pageSize", pageSize);
        List<Log> list = session.selectList("com.ning.mapper.LogMapper.selByPage",param);
        long count = session.selectOne("com.ning.mapper.LogMapper.selCount");
        PageInfo pi = new PageInfo();
        pi.setList(list);
        pi.setPageNumber(pageNumber);
        pi.setPageSize(pageSize);
        pi.setTotal(count%pageSize==0?count/pageSize:count/pageSize+1);
        return pi;
    }
}
