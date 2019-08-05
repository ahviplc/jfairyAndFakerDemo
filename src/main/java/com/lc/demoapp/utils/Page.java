package com.lc.demoapp.utils;

import java.util.List;

public class Page {
	
	private Integer pageSize ;
	
	private Integer pageIdx ;
	
	@SuppressWarnings("rawtypes")
	private List rows ;
	
	private Integer totalRowNum = 0 ;
	
	private Integer totalPageNum = 0 ;
	
	public Page(int pageSize,int pageIdx,int totalRowNum,List<?> pageData){
		this.pageIdx = pageIdx ;
		this.pageSize = pageSize ;
		this.totalRowNum = totalRowNum ;
		this.totalPageNum=((this.totalRowNum-1)/this.pageSize)+1;
		if(this.totalPageNum<this.pageIdx){
			this.pageIdx=this.totalPageNum;
		}
		this.rows = pageData ;
	}
	
	@SuppressWarnings("rawtypes")
	public List getRows() {
		return rows;
	}
	
	@SuppressWarnings("rawtypes")
	public void setRows(List rows) {
		this.rows = rows;
	}
	
	public int getTotalRowNum() {
		return totalRowNum;
	}

	public int getTotalPageNum() {
		return totalPageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getPageIdx() {
		return pageIdx;
	}
}
