package com.money.entity;

/**
 * Created by mfg on 16/08/22.
 */
public class Page {
    int pageIndex;
    int pageSize;
    int totalPage;
    int totalCount;
    int sinceIndex;
    public Page(){

    }

    public Page(int pageIndex,int pageSize){
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        if(pageIndex>=1){
            sinceIndex = (pageIndex-1)*pageSize;
        }
    }
}
