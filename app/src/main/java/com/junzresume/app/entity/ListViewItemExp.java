package com.junzresume.app.entity;

public class ListViewItemExp {

	private int seqNum;
	private int tableKey;
	private String programeName;
	private String programeDesp;

	public ListViewItemExp(int seqNum, int tableKey, String programeName,
			String programeDesp) {
		super();
		this.seqNum = seqNum;
		this.tableKey = tableKey;
		this.programeName = programeName;
		this.programeDesp = programeDesp;
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

	public String getProgrameDesp() {
		return programeDesp;
	}

	public void setProgrameDesp(String programeDesp) {
		this.programeDesp = programeDesp;
	}

}
