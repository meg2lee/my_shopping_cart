package com.tj.session.cart;

import java.util.ArrayList;

/* �̿��ڰ� ������ �������� ������ ItemVO ��ü�� �����Ͽ� ��ٱ��Ͽ� ��´� (��ٱ��� �߰�)
 * ��ٱ��� ����, ���Աݾ� �հ�, ��ٱ��� ����, �׸�� ����,����
 */

public class CartMgr {
	private ArrayList<ItemVO> cart = new ArrayList<>();
	// list interface ��ӹ��� ArrayList�� ũ�Ⱑ �������� �迭
	
	public CartMgr() {} // �⺻������ for??
	
	public boolean add(ItemVO item){// item �߰����� Ȯ�� method
		if(cart.contains(item)){ 
			// cart�迭�� ()��ü�� ���� �ִ��� �˻��ؼ� �ִٸ� ��������
			ItemVO inItem = cart.get(cart.indexOf(item)); 
			// cart�迭���� item�� �����ϴ� index ���� �� ItemVO������ ��ü����
			inItem.setCnt(inItem.getCnt()+item.getCnt());
			// �ش� index��ü�� ���� val�� + �߰��� val���� ��ģ �� ������ ����
		}else { //�ٸ� �������̶�� �׳� īƮ�� ����
			cart.add(item); // ArrayList cart�� ���ο� item��ü �߰�
		}
		return true;
	}
	
	public ArrayList<ItemVO> getCartItems(){
		return cart;
	}
	public int getTotal() {
		int total = 0; // total�� �ʱ�ȭ
		ItemVO item = null; // ItemVO��ü ���� �� �ʱ�ȭ
		for(int i=0;i<cart.size();i++) { // �迭 for loop ������
			item = cart.get(i); // ItemVO item�� car�迭 �� index�� ����
			total += item.getPrice()*item.getCnt(); // �� �ݾ��� price*cnt�� ����
		}
		return total;
	}
	
	public boolean empty() {
		cart.clear(); // ArrayList cart�ȿ� �ִ� ��� ��� ����
		return true;
	}
	
	public boolean removeSome(String[] items) {
		for(int i=cart.size()-1;i>=0;i--) { //size�� �� �����̱⶧���� ����index�� -1
		// index�� ������������ loop(if index deleted, nxt val to be moved forward)
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
