package com.zhouhongs.ssoserver.util;

import java.util.HashMap;
import java.util.Map;


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
 * <p>Title: TokenUtil</p>  
 * <p>Description: </p>  
 * @author 周红  
 * @date 2019年6月5日  
 *token工具类，管理token
 */  
public class TokenUtil {
	
	
	public static Map<String,String> TOKEN_MAP=new HashMap<String, String>();
	
	public static void put(String token, String userName) {
		TOKEN_MAP.put(token, userName);

	}

	public static String get(String token) {
		return TOKEN_MAP.get(token);

	}

	public static void remove(String token) {
		TOKEN_MAP.remove(token);

	}
}
