package �̴߳���;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * �̳߳ش���
 * .--,       .--,
 *( (  \.---./  ) )
 * '.__/o   o\__.'   _______________________________________________________
 *    {=  ^  =}  <===������BUG!                                              |
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
 * @author �ܺ�  
 * @date 2019��8��2��  
 *
 */
public class MyThread4 {

	public static void main(String[] args) {
//		//һ����������߳�
//		//ExecutorService threadPool = Executors.newFixedThreadPool(5);
//		//һ��һ�������߳�
//		//ExecutorService threadPool = Executors.newSingleThreadExecutor();
//		//һ��N�������̣߳�����һ���̣߳������ӣ�
//		ExecutorService threadPool = Executors.newCachedThreadPool();
//		
//		//ģ��10���û�����ҵ��ÿ���û�����һ�������ⲿ�������߳�
//		try {
//			for (int i = 1; i <= 100; i++) {
//				threadPool.execute(() -> {
//					System.out.println(Thread.currentThread().getName() + "\t����ҵ��");
//				});
//			}
//				
//		} finally {
//			// TODO: handle finally clause
//		}
		
		
/*
 * ʵ�ʿ����д����̳߳صķ�����		
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
				System.out.println(Thread.currentThread().getName() + "\t����ҵ��");
			});
		}
	} finally {
		// TODO: handle finally clause
	}
	
			}
	
}
