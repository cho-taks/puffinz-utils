package com.puffinz.utils.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Page {
	private long totalCount;
	private int currentPage;
	private int pageSize;
	private int pageBlock;
	private int preBlock;
	private int nextBlock;
	private int totalPage;
	private Integer startPage;
	private int endPage;
	private int topNo;
	
	// for thymeleaf
	private List<Integer> page_no = new ArrayList<Integer>();
}
