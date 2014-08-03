package com.canteen.controller;

import com.canteen.entity.Capacity;
import com.canteen.entity.Goods;
import com.canteen.entity.User;
import com.canteen.service.GoodsServiceImpl;
import com.canteen.service.PurchaseServiceImpl;
import com.canteen.service.UserServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: lixy
 * Mail: admin@TomyTime.com
 * Date: 14-2-16 上午12:32
 */
@Controller
@RequestMapping(value="/g")
public class IndexController {
    private Logger logger = Logger.getLogger(IndexController.class);

    @Resource(name="goodsService")
    private GoodsServiceImpl goodsService;
    @Resource(name="userService")
    private UserServiceImpl userService;
    @Resource(name="purchaseService")
    private PurchaseServiceImpl purchaseService;

    private Goods g;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView getIndex() {
        return new ModelAndView("goods");
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView getGoodsList() {
        return new ModelAndView("purchase");
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public @ResponseBody List<Goods> getAllGoods() {
        List<Goods> goodsList = goodsService.getAllGoods();
        return goodsList;
    }

    @RequestMapping(value = "/getC", method = RequestMethod.GET)
    public @ResponseBody List<Capacity> getCapacity() {
        return purchaseService.getAllCapacity();
    }

    @RequestMapping(value = "/getU", method = RequestMethod.GET)
    public @ResponseBody List<User> getAllUser() {
        return userService.getAllUser();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteGoodsById(@PathVariable("id") String id){
        logger.info("delete goods");
        boolean flag;
        ModelAndView mav = new ModelAndView("redirect:/g/index");
        if(StringUtils.isNotBlank(id)){
            flag = goodsService.deleteGoods(id);
            if(!flag){
                logger.error("delete goods failed");
            }
        }else{
            logger.error("goods id is null");
        }
        return mav;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addGoods(Goods g){
        logger.info("add goods");
        boolean flag;
        ModelAndView mav = new ModelAndView("redirect:/g/index");
        if(null != g){
            flag = goodsService.addGoods(g);
            if(!flag){
                logger.error("add goods failed");
            }
        }else{
            logger.error("goods is null");
        }

        return mav;
    }
}
