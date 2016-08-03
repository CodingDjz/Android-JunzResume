package com.junzresume.app.entity;

public class ArrayListViewItem {

	private int seqNum;
	private int tableKey;
	private String text;

	public ArrayListViewItem(int seqNum, int index, String text) {
		super();
		this.seqNum = seqNum;
		this.tableKey = index;
		this.text = text;
	}

	public int getTableKey() {
		return tableKey;
	}

	public void setTableKey(int index) {
		this.tableKey = index;
	}

	public int getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(int seqNum) {
		this.seqNum = seqNum;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
