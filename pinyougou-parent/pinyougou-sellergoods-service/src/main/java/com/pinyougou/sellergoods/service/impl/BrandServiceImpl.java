package com.pinyougou.sellergoods.service.impl;


import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.pojo.TbBrandExample.Criteria;
import com.pinyougou.sellergoods.service.BrandService;

import entity.PageResult;

@Service
public class BrandServiceImpl implements BrandService {
	
	@Autowired
	private TbBrandMapper tbBrandMapper;
	
	@Override
	public List<TbBrand> findAll() {
		return tbBrandMapper.selectByExample(null);
	}

	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<TbBrand> page = (Page<TbBrand>)tbBrandMapper.selectByExample(null);
		return new PageResult(page.getTotal(),page.getResult());
	}

	@Override
	public void add(TbBrand brand) {
		tbBrandMapper.insert(brand);
	}

	@Override
	public TbBrand findOne(Long id) {
		return tbBrandMapper.selectByPrimaryKey(id);
	}

	@Override
	public void update(TbBrand brand) {
		tbBrandMapper.updateByPrimaryKey(brand);
	}

	@Override
	public void delete(Long[] ids) {
		for (Long id : ids) {
			tbBrandMapper.deleteByPrimaryKey(id);
		}
	}
	
	@Override
	public PageResult findPage(TbBrand brand, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		TbBrandExample tbBrandExample = new TbBrandExample();
		Criteria createCriteria = tbBrandExample.createCriteria();
		if (brand != null) {
			if (StringUtils.isNotBlank(brand.getName())) {
				createCriteria.andNameLike("%" + brand.getName() + "%");
			}
			if (StringUtils.isNoneBlank(brand.getFirstChar())) {
				createCriteria.andFirstCharEqualTo(brand.getFirstChar());
			}
		}
		Page<TbBrand> page = (Page<TbBrand>)tbBrandMapper.selectByExample(tbBrandExample);
		return new PageResult(page.getTotal(),page.getResult());
	}

	@Override
	public List<Map> selctOptionList() {
		return tbBrandMapper.selectOptionList();
	}
	

}
