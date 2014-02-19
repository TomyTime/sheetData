package com.canteen.common.dao;

import com.canteen.entity.BaseEntity;

public class BaseManager {

	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	public boolean update(BaseEntity entity){
        try{
            baseDao.update(entity);
            return true;
        }catch (Exception e){
            return false;
        }
	}
	
	public boolean save(BaseEntity entity){
        try{
            baseDao.save(entity);
            return true;
        }catch (Exception e){
            return false;
        }
	}
	
	
	
	
}
