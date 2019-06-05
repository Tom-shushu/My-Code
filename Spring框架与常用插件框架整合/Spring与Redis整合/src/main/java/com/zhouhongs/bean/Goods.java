
package com.zhouhongs.bean;

import java.io.Serializable;

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
 * <p>Title: Goods</p>  
 * <p>Description: 商品信息</p>  
 * @author 周红  
 * @date 2019年6月5日  
 *
 */  
public class Goods implements Serializable, Comparable<Goods>{

	private static final long serialVersionUID = 7225820943087732837L;

	// 商品ID
    private String id;
    
    // 商品名称
    private String name;
    
    // 商品价格
    private double price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

	public Goods(String id, String name, double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	@Override
	public String toString() {
		return "Goods [id=" + id + ", name=" + name + ", price=" + price + "]";
	}

	@Override
	public int compareTo(Goods goods) {
		
		// 按价格升序
		return (int)(this.getPrice() - goods.getPrice());
		// 按价格降序
		// return (int)(goods.getPrice() - this.getPrice());
	}
    
}

