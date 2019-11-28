package com.webside.util;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

/**
 * 
 * @ClassName: PageUtil
 * @Description: 分页工具类
 * @author gaogang
 * @date 2016年7月12日 下午4:25:03
 *
 */
public class PageUtil implements Serializable{
	/*
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
     * 页码，从1开始
     */
    private int pageNum;
    /*
     * 页面大小
     */
    private int pageSize;
    /*
     * 排序字段
     */
    private String orderByColumn;
	/*
	 * 排序方式
	 */
    private String orderByType;
    
    
    
    public static boolean addPageToModel(Model model, HttpServletRequest request){
		if ( ! request.getParameterMap().containsKey("page") ) {
			model.addAttribute("page", new PageUtil());
			return false;
		}
		
		PageUtil page = new PageUtil();
		page.setPageNum(Integer.valueOf(request.getParameter("page")));
		page.setPageSize(Integer.valueOf(request.getParameter("rows")));
		page.setOrderByColumn(request.getParameter("sidx"));
		page.setOrderByType(request.getParameter("sord"));
		model.addAttribute("page", page);
		return true;
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
	
	public String getOrderByColumn() {
		return orderByColumn;
	}
	
	public void setOrderByColumn(String orderByColumn) {
		this.orderByColumn = orderByColumn;
	}
	
	public String getOrderByType() {
		return orderByType;
	}
	
	public void setOrderByType(String orderByType) {
		this.orderByType = orderByType;
	}

	@Override
	public String toString() {
		return "PageUtil [pageNum=" + pageNum + ", pageSize=" + pageSize + ", orderByColumn=" + orderByColumn
				+ ", orderByType=" + orderByType + "]";
	}
	
}
