package com.canteen.controller;

import com.canteen.common.utils.DateUtils;
import com.canteen.entity.Capacity;
import com.canteen.entity.Purchase;
import com.canteen.entity.Trade;
import com.canteen.service.GoodsServiceImpl;
import com.canteen.service.PurchaseServiceImpl;
import com.canteen.service.UserServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.List;

/**
 * User: lixy
 * Mail: admin@TomyTime.com
 * Date: 14-2-18 下午11:25
 */
@Controller
@RequestMapping(value = "/p")
public class TradeController {
    private Logger logger = Logger.getLogger(IndexController.class);
    private Purchase purchase;
    private Capacity capacity;

    @Resource(name = "goodsService")
    private GoodsServiceImpl goodsService;
    @Resource(name = "userService")
    private UserServiceImpl userService;
    @Resource(name = "purchaseService")
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

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public void setCapacity(Capacity capacity) {
        this.capacity = capacity;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView getIndex() {
        ModelAndView mav = new ModelAndView("purchase_log");

        return mav;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public @ResponseBody List<Purchase> getAllPurchaseRecord() {
        List<Purchase> purchasesList = purchaseService.getAllPurchase();
        return purchasesList;
    }

    @RequestMapping(value = "/getT", method = RequestMethod.GET)
    public @ResponseBody List<Trade> getAllTradeRecord() {
        return purchaseService.getAllTrade();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addPurchase(Purchase purchase) {
        logger.info("add purchase");
        boolean flag;
        Capacity c;
        if (null != purchase) {
            purchase.setLogtime(DateUtils.getYMDHMSTime());
            flag = purchaseService.addPurchase(purchase);
            if (flag) {
                c = purchaseService.getCapacityByIdandPrice(purchase.getGid(), purchase.getPrice());
                if (null == c) {
                    c = new Capacity();
                    c.setSubtotal(purchase.getSubtotal());
                    c.setAmount(purchase.getAmount());
                    c.setPrice(purchase.getPrice());
                    c.setGid(purchase.getGid());
                    purchaseService.addCapacity(c);
                } else {
                    c.setAmount((Integer.parseInt(c.getAmount()) + Integer.parseInt(purchase.getAmount())) + "");
                    Double subtotal = (Double.parseDouble(c.getSubtotal()) + Double.parseDouble(purchase.getSubtotal()));
                    DecimalFormat df = new DecimalFormat("#.00");
                    c.setSubtotal(df.format(subtotal) + "");
//                c.setPrice(purchase.getPrice());
                    purchaseService.updateCapacity(c);
                }
            } else {
                //do something for capacity error
            }

        }
        ModelAndView mav = new ModelAndView("redirect:/g/list");
        return mav;
    }

    @RequestMapping(value = "/addT", method = RequestMethod.POST)
    public ModelAndView addTrade(Trade trade) throws Exception {
        logger.info("add trade");
        boolean flag;
        Capacity c;
        if (null != trade) {
            System.out.println(trade.getGid() + " 3242 " + trade.getPrice());
            String id = trade.getGid();
            trade.setGid(id.split("_")[1]);
            trade.setLogtime(DateUtils.getYMDHMSTime());
            flag = purchaseService.addTrade(trade);

            if (flag) {
                System.out.println(trade.getGid() + " - " + trade.getPrice());
                c = purchaseService.getCapacityByIdandPrice(trade.getGid(), trade.getPrice());
                if (null == c) {
                    logger.error("capacity null in selling");
                } else {
                    c.setAmount((Integer.parseInt(c.getAmount()) - Integer.parseInt(trade.getAmount())) + "");
                    Double subtotal = (Double.parseDouble(c.getSubtotal()) - Double.parseDouble(trade.getSubtotal()));
                    DecimalFormat df = new DecimalFormat("#.00");
                    c.setSubtotal(df.format(subtotal) + "");
//                c.setPrice(purchase.getPrice());
                    purchaseService.updateCapacity(c);
                }
            } else {
                //do something for capacity error
            }
        }
        ModelAndView mav = new ModelAndView("redirect:/");
        return mav;
    }

}
