package com.tj.session.cart;

public class ItemVO {
	
	private String category;
	private String itemName;
	private int price;
	private int cnt=1; //구매시 1로 초기화
	
	public ItemVO() {} // usebean 객체 생성위해 기본생성자
	
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
		// java의 모든 class는 Object에서 상속된 method가 있다. ex.equals
		// 어떤 객체를 받을지 모르기 때문에 parameter를 Object로 받는다
		if(!(obj instanceof ItemVO)) return false;
		// Object parameter로 받은 obj가 ItemVO의 객체가 아니면 return false
		// 만약 해당객체가 맞다면 아래 함수 실행
		ItemVO other = (ItemVO) obj;
		if(this.itemName.equals(other.itemName) &&
			this.category.equals(other.category) &&
			this.price==other.price) {
			return true;
		}
		return false;
	}
	
	
}
