package com.summer.spring.board.domain;

import java.sql.Date;

public class Board {
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private String boardFileName;
	private String boardFileRename;
	private String boardFilePath;
	private long boardFileLength;
	private int boardCount;
	private Date bCreateDate;
	private Date bUpadteDate;
	private char bStatus;
	
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getBoardWriter() {
		return boardWriter;
	}
	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}
	public String getBoardFileName() {
		return boardFileName;
	}
	public void setBoardFileName(String boardFileName) {
		this.boardFileName = boardFileName;
	}
	public String getBoardFileRename() {
		return boardFileRename;
	}
	public void setBoardFileRename(String boardFileRename) {
		this.boardFileRename = boardFileRename;
	}
	public String getBoardFilePath() {
		return boardFilePath;
	}
	public void setBoardFilePath(String boardFilePath) {
		this.boardFilePath = boardFilePath;
	}
	public long getBoardFileLength() {
		return boardFileLength;
	}
	public void setBoardFileLength(long boardFileLength) {
		this.boardFileLength = boardFileLength;
	}
	public int getBoardCount() {
		return boardCount;
	}
	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}
	public Date getbCreateDate() {
		return bCreateDate;
	}
	public void setbCreateDate(Date bCreateDate) {
		this.bCreateDate = bCreateDate;
	}
	public Date getbUpadteDate() {
		return bUpadteDate;
	}
	public void setbUpadteDate(Date bUpadteDate) {
		this.bUpadteDate = bUpadteDate;
	}
	public char getbStatus() {
		return bStatus;
	}
	public void setbStatus(char bStatus) {
		this.bStatus = bStatus;
	}
	
	@Override
	public String toString() {
		return "게시글 [번호=" + boardNo + ", 제목=" + boardTitle + ", 내용=" + boardContent
				+ ", 작성자=" + boardWriter + ", 파일이름=" + boardFileName + ", 파일리네임="
				+ boardFileRename + ", 파일경로=" + boardFilePath + ", 파일크기=" + boardFileLength
				+ ", 조회수=" + boardCount + ", 등록일=" + bCreateDate + ", 수정일=" + bUpadteDate
				+ ", 사용여부=" + bStatus + "]";
	}


	
}
