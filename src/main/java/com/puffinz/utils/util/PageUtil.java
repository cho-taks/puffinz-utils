package com.puffinz.utils.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.puffinz.utils.model.Page;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PageUtil {
	
	public final static int DEFAULT_CURRENT_PAGE = 0;
	public final static int DEFAULT_SCALE = 40;
	public final static int DEFAULT_PAGE_SCALE = 10;

	public Page getPageInfo(long totalCount, int currentPage) {
		return getPageInfo(totalCount, currentPage, 0, 0);
	}
	
	public Page getPageInfo(long totalCount, int currentPage, int contentsScale) {
		return getPageInfo(totalCount, currentPage, 0, contentsScale);
	}

	public Page getPageInfo(long totalCount, int currentPage, int pageScale, int contentsScale) {

		Page page = new Page();
		
		currentPage++;

		int pageSize = DEFAULT_SCALE;
		if (contentsScale > 0) {
			pageSize = contentsScale;
		}

		int pageBlock = DEFAULT_PAGE_SCALE;
		if (pageScale > 0) {
			pageBlock = pageScale;
		}

		int preBlock = (int) Math.floor((currentPage - 1) / pageBlock) * pageBlock;
		int nextBlock = preBlock + pageBlock + 1;

		int totalPage = (((int) totalCount - 1) / pageSize) + 1;

		int startPage = preBlock + 1;

		// Default Page
		int endPage = pageBlock;
		if (totalPage < nextBlock) {
			endPage = totalPage;
		} else {
			endPage = nextBlock - 1;
		}

		if (nextBlock > totalPage) {
			nextBlock = 0;
		}

		int topNo = (int) totalCount - ((currentPage - 1) * pageSize);

		// for thymeleaf
		List<Integer> page_no = new ArrayList<Integer>();
		for (int i = startPage; i <= endPage; i++) {
			page_no.add(i);
		}

		// 총 게시물 수
		page.setTotalCount(totalCount);
		// 현재 페이지
		page.setCurrentPage(currentPage);
		// 한 페이지 게시물 수
		page.setPageSize(pageSize);
		// 화면에 보여질 페이지 수
		page.setPageBlock(pageBlock);
		// 화면에 보여지는 페이지 수 중 이전 페이지 정보
		page.setPreBlock(preBlock);
		// 화면에 보여지는 페이지 수 중 다음 페이지 정보
		page.setNextBlock(nextBlock);
		// 총 페이지 블럭 수
		page.setTotalPage(totalPage);
		// 화면의 페이지 시작 수
		page.setStartPage(startPage);
		// 화면의 페이지 종료 수
		page.setEndPage(endPage);
		// 넘버링 시작 숫자
		page.setTopNo(topNo);
		// thymeleaf 에서 그릴 페이지 정보
		page.setPage_no(page_no);

		// 검증
		/*
		log.warn("totalCount : {}", totalCount);
		log.warn("currentPage : {}", currentPage);
		log.warn("pageSize : {}", pageSize);
		log.warn("pageBlock : {}", pageBlock);
		log.warn("preBlock : {}", preBlock);
		log.warn("nextBlock : {}", nextBlock);
		log.warn("totalPage : {}", totalPage);
		log.warn("startPage : {}", startPage);
		log.warn("endPage : {}", endPage);
		log.warn("topNo : {}", topNo);
		log.warn("page_no.size() : {}", page_no.size());
		 */
		return page;
	}
}
