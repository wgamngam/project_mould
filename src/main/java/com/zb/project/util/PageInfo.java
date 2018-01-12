package com.zb.project.util;

/**
 * Created by tangshiwen on 2016/11/30.
 */
public class PageInfo {
    private int pageIndex = 1;  // 页标

    private int pageSize = 10; // 每页记录数(默认10)

    private long totalRecords;   // 总记录数

    private int totalPages;     // 总页数

    public PageInfo() {
    }

    public PageInfo(int pageIndex, int pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public PageInfo(int pageIndex, int pageSize, long totalRecords) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        setTotalRecords(totalRecords);
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
        if (totalRecords % pageSize == 0) {
            totalPages = (int) (totalRecords / pageSize);
        } else {
            totalPages = (int) (totalRecords / pageSize + 1);
        }
    }

    public int getTotalPages() {
        return totalPages == 0 ? 1 : totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * 分页查询起始位置
     */
    public int getStart() {
        return (pageIndex - 1) * pageSize;
    }

    /**
     * 分页查询结束位置
     */
    public int getEnd() {
        return (pageIndex - 1) * pageSize + pageSize;
    }
}
