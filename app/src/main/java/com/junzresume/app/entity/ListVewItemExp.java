package com.junzresume.app.entity;

public class ListVewItemExp {

	private int seqNum;
	private int tableKey;
	private String programeName;
	private String programDesp;

	public ListVewItemExp(int seqNum, int tableKey, String programeName,
			String programDesp) {
		super();
		this.seqNum = seqNum;
		this.tableKey = tableKey;
		this.programeName = programeName;
		this.programDesp = programDesp;
	}

	public int getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(int seqNum) {
		this.seqNum = seqNum;
	}

	public int getTableKey() {
		return tableKey;
	}

	public void setTableKey(int tableKey) {
		this.tableKey = tableKey;
	}

	public String getProgrameName() {
		return programeName;
	}

	public void setProgrameName(String programeName) {
		this.programeName = programeName;
	}

	public String getProgramDesp() {
		return programDesp;
	}

	public void setProgramDesp(String programDesp) {
		this.programDesp = programDesp;
	}

}
