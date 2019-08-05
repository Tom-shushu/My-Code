package 线程创建;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池创建
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
 * <p>Title: MyThread4</p>  
 * <p>Description: </p>  
 * @author 周红  
 * @date 2019年8月2日  
 *
 */
public class MyThread4 {

	public static void main(String[] args) {
//		//一池五个处理线程
//		//ExecutorService threadPool = Executors.newFixedThreadPool(5);
//		//一池一个处理线程
//		//ExecutorService threadPool = Executors.newSingleThreadExecutor();
//		//一池N个处理线程（够了一个线程，不够加）
//		ExecutorService threadPool = Executors.newCachedThreadPool();
//		
//		//模拟10个用户办理业务，每个用户就是一个来自外部的请求线程
//		try {
//			for (int i = 1; i <= 100; i++) {
//				threadPool.execute(() -> {
//					System.out.println(Thread.currentThread().getName() + "\t办理业务");
//				});
//			}
//				
//		} finally {
//			// TODO: handle finally clause
//		}
		
		
/*
 * 实际开发中创建线程池的方法：		
 */
	ExecutorService threadPool = new ThreadPoolExecutor(3, 5, 1L,
			TimeUnit.SECONDS, 
			new LinkedBlockingDeque<>(3),
			Executors.defaultThreadFactory(),
			new ThreadPoolExecutor.CallerRunsPolicy());
			//new ThreadPoolExecutor.AbortPolicy()
			//new ThreadPoolExecutor.CallerRunsPolicy();
			//new ThreadPoolExecutor.DiscardOldestPolicy();
			//new ThreadPoolExecutor.DiscardPolicy();
	
	try {
		for (int i = 1; i <= 10; i++) {
			threadPool.execute(() -> {
				System.out.println(Thread.currentThread().getName() + "\t处理业务");
			});
		}
	} finally {
		// TODO: handle finally clause
	}
	
			}
	
}
