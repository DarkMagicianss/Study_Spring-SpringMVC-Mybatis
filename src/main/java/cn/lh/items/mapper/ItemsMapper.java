package cn.lh.items.mapper;

import cn.lh.items.po.ItemsCustom;

import java.sql.SQLException;
import java.util.List;

public interface ItemsMapper {
    public ItemsCustom findItemsById(int id) throws SQLException;
    public void updateItems(ItemsCustom itemsCustom) throws SQLException;
    //商品查询列表
    public List<ItemsCustom> findItemsList() throws SQLException;
}
