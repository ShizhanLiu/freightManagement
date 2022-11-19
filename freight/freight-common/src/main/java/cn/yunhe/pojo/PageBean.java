package cn.yunhe.pojo;

import java.util.List;

/**
 * @Author:Shizhan
 * @ProjectName:freight-codes
 * @currentTime: 2022/9/9 9:24
 */
/*封装分页数据    */
public class PageBean {
    //展示数据
    private List<?> data;
    //总记录数
    private long totalRows;
    //当前页
    private Integer curPage = 1;
    //分页单位
    private Integer pageSize = 5;
    //总页数
    private Integer totalPages;

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public long getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(long totalRows) {
        this.totalRows = totalRows;
    }

    public Integer getCurPage() {
        return curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "data=" + data +
                ", totalRows=" + totalRows +
                ", curPage=" + curPage +
                ", pageSize=" + pageSize +
                ", totalPages=" + totalPages +
                '}';
    }
}
