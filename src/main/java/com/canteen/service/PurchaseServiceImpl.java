package com.canteen.service;

import com.canteen.common.dao.BaseDao;
import com.canteen.entity.Purchase;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: lixy
 * Mail: admin@TomyTime.com
 * Date: 14-2-16 下午1:13
 */
@Component("purchaseService")
public class PurchaseServiceImpl {
    @Resource(name="baseDao")
    private BaseDao baseDao;
    @Resource(name="goodsService")
    private GoodsServiceImpl goodsService;

    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    public void setGoodsService(GoodsServiceImpl goodsService) {
        this.goodsService = goodsService;
    }

    public Purchase getPurchaseById(String id){
        String hql = "from Purchase p where p.Id=?";

        return (Purchase) baseDao.find(hql, new String[]{id});
    }

   /* public List<User> getUser(String type) {
        String hql = "from User g where g.Type=?";

        return baseDao.find(hql, new String[]{type});
    }*/

    public List<Purchase> getAllPurchase() {
//        return baseDao.find("from Purchase p, Goods g where p.gid = g.id");
        return baseDao.findBySql("select p.id, g.name||'_'||g.model as gid," +
                "p.price, p.subtotal, p.amount, p.daytime, p.username from purchase p, " +
                "goods g where p.gid = g.id order by p.daytime desc",
                new String[] {}, Purchase.class);
    }

    public void addPurchase(Purchase purchase) {
        baseDao.save(purchase);
    }

    public void updatePurchase(Purchase purchase) {
        baseDao.update(purchase);
    }

    public boolean deletePurchase(String id) {
        return baseDao.delOrUpdate("delete Purchase p where p.Id = ?", new String[]{id});
    }
}
