package com.kh.mybatis.common.util;

// 230307 5교시 게시글 목록 조회 목록 페이징 처리를 위한 클래스
public class PageInfo {

	private int currentPage;
	
	private int pageLimit;
	
	private int listCount;
	
	private int listLimit;	
	
	/**
	 * @param currentPage 현재 페이지
	 * @param pageLimit 한 페이지에 보이는 페이지의 수 (하단에 1, 2, 3, ... 8, 9, 10 버튼)
	 * @param listCount 전체 리스트의 수			(게시물의 갯수)
	 * @param listLimit 한 페이지에 표시될 리스트의 수 (한 페이지에 게시물 몇 개 표시할지)
	 */
	// 모든 값을 다 받는 생성자
	public PageInfo(int currentPage, int pageLimit, int listCount, int listLimit) {
		this.currentPage = currentPage;
		this.pageLimit = pageLimit;
		this.listCount = listCount;
		this.listLimit = listLimit;
	}
	
	/** 	
	 * @return 전체 페이지 중 가장 마지막 페이지
	 */
	public int getMaxPage() {
		/*
		 	listCount = 100, listLimit = 10
		 	100 / 10 = 10.0		=> 10페이지
		 	101 / 10 = 10.1		=> 11페이지
		 	103 / 10 = 10.3		=> 11페이지
		 	109 / 10 = 10.9		=> 11페이지
		 	110 / 10 = 11.0		=> 11페이지
		 	111 / 10 = 11.1		=> 12페이지
		 */
		return (int) Math.ceil((double) this.listCount / this.listLimit);
	}
	
	/**
	 * 
	 * @return 페이징 된 페이지 중 시작 페이지
	 */
	public int getStartPage() {
		/*	
			< 1 2 3 4 5 6 7 8 9 10 >
			< 11 12 13 14 15 16 17 18 19 20 >
			< 21 22 23 24 25 26 27 28 29 30 >
			
			1, 11, 21, 31, .... => (10 * n) + 1 (n >= 0)
			
			1 ~ 10 : n = 0
			11 ~ 20 : n = 1
			21 ~ 30 : n = 2
			31 ~ 40 : n = 3 
			.... 
			n = (currentPage - 1) / pageLimit
			
			(10 * ((currentPage - 1) / pageLimit)) + 1 (n >= 0)
		 */
		return (this.pageLimit * ((this.currentPage - 1) / this.pageLimit)) + 1;
	}

	/**
	 * 
	 * @return 페이징 된 페이지 중 마지막 페이지
	 */ 
	public int getEndPage() {
		// 10, 20, 30, 40, .... 
		
		int endPage = this.getStartPage() + this.pageLimit - 1;
		
		return endPage > this.getMaxPage() ? this.getMaxPage() : endPage;
	}	
	
	/**
	 * 
	 * @return 현재 페이지
	 */ 
	public int getCurrentPage() {
		return this.currentPage;
	}
	
// 230307 6교시
	// 게시글 목록 조회 목록 페이징 처리
	/** 별표 두 개 > 자바독주석. @return 하면 커서를 올렸을 때 노란 메모에 내가 주석에 적은 내용 뜸
	 *
	 *	@return 한 페이지에 표시될 페이지 수
	 *
	 */
	public int getListLimit() {
		return listLimit;
	}
	
	/**
	 * 
	 * @return 이전 페이지
	 */ 
	public int getPrevPage() {
		int prevPage = this.getCurrentPage() - 1;
		
		return prevPage < 1 ? 1 : prevPage;
	}
	


	/**
	 * 
	 * @return 다음 페이지
	 */ 
	public int getNextPage() {
		int nextPage = this.getCurrentPage() + 1;
		
		return nextPage > this.getMaxPage() ? this.getMaxPage() : nextPage;
	}
	
	/**
	 * 
	 * @return 페이지의 시작 리스트 
	 */ 	
	public int getStartList() {
		return (this.getCurrentPage() - 1) * this.listLimit + 1;
	}
	
	/**
	 * 
	 * @return 페이지의 마지막 리스트
	 */ 	
	public int getEndList() {
		int endList = this.getStartList() + this.listLimit - 1;
		
		return endList > this.listCount ? this.listCount : endList;
	}
		
}
