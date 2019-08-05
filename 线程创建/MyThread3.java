package 线程创建;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 实现Callable接口，复写Call方法
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
 * <p>Title: MyThread3</p>  
 * <p>Description: </p>  
 * @author 周红  
 * @date 2019年8月2日  
 *
 */
public class MyThread3 implements Callable<String> {
	private int ticket = 10 ; // 一共10张票 
	@Override 
	public String call() throws Exception { 
		while(this.ticket>0){ 
			System.out.println("剩余票数："+this.ticket -- );
			}
		return "票卖完了，下次吧。。。" ; 
		} 
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FutureTask<String> task = new FutureTask<>(new MyThread3()) ;
		new Thread(task).start(); 	
		
		new Thread(task).start(); 
		
		}
	
	
	}
