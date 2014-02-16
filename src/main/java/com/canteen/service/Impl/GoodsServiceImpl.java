package com.canteen.service.Impl;

import com.canteen.dao.GoodsDaoImpl;
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
    @Resource(name="goodsDao")
    private GoodsDaoImpl goodsDao;

    public void setGoodsDao(GoodsDaoImpl goodsDao) {
        this.goodsDao = goodsDao;
    }

    public Goods getGoodsById(String id){
        return goodsDao.getGoodsById(id);
    }

    public List<Goods> getGoods(String type) {
        return goodsDao.getGoods(type);
    }

    public List<Goods> getAllGoods() {
       return goodsDao.getAllGoods();
    }

    public void addGoods(Goods goods) {
        goodsDao.addGoods(goods);
    }

    public void updateGoods(Goods goods) {
        goodsDao.updateGoods(goods);
    }

    public boolean deleteGoods(String id) {
        return goodsDao.deleteGoods(id);
    }
}
