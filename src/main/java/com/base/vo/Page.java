package com.base.vo;

import java.util.List;

/**
 * 对分页的基本数据进行封装
 */
public class Page<T>{
    private int pageNum = 1;//页码，默认是第一页
    private int pageSize = 5;//每页显示的记录数，默认是5
    private int totalRecord;//总记录数
    private int total;//总记录数
    private int totalPage;//总页数
    private List<T> results;//对应的当前页记录

    
    public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
        this.total=totalRecord;
        int totalPage = totalRecord % pageSize == 0 ? totalRecord / pageSize : totalRecord / pageSize + 1;
        this.setTotalPage(totalPage);
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getResults() {
    	if(null != results && results.size() == 0){
            return null;
    	}
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }



}
