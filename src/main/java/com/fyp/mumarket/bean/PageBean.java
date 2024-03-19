package com.fyp.mumarket.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Paging information encapsulation class.
 * @author Administrator
 *
 */
@Component
public class PageBean<T> {
	
	private int currentPage = 1;//currentPage
	
	private int pageSize = 10;//items display in each page. set default to 10
	
	private long total = 0;
	
	private int totalPage;//Total pages
	
	private int offset = 0;//database offset
	
	private List<T> content;
	
	private int showPageSize = 5;//The number of page numbers displayed on the page for quick navigation.
	
	private List<Integer> currentShowPage = new ArrayList<Integer>();//The current page number displayed on the page for quick navigation.

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public int getShowPageSize() {
		return showPageSize;
	}

	public void setShowPageSize(int showPageSize) {
		this.showPageSize = showPageSize;
	}

	public List<Integer> getCurrentShowPage() {
		//First,  display pages that are before the current page
		for(int i = currentPage - 1;i > 0; i--){
			currentShowPage.add(i);
			if(i <= (currentPage - showPageSize)){
				break;
			}
		}
		//Next, display pages that are after the current page
		for(int i = currentPage;i <= totalPage; i++){
			currentShowPage.add(i);
			if(i >= totalPage){
				break;
			}
			if(i >= (showPageSize + currentPage)){
				break;
			}
		}
		Collections.sort(currentShowPage);
		return currentShowPage;
	}

	public void setCurrentShowPage(List<Integer> currentShowPage) {
		this.currentShowPage = currentShowPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getOffset() {
		offset = (currentPage - 1) * pageSize;
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	
}
