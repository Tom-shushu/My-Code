
package com.zhouhongs.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.stereotype.Service;

import com.zhouhongs.bean.Goods;
import com.zhouhongs.dao.RedisDAO;
import com.zhouhongs.service.GoodsService;


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
 * <p>Title: GoodsServiceImpl</p>  
 * <p>Description:商品信息管理 </p>  
 * @author 周红  
 * @date 2019年6月5日  
 *
 */  
@Service
public class GoodsServiceImpl implements GoodsService{
	
	@Resource
	RedisDAO redisDAO;

	@Override
	public void saveGoods(Goods goods) {
		redisDAO.add(goods.getId(), goods.getName());
	}

	@Override
	public String getGoodsNameById(String id) {
		return redisDAO.getString(id);
	}

	@Override
	public void saveShoppingCart(String memberId, Map<String, Integer> shoppingCartMap) {
		redisDAO.add(memberId, shoppingCartMap);
	}

	@Override
	public Map<String, Integer> getShoppingCart(String memberId) {
		
		BoundHashOperations<String,Object,Object> hash = redisDAO.getHash(memberId);
		Map<Object, Object> map = hash.entries();
		
		Map<String, Integer> cartMap = new HashMap<String, Integer>();
		
		for(Object key : map.keySet()) {
			cartMap.put(key.toString(), (Integer)map.get(key));
		}
		
		return cartMap;
	}

	@Override
	public void saveHotGoodsList(List<Goods> goodsList) {
		redisDAO.add("hotGoodsList", goodsList);
	}

	@Override
	public List<Goods> getHotGoodsList() {
		
		BoundListOperations<String,Object> list = redisDAO.getList("hotGoodsList");
		
		List<Goods> goodsList = new ArrayList<Goods>();
		
		for(int i = 0; i < list.size(); i++) {
			goodsList.add((Goods)list.index(i));
		}
		
		return goodsList;
	}

	@Override
	public void saveBlackList(String ip) {
		
		Set<String> ipSet = new HashSet<String>();
		ipSet.add(ip);
		
		redisDAO.add("ipBlackList", ipSet);
	}

	@Override
	public Set<String> getBlackList() {
		
		BoundSetOperations<String,Object> set = redisDAO.getSet("ipBlackList");
		
		Set<Object> members = set.members();
		
		Set<String> blackListSet = new HashSet<String>();
		
		for (Object object : members) {
			blackListSet.add(object.toString());
		}
		
		return blackListSet;
	}

	@Override
	public void saveBrowsedGoods(String memberId, Goods goods) {
		
		SortedSet<Goods> goodsSet = new TreeSet<Goods>();
		goodsSet.add(goods);
		
		redisDAO.add(memberId + "BrowsedGoodsSet", goodsSet);
	}

	@Override
	public SortedSet<Goods> getBrowsedGoodsSet(String memberId) {

		BoundZSetOperations<String,Object> sortedSet = redisDAO.getSortedSet(memberId + "BrowsedGoodsSet");
		
		Set<Object> range = sortedSet.range(0, Long.MAX_VALUE);
		
		SortedSet<Goods> browsedGoodsSet = new TreeSet<Goods>();
		
		for (Object object : range) {
			browsedGoodsSet.add((Goods)object);
		}
		
		return browsedGoodsSet;
	}

}

