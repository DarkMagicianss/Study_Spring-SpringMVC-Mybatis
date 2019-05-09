package cn.lh.items.controller;

import cn.lh.items.po.ItemsCustom;
import cn.lh.items.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.List;

@Controller
public class ItemsController {
    //注入service
    @Autowired
    private ItemsService itemsService;

    @RequestMapping("/queryItems")
    public ModelAndView queryItems() throws SQLException {
        //调用service查询商品列表
        List<ItemsCustom> itemsList = itemsService.findItemsList();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("itemsList", itemsList);
        //指定逻辑视图名
        modelAndView.setViewName("itemsList");
        return modelAndView;
    }

    @RequestMapping("/editItems")
    public String editItems(Model model, Integer id) throws SQLException {
        //调用service查询商品信息
        ItemsCustom itemsCustom = itemsService.findItemsById(id);
        model.addAttribute("item", itemsCustom);
        return "editItem";
    }

    @RequestMapping("/editItemSubmit")
    public String editItemSubmit(Model model, ItemsCustom itemsCustom) throws SQLException {
        //进行数据回显
        model.addAttribute("item", itemsCustom);
        if (itemsCustom.getName().equals(""))
            return "editItem";
        //调用service接口更新商品信息
        itemsService.updateItems(itemsCustom.getId(), itemsCustom);
        return "redirect:queryItems.action";
    }
}
