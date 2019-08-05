package JUC.collection;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * set安全问题解决
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
 * <p>Title: HashSetTest</p>  
 * <p>Description: </p>  
 * @author 周红  
 * @date 2019年8月3日  
 *
 */
public class HashSetTest {

	public static void main(String[] args) {
		//notSafe();
		safe1();
		safe2();
	}
	//java.util.ConcurrentModificationException
	/**
	 * 并发争抢修改导致
	 * 一个人正在写入，另一个同学来抢夺，导致数据不一致，并发修改异常
	 */
	public static void  notSafe() {
		Set<String> list = new HashSet<String>();
		for (int i = 0; i <= 30; i++) {
			new Thread(()->{
//				for (int j = 0; j <= 100; j++) {
//					list.add(j);
//				}
				list.add(UUID.randomUUID().toString().substring(0,8));
				System.out.println(list);
			},"Thread" + i).start();
		}
	}
	//使用Collections.synchronizedSet(new HashSet<>())
	public static void  safe1() {
		Set<Integer> list = Collections.synchronizedSet(new HashSet<>());
		for (int i = 0; i <= 30; i++) {
			new Thread(()->{
				for (int j = 0; j <= 100; j++) {
					list.add(j);
				}
				//list.add(UUID.randomUUID().toString().substring(0,8));
				System.out.println(list);
			},"Thread" + i).start();
		}
	}	
	
	/*
	 * CopyOnWriteArrayList.add方法：
	 * CopyOnWrite容器即写时复制，往一个元素添加容器的时候，
	 * 不直接往当前容器Object[]添加，而是先将当前容器Object[]进行copy，复制出一个新的容器Object[] newElements，
	 * 让后新的容器添加元素，添加完元素之后，再将原容器的引用指向新的容器setArray(newElements),
	 * 这样做可以对CopyOnWrite容器进行并发的读，而不需要加锁，因为当前容器不会添加任何元素，
	 * 所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器
	 */

	public static void  safe2() {
		Set<String> list = new CopyOnWriteArraySet<String>();
		for (int i = 0; i <= 30; i++) {
			new Thread(()->{
				list.add(UUID.randomUUID().toString().substring(0,8));
				System.out.println(list);
			},"Thread" + i).start();
		}
	}	
	
}
