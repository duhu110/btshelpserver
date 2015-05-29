package com.duhu.entity;

import java.io.Serializable;

public class Btsname implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int btsid;
	private String btsnameString;
	public Btsname() {
		super();
	}
	public Btsname(int btsid, String btsnameString) {
		super();
		this.btsid = btsid;
		this.btsnameString = btsnameString;
	}
	public int getBtsid() {
		return btsid;
	}
	public void setBtsid(int btsid) {
		this.btsid = btsid;
	}
	public String getBtsnameString() {
		return btsnameString;
	}
	public void setBtsnameString(String btsnameString) {
		this.btsnameString = btsnameString;
	}
}
