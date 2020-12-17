package com.jie.service.bill;

import com.jie.pojo.Bill;

import java.util.List;



public interface BillService {
	/**
	 * 增加订单
	 * @param bill
	 */
	public boolean add(Bill bill);


	/**
	 * 通过条件获取订单列表-模糊查询-billList
	 * @param bill
	 */
	public List<Bill> getBillList(Bill bill);
	
	/**
	 * 通过billId删除Bill
	 * @param delId
	 */
	public boolean deleteBillById(String delId);
	
	
	/**
	 * 通过billId获取Bill
	 * @param id
	 */
	public Bill getBillById(String id);
	
	/**
	 * 修改订单信息
	 * @param bill
	 */
	public boolean modify(Bill bill);
	
}
