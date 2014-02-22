package com.canteen.service;

import com.canteen.common.dao.BaseDao;
import com.canteen.entity.Capacity;
import com.canteen.entity.Purchase;
import com.canteen.entity.Trade;
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
        String hql = "from Purchase p where p.id=?";

        return (Purchase) baseDao.find(hql, new String[]{id});
    }

    public Capacity getCapacityByGid(String gid){
        String hql = "from Capacity c where c.gid = ?";
        List<Capacity> resultList = baseDao.find(hql, new String[]{gid});
        if(resultList.size() != 0){
            return resultList.get(0);
        }else{
            return null;
        }
    }

    public Capacity getCapacityByIdandPrice(String gid, String price){
        String hql = "from Capacity c where c.gid = ? and c.price = ?";
        List<Capacity> resultList = baseDao.find(hql, new String[]{gid, price});
        if(resultList.size() != 0){
            return resultList.get(0);
        }else{
            return null;
        }
    }

    public List<Purchase> getPurchaseTopList(String gid, int num){
        String hql = "select p.id, p.gid, p.price, p.subtotal, " +
                "p.amount, p.daytime, p.username from" +
                " (select pi.*, row_number() over() as rownum" +
                " from purchase pi where pi.gid=? order by pi.daytime desc) as p where rownum <= " + num;
        List<Purchase> resultList = baseDao.findBySql(hql, new String[]{gid}, Purchase.class);

        if(resultList.size() != 0){
            return resultList;
        }else{
            return null;
        }
    }

   /* public List<User> getUser(String type) {
        String hql = "from User g where g.Type=?";

        return baseDao.find(hql, new String[]{type});
    }*/

    public List<Purchase> getAllPurchase() {
//        return baseDao.find("from Purchase p, Goods g where p.gid = g.id");
        return baseDao.findBySql("select p.id, g.name||'_'||g.model as gid," +
                "p.price, p.subtotal, p.amount, p.daytime, p.username, p.logtime from purchase p, " +
                "goods g where p.gid = g.id order by p.logtime desc, p.daytime desc",
                new String[] {}, Purchase.class);
    }

    public List<Trade> getAllTrade() {
//        return baseDao.find("from Purchase p, Goods g where p.gid = g.id");
        return baseDao.findBySql("select t.id, g.name||'_'||g.model as gid," +
                "u.name as uid, t.price, t.subtotal, t.amount, t.daytime, t.logtime from trade t, " +
                "goods g, member u where t.gid = g.id and t.uid = u.id order by t.logtime desc, t.daytime desc",
                new String[] {}, Trade.class);
    }

    public List<Capacity> getAllCapacity() {
//        return baseDao.find("from Purchase p, Goods g where p.gid = g.id");
        return baseDao.findBySql("select c.id||'_'||g.id as id, g.name||'_'||g.model as gid," +
                "c.price, c.amount, c.subtotal from capacity c, " +
                "goods g where c.gid = g.id and c.amount != '0' order by g.name||'_'||g.model",
                new String[] {}, Capacity.class);
    }

    public boolean addPurchase(Purchase purchase){
        try{
            baseDao.save(purchase);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean addCapacity(Capacity capacity){
        try{
            baseDao.save(capacity);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean addTrade(Trade trade){
        try{
            baseDao.save(trade);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean updatePurchase(Purchase purchase) {
        try{
            baseDao.update(purchase);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean updateCapacity(Capacity capacity) {
        try{
            baseDao.update(capacity);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean deletePurchase(String id) {
        return baseDao.delOrUpdate("delete Purchase p where p.Id = ?", new String[]{id});
    }
}
