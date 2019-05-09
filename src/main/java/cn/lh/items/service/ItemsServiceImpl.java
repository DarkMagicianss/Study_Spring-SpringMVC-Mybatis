package cn.lh.items.service;

import cn.lh.items.mapper.ItemsMapper;
import cn.lh.items.po.ItemsCustom;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.List;

public class ItemsServiceImpl implements ItemsService{
    @Autowired
    private ItemsMapper itemsMapper;
    //商品查询列表
    public List<ItemsCustom> findItemsList() throws SQLException {
        return itemsMapper.findItemsList();
    }
    @Override
    public ItemsCustom findItemsById(int id) throws SQLException {
        return itemsMapper.findItemsById(id);
    }

    @Override
    public void updateItems(Integer id, ItemsCustom itemsCustom) throws SQLException {
        itemsMapper.updateItems(itemsCustom);
    }
}
