package com.ning.service;

import com.ning.pojo.PageInfo;

import java.io.IOException;

public interface LogService {
    /*
    * 分页显示
    * */
    PageInfo showPage(int pageSize,int pageNumber) throws IOException;
}
