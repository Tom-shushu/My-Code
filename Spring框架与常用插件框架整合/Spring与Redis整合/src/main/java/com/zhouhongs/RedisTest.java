
package com.zhouhongs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhouhongs.bean.Goods;
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
 * <p>Title: RedisSpring整合</p>  
 * <p>Description:RedisSpring整合 </p>  
 * @author 周红  
 * @date 2019年6月5日  
 *
 */  
class RedisTest {

	private GoodsService goodsService;

	@BeforeEach
	void setUp() throws Exception {
		
		// 启动spring容器
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "spring.xml" });
		context.start();

		// 获取业务层对象
		goodsService = (GoodsService) context.getBean("goodsServiceImpl");

	}

	/**
	 * @方法描述：Redis 5大数据类型操作演示
	 * @作成人：周红
	 * @作成日期：2018年10月11日 下午4:18:42
	 * @版本：1.0
	 */
	@Test
	void dataTypeWriteAndRead() {
		
		// String类型数据的写入和读取【业务场景举例：商品信息的保存和读取】
		saveAndGetGoods();
		
		// Hash类型数据的写入和读取【业务场景举例：购物车信息的保存和读取】
		saveAndGetShoppingCart();
		
		// List类型数据的写入和读取【业务场景举例：热销商品信息的保存和读取】
		saveAndGetHotGoodsList();
		
		// Set类型数据的写入和读取【业务场景举例：IP黑名单的保存和读取】
		saveAndGetBlackList();
		
		// SortedSet类型数据的写入和读取【业务场景举例：顾客浏览过的商品信息的保存和读取】
		saveAndGetBrowsedGoods();
	}
	
	void saveAndGetGoods() {
		
		System.out.println("----------- 【业务场景：保存商品信息】【String类型数据写入】开始 -----------");
			
			Goods goods = new Goods("1001", "电视机", 1999);
			goodsService.saveGoods(goods);
			
		System.out.println("----------- 【业务场景：保存商品信息】【String类型数据写入】结束 -----------");
		
		System.out.println("----------- 【业务场景：保存商品信息】【String类型数据读取】开始 -----------");
		
			String name = goodsService.getGoodsNameById(goods.getId());
			System.out.println("ID为" + goods.getId() + "的商品名称是：" + name);
			
		System.out.println("----------- 【业务场景：保存商品信息】【String类型数据读取】结束 -----------");
		
	}
	
	void saveAndGetShoppingCart() {
		
		System.out.println("----------- 【业务场景：保存购物车信息】【Hash类型数据写入】开始 -----------");
		
			Map<String, Integer> shoppingCartMap = new HashMap<String, Integer>();
			shoppingCartMap.put("1011", 2);
			shoppingCartMap.put("1012", 1);
			shoppingCartMap.put("1013", 1);
			
			goodsService.saveShoppingCart("Jerry", shoppingCartMap);
		
		System.out.println("----------- 【业务场景：保存购物车信息】【Hash类型数据写入】结束 -----------");
		
		System.out.println("----------- 【业务场景：保存购物车信息】【Hash类型数据读取】开始 -----------");
		
			Map<String, Integer> cartMap = goodsService.getShoppingCart("Jerry");
			
			for(String key : cartMap.keySet()) {
				System.out.println("商品ID为 " + key + "，数量为 " + cartMap.get(key));
			}
		
		System.out.println("----------- 【业务场景：保存购物车信息】【Hash类型数据读取】结束 -----------");
		
	}
	
	void saveAndGetHotGoodsList() {
		
		System.out.println("----------- 【业务场景：保存热销商品信息】【List类型数据写入】开始 -----------");
		
			List<Goods> hotGoodsList = new ArrayList<Goods>();
			
			hotGoodsList.add(new Goods("1021", "卫衣", 198));
			hotGoodsList.add(new Goods("1024", "长袖T恤", 100));
			hotGoodsList.add(new Goods("1028", "鲁花 花生油", 159.8));
			
			goodsService.saveHotGoodsList(hotGoodsList);
		
		System.out.println("----------- 【业务场景：保存热销商品信息】【List类型数据写入】结束 -----------");
		
		System.out.println("----------- 【业务场景：保存热销商品信息】【List类型数据读取】开始 -----------");
		
			List<Goods> goodsList = goodsService.getHotGoodsList();
			
			for (Goods hotGoods : goodsList) {
				System.out.println(hotGoods.toString());
			}
		
		System.out.println("----------- 【业务场景：保存热销商品信息】【List类型数据读取】结束 -----------");
		
	}
	
	void saveAndGetBlackList() {
		
		System.out.println("----------- 【业务场景：保存IP黑名单】【Set类型数据写入】开始 -----------");
		
			goodsService.saveBlackList("101.102.103.1");
			goodsService.saveBlackList("101.102.103.2");
			goodsService.saveBlackList("101.102.103.3");
		
		System.out.println("----------- 【业务场景：保存IP黑名单】【Set类型数据写入】结束 -----------");
		
		System.out.println("----------- 【业务场景：保存IP黑名单】【Set类型数据读取】开始 -----------");
		
			Set<String> blackList = goodsService.getBlackList();
			
			for (String ip : blackList) {
				System.out.println(ip);
			}
		
		System.out.println("----------- 【业务场景：保存IP黑名单】【Set类型数据读取】结束 -----------");
		
	}
	
	void saveAndGetBrowsedGoods() {
		
		System.out.println("----------- 【业务场景：保存顾客浏览过的商品信息】【SortedSet类型数据写入】开始 -----------");
		
			goodsService.saveBrowsedGoods("M001", new Goods("5001", "专业跑鞋", 399));
			goodsService.saveBrowsedGoods("M001", new Goods("5003", "洗衣机", 1299.9));
			goodsService.saveBrowsedGoods("M001", new Goods("5006", "床品四件套", 458));
		
		System.out.println("----------- 【业务场景：保存顾客浏览过的商品信息】【SortedSet类型数据写入】结束 -----------");
		
		System.out.println("----------- 【业务场景：保存顾客浏览过的商品信息】【SortedSet类型数据读取】开始 -----------");
		
			SortedSet<Goods> browsedGoodsSet = goodsService.getBrowsedGoodsSet("M001");
			
			for (Goods browsedGoods : browsedGoodsSet) {
				System.out.println(browsedGoods.toString());
			}
		
		System.out.println("----------- 【业务场景：保存顾客浏览过的商品信息】【SortedSet类型数据读取】结束 -----------");
		
	}

}

