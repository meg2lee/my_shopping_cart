package com.tj.session.cart;

public class ItemVO {
	
	private String category;
	private String itemName;
	private int price;
	private int cnt=1; //���Ž� 1�� �ʱ�ȭ
	
	public ItemVO() {} // usebean ��ü �������� �⺻������
	
	public ItemVO(String category, String itemName, int price, int cnt) {
		this.category = category;
		this.itemName = itemName;
		this.price = price;
		this.cnt = cnt;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	@Override
	public boolean equals(Object obj) { 
		// java�� ��� class�� Object���� ��ӵ� method�� �ִ�. ex.equals
		// � ��ü�� ������ �𸣱� ������ parameter�� Object�� �޴´�
		if(!(obj instanceof ItemVO)) return false;
		// Object parameter�� ���� obj�� ItemVO�� ��ü�� �ƴϸ� return false
		// ���� �ش簴ü�� �´ٸ� �Ʒ� �Լ� ����
		ItemVO other = (ItemVO) obj;
		if(this.itemName.equals(other.itemName) &&
			this.category.equals(other.category) &&
			this.price==other.price) {
			return true;
		}
		return false;
	}
	
	
}
