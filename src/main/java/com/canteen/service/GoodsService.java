package com.canteen.service;

import com.canteen.entity.Goods;

import java.util.List;

/**
 * User: lixy
 * Mail: admin@TomyTime.com
 * Date: 14-2-16 下午1:11
 */
public interface GoodsService {
    public Goods getGoodsById(String id);

    public List<Goods> getGoods(String type);

    public List<Goods> getAllGoods();

    public void addGoods(Goods goods);

    public boolean updateGoods(Goods goods);

    public boolean deleteGoods(String id);
}
