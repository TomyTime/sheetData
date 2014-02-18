package com.canteen.controller;

import com.canteen.entity.Goods;
import com.canteen.entity.User;
import com.canteen.service.GoodsServiceImpl;
import com.canteen.service.PurchaseServiceImpl;
import com.canteen.service.UserServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
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

    public void setGoodsService(GoodsServiceImpl goodsService) {
        this.goodsService = goodsService;
    }

    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    public void setPurchaseService(PurchaseServiceImpl purchaseService) {
        this.purchaseService = purchaseService;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public @ResponseBody List<Goods> getAllGoods() {
        List<Goods> goodsList = goodsService.getAllGoods();
        return goodsList;
    }

    @RequestMapping(value = "/u", method = RequestMethod.GET)
    public @ResponseBody List<User> getAllUser() {
        return userService.getAllUser();
    }

    @RequestMapping(value = "/p", method = RequestMethod.POST)
    public @ResponseBody Goods p(@RequestBody Goods e){
        logger.info("post: " + e.getId());
        e.setId("这是Id");
        return e;
    }

    @RequestMapping(value = "/i/{id}", method = RequestMethod.GET)
    public ModelAndView getGoodsById(@PathVariable("id") String id){
        ModelAndView mav = new ModelAndView("/index");
        mav.addObject("message", "halo world");
        mav.addObject("goods", goodsService.getGoodsById(id));
        return mav;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addUser(@RequestBody User u){
        logger.info("enter add user");
        ModelAndView mav = new ModelAndView("/index");
        if(null != u){
            userService.addUser(u);
        }else{
            logger.info("add user wrong");
        }

        return mav;
    }
}
