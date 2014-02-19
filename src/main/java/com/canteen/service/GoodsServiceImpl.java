package com.canteen.service;

import com.canteen.common.dao.BaseDao;
import com.canteen.entity.Goods;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: lixy
 * Mail: admin@TomyTime.com
 * Date: 14-2-16 下午1:13
 */
@Component("goodsService")
public class GoodsServiceImpl {
    @Resource(name="baseDao")
    private BaseDao baseDao;

    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    public Goods getGoodsById(String id){
        String hql = "from Goods g where g.Id=?";

        return (Goods) baseDao.find(hql, new String[]{id});
    }

    public List<Goods> getGoods(String type) {
        String hql = "from Goods g where g.Type=?";

        return baseDao.find(hql, new String[]{type});
    }

    public List<Goods> getAllGoods() {
        return baseDao.find("from Goods");
    }

    public boolean addGoods(Goods goods){
        try{
            baseDao.save(goods);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean updateGoods(Goods goods) {
        try{
            baseDao.update(goods);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean deleteGoods(String id) {
        return baseDao.delOrUpdate("delete Goods g where g.Id = ?", new String[]{id});
    }
}
