package com.canteen.dao;

import com.canteen.common.dao.BaseDao;
import com.canteen.entity.Goods;

import java.util.List;

/**
 * User: lixy
 * Mail: admin@TomyTime.com
 * Date: 14-2-16 下午1:07
 */
public class GoodsDaoImpl {

    private BaseDao baseDao;

    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    public Goods getGoodsById(String id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<Goods> getGoods(String type){
        String hql = "from Goods g where g.Type=?";

        return baseDao.find(hql, new String[] {type});
    }

    public List<Goods> getAllGoods() {
        return baseDao.find("from Goods");
    }

    public void addGoods(Goods goods) {
        baseDao.save(goods);
    }

    public void updateGoods(Goods goods) {
        baseDao.update(goods);
    }

    public boolean deleteGoods(String id) {
        return baseDao.delOrUpdate("delete Goods g where g.Id = ?", new String[] {id});
    }

}
