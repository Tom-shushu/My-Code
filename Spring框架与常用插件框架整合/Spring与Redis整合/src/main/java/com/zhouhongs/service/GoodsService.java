
package com.zhouhongs.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import com.zhouhongs.bean.Goods;


/**  
 * .--,       .--,
 *( (  \.---./  ) )
 * '.__/o   o\__.'   _______________________________________________________
 *    {=  ^  =}  <===代码无BUG!                                              |
 *     >  -  <       | Code is far away from bug with the animal protecting|
 *    /       \      ```````````````````````````````````````````````````````
 *   //       \\
 *  //|   .   |\\
 *  "'\       /'"_.-~^`'-.
 *     \  _  /--'         `
 *   ___)( )(___       
 *  (((__) (__)))    
 *
 * <p>Title: GoodsService</p>  
 * <p>Description:商品信息管理 </p>  
 * @author 周红  
 * @date 2019年6月5日  
 *
 */  
public interface GoodsService {

	/**
	 * 保存商品信息
	 * @param goods
	 */
	public void saveGoods(Goods goods);
	
	/**
	 * 根据商品ID获取商品名称
	 * @param id
	 * @return
	 */
	public String getGoodsNameById(String id);
	
	/**
	 * 保存顾客的购物车信息
	 * @param memberId
	 * @param shoppingCartMap
	 */
	public void saveShoppingCart(String memberId, Map<String, Integer> shoppingCartMap);
	
	/**
	 * 获取顾客的购物车信息
	 * @param memberId
	 * @return
	 */
	public Map<String, Integer> getShoppingCart(String memberId);
	
	/**
	 * 保存热销商品信息
	 * @param goodsList
	 */
	public void saveHotGoodsList(List<Goods> goodsList);
	
	/**
	 * 获取热销商品信息
	 * @return
	 */
	public List<Goods> getHotGoodsList();
	
	/**
	 * 将恶意IP存入黑名单
	 * @param ip
	 */
	public void saveBlackList(String ip);
	
	/**
	 * 获取IP黑名单
	 * @return
	 */
	public Set<String> getBlackList();
	
	/**
	 * 保存顾客的浏览信息
	 * @param memberId
	 * @param goods
	 */
	public void saveBrowsedGoods(String memberId, Goods goods);
	
	/**
	 * 获取顾客的浏览信息
	 * @param memberId
	 * @return
	 */
	public SortedSet<Goods> getBrowsedGoodsSet(String memberId);
	
}

