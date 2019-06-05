
package com.zhouhongs.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

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
 * <p>Title: RedisDAO</p>  
 * <p>Description: 利用Redis实现数据缓存</p>  
 * @author 周红  
 * @date 2019年6月5日  
 *
 */  
@Repository
public class RedisDAO {
	
    @Autowired
    private RedisTemplate<String, Object> template;

    /**
     * 向Redis写入String类型的数据
     * @param key
     * @param value
     */
    public void add(String key, Object value) {
    	
    	if(value instanceof String) {
    		template.opsForValue().set(key, value);
    	}
    	else if(value instanceof Map) {
    		
    		Map<String, Object> map = (Map<String, Object>) value;
    		
    		// template.opsForHash().putAll(key, map);
    		
    		for (String hashKey : map.keySet()) {
    			template.opsForHash().put(key, hashKey, map.get(hashKey));
			}
    	}
    	else if(value instanceof List) {
			
    		List<Object> list = (List<Object>) value;
    		
			template.opsForList().leftPushAll(key, list);
    	}
    	else if(value instanceof SortedSet) {
			
    		SortedSet<Object> zset = (SortedSet<Object>) value;
    		
    		for (Object object : zset) {
    			template.opsForZSet().add(key, object, 0);
			}
    	}
    	else if(value instanceof Set) {
			
    		Set<Object> zset = (Set<Object>) value;
    		
    		for (Object object : zset) {
    			template.opsForSet().add(key, object);
			}
    	}
    	
    }
    
  /**
   * 从Redis取出String类型的数据
   * @param key 主键
   * @return String类型的值
   */
    public String getString(String key) {
    	
        return (String) template.opsForValue().get(key);
    }
 
    /**
     * 从Redis取出Hash类型的数据
     * @param <HK>
     * @param <HV>
     * @param key
     * @return
     */
    public <HK, HV> BoundHashOperations<String, HK, HV> getHash(String key) {
    	
        return template.boundHashOps(key);
    }

    /**
     * 从Redis取出List类型的数据
     * @param key
     * @return
     */
    public BoundListOperations<String, Object> getList(String key) {
	
    	return template.boundListOps(key);
    }

    /**
     * 从Redis取出Set类型的数据
     * @param key
     * @return
     */
    public BoundSetOperations<String, Object> getSet(String key) {
	
    	return template.boundSetOps(key);
    }

    /**
     * 从Redis取出ZSet类型的数据
     */
    public BoundZSetOperations<String, Object> getSortedSet(String key) {
	
    	return template.boundZSetOps(key);
    }
    
}

