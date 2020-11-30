package com.tj.session.cart;

import java.util.ArrayList;

/* 이용자가 구입한 이이템의 정보를 ItemVO 객체에 저장하여 장바구니에 담는다 (장바구니 추가)
 * 장바구니 보기, 구입금액 합계, 장바구니 비우기, 항목수 증감,삭제
 */

public class CartMgr {
	private ArrayList<ItemVO> cart = new ArrayList<>();
	// list interface 상속받은 ArrayList는 크기가 가변적인 배열
	
	public CartMgr() {} // 기본생성자 for??
	
	public boolean add(ItemVO item){// item 추가여부 확인 method
		if(cart.contains(item)){ 
			// cart배열이 ()객체를 갖고 있는지 검사해서 있다면 수량증가
			ItemVO inItem = cart.get(cart.indexOf(item)); 
			// cart배열에서 item이 차지하는 index 추출 및 ItemVO형으로 객체저장
			inItem.setCnt(inItem.getCnt()+item.getCnt());
			// 해당 index객체의 기존 val값 + 추가된 val값을 합친 총 개수를 구함
		}else { //다른 아이템이라면 그냥 카트에 저장
			cart.add(item); // ArrayList cart에 새로운 item객체 추가
		}
		return true;
	}
	
	public ArrayList<ItemVO> getCartItems(){
		return cart;
	}
	public int getTotal() {
		int total = 0; // total값 초기화
		ItemVO item = null; // ItemVO객체 생성 및 초기화
		for(int i=0;i<cart.size();i++) { // 배열 for loop 돌리기
			item = cart.get(i); // ItemVO item을 car배열 각 index에 저장
			total += item.getPrice()*item.getCnt(); // 총 금액은 price*cnt로 구함
		}
		return total;
	}
	
	public boolean empty() {
		cart.clear(); // ArrayList cart안에 있는 목록 모두 삭제
		return true;
	}
	
	public boolean removeSome(String[] items) {
		for(int i=cart.size()-1;i>=0;i--) { //size는 총 갯수이기때문에 끝방index는 -1
		// index를 내림차순으로 loop(if index deleted, nxt val to be moved forward)
			for(int j=0;j<items.length;j++) {
				if(cart.get(i).getItemName().equals(items[j])) {
					cart.remove(i);
					break;
				}
			}
		}
		return true;
	}
	
	public boolean updateQty(String items, int cnt) {
		for(int i=0;i<cart.size();i++) {
			if(cart.get(i).getItemName().equals(items)) {
				cart.get(i).setCnt(cnt);
				return true;
			}
		}
		return false;
	}
	
}
