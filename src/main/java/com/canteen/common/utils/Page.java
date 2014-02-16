package com.canteen.common.utils;

import java.io.Serializable;
import java.util.ArrayList;

public class Page implements Serializable {
	private static final long serialVersionUID = -6375618652287827724L;
	private int start;
	private int pageSize;
	private Object data;
	private int totalCount;
	private int totalPageCount;
	private int currentPageNo;
	private boolean hasNextPage;
	private boolean hasPreviousPage;
	private boolean isStartPage;
	private boolean isEndPage;

	public Page() {
		this(0, 0, Constants.DEFAULT_PAGE_SIZE, new ArrayList());
	}

	public Page(int start, int totalSize, int pageSize, Object data) {
		this.pageSize = Constants.DEFAULT_PAGE_SIZE;

		this.pageSize = pageSize;
		this.start = start;
		this.totalCount = totalSize;
		this.data = data;
		init();
	}

	public int getTotalCount() {
		return this.totalCount;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public Object getResult() {
		return this.data;
	}
	public void setResult(Object obj) {
		this.data = obj;
	}

	public int getCurrentPageNo() {
		return this.currentPageNo;
	}

	public boolean isHasNextPage() {
		return this.hasNextPage;
	}

	public boolean isHasPreviousPage() {
		return this.hasPreviousPage;
	}

	public int getStart() {
		return this.start;
	}

	public int getTotalPageCount() {
		return this.totalPageCount;
	}

	public boolean isEndPage() {
		return this.isEndPage;
	}

	public boolean isStartPage() {
		return this.isStartPage;
	}

	protected void init() {
		if (this.totalCount % this.pageSize == 0)
			this.totalPageCount = (this.totalCount / this.pageSize);
		else {
			this.totalPageCount = (this.totalCount / this.pageSize + 1);
		}

		this.currentPageNo = (this.start / this.pageSize + 1);

		this.hasNextPage = (this.currentPageNo < this.totalPageCount);

		this.hasPreviousPage = (this.currentPageNo > 1);

		this.isStartPage = (this.currentPageNo == 1);

		this.isEndPage = (this.currentPageNo == this.totalPageCount);
	}

	protected static int getStartOfPage(int pageNo) {
		return getStartOfPage(pageNo, Constants.DEFAULT_PAGE_SIZE);
	}

	protected static int getStartOfPage(int pageNo, int pageSize) {
		return ((pageNo - 1) * pageSize);
	}

}