package com.pinyougou.sellergoods.service;

import java.util.List;
import java.util.Map;

import com.pinyougou.pojo.TbBrand;

import entity.PageResult;

public interface BrandService {
	
	List<TbBrand> findAll();
	
	/**
	 * 分页查询
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageResult findPage(int pageNum,int pageSize);
	
	/**
	 * 新增商品
	 * @param brand
	 */
	void add(TbBrand brand);
	
	/**
	 * 根据id查询产品
	 * @param id
	 * @return
	 */
	TbBrand findOne(Long id);
	
	/**
	 * 更新产品
	 * @param brand
	 */
	void update(TbBrand brand);
	
	/**
	 * 根据id删除数据
	 * @param id
	 */
	void delete(Long[] ids);
	
	/**
	 * 条件分页查询
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageResult findPage(TbBrand brand,int pageNum,int pageSize);
	
	/**
	 * 返回下拉列表数据
	 * @return
	 */
	public List<Map> selctOptionList();
}
