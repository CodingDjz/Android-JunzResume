package com.junzresume.app.entity;


public class DrawerItem {

	int itemImageId;
	String itemName;

	public DrawerItem(int itemImageId, String itemName) {
		super();
		this.itemImageId = itemImageId;
		this.itemName = itemName;
	}

	public int getItemImage() {
		return itemImageId;
	}

	public void setItemImage(int itemImage) {
		this.itemImageId = itemImage;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

}
