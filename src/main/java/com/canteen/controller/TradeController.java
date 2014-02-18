package com.canteen.controller;

import com.canteen.common.utils.DateUtils;
import com.canteen.entity.Purchase;
import com.canteen.service.GoodsServiceImpl;
import com.canteen.service.PurchaseServiceImpl;
import com.canteen.service.UserServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * User: lixy
 * Mail: admin@TomyTime.com
 * Date: 14-2-18 下午11:25
 */
@Controller
@RequestMapping(value="/p")
public class TradeController {
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

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView("/purchase");

        return mav;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public @ResponseBody List<Purchase> getAllPurchaseRecord() {
        List<Purchase> purchasesList = purchaseService.getAllPurchase();
        return purchasesList;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addPurchase(HttpServletRequest request,HttpServletResponse response){
        logger.info("purchase");

        Purchase p = new Purchase();
        p.setGid(request.getParameter("gid"));
        p.setAmount(request.getParameter("amount"));
        p.setSubtotal(request.getParameter("subtotal"));
        p.setPrice(request.getParameter("price"));
        p.setDaytime(request.getParameter("daytime"));
        p.setUsername("LiYuzhen");

        purchaseService.addPurchase(p);

        ModelAndView mav = new ModelAndView("redirect:/");
        return mav;
    }
}
