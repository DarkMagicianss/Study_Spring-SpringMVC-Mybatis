package cn.lh.items.service;

import cn.lh.items.po.ItemsCustom;

import java.sql.SQLException;
import java.util.List;

public interface ItemsService {
    //商品查询列表
    public List<ItemsCustom> findItemsList() throws SQLException;
    //根据商品id查询商品信息
    public ItemsCustom findItemsById(int id) throws SQLException;
    //更新商品信息
    //定义service接口,遵循单一职责,将业务参数细化(不要使用包装类型,比如map)
    //id修改商品的id,itemsCustom修改商品的信息
    public void updateItems(Integer id, ItemsCustom itemsCustom) throws SQLException;
}
