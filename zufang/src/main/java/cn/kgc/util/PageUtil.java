package cn.kgc.util;

public class PageUtil {
	private Integer page;
	private Integer rows;

	public PageUtil() {
	}

	public PageUtil(Integer page, Integer rows) {
		this.page = page;
		this.rows = rows;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}
}
