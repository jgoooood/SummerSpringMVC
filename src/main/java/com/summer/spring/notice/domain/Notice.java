package com.summer.spring.notice.domain;

import java.sql.Timestamp;

public class Notice {
    private int noticeNo;
	private String noticeSubject;
	private String noticeContent;
	private String noticeWriter;
	private Timestamp nCreateDate;
	private Timestamp nUpdateDate;
	private String noticeFileName;
	private String noticeFilePath;
	private long noticeFileLength;
	
	
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getNoticeSubject() {
		return noticeSubject;
	}
	public void setNoticeSubject(String noticeSubject) {
		this.noticeSubject = noticeSubject;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public String getNoticeWriter() {
		return noticeWriter;
	}
	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
	}
	public Timestamp getnCreateDate() {
		return nCreateDate;
	}
	public void setnCreateDate(Timestamp nCreateDate) {
		this.nCreateDate = nCreateDate;
	}
	public Timestamp getnUpdateDate() {
		return nUpdateDate;
	}
	public void setnUpdateDate(Timestamp nUpdateDate) {
		this.nUpdateDate = nUpdateDate;
	}
	public String getNoticeFileName() {
		return noticeFileName;
	}
	public void setNoticeFileName(String noticeFileName) {
		this.noticeFileName = noticeFileName;
	}
	public String getNoticeFilePath() {
		return noticeFilePath;
	}
	public void setNoticeFilePath(String noticeFilePath) {
		this.noticeFilePath = noticeFilePath;
	}
	
	public long getNoticeFileLength() {
		return noticeFileLength;
	}
	public void setNoticeFileLength(long noticeFileLength) {
		this.noticeFileLength = noticeFileLength;
	}
	
	@Override
	public String toString() {
		return "공지 [공지번호=" + noticeNo + ", 제목=" + noticeSubject + ", 내용=" + noticeContent
				+ ", 작성자=" + noticeWriter + ", 등록일=" + nCreateDate + ", 수정일=" + nUpdateDate
				+ ", 파일이름=" + noticeFileName + ", 파일경로=" + noticeFilePath + ", 파일크기="
				+ noticeFileLength + "]";
	}
	
	
}
