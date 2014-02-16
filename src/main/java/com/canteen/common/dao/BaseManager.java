package com.canteen.common.dao;

import com.canteen.entity.BaseEntity;

public class BaseManager {

	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	public void update(BaseEntity entity){
		baseDao.update(entity);
	}
	
	public void save(BaseEntity entity){
		baseDao.save(entity);
	}
	
	
	
	
}
