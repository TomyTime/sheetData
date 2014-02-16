package com.canteen.controller;

import com.canteen.entity.Goods;
import com.canteen.service.Impl.GoodsServiceImpl;
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

    public void setGoodsService(GoodsServiceImpl goodsService) {
        this.goodsService = goodsService;
    }

    @Resource(name="goodsService")
    private GoodsServiceImpl goodsService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public @ResponseBody List<Goods> getAllGoods() {
        List<Goods> goodsList = goodsService.getAllGoods();
        return goodsList;
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
    public ModelAndView addGoods(@RequestBody Goods g){
        ModelAndView mav = new ModelAndView("redirect:/g/get");
        if(null != g){
            goodsService.addGoods(g);
        }

        return mav;
    }
}
