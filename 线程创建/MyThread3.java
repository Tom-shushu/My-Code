package �̴߳���;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * ʵ��Callable�ӿڣ���дCall����
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
 * <p>Title: MyThread3</p>  
 * <p>Description: </p>  
 * @author �ܺ�  
 * @date 2019��8��2��  
 *
 */
public class MyThread3 implements Callable<String> {
	private int ticket = 10 ; // һ��10��Ʊ 
	@Override 
	public String call() throws Exception { 
		while(this.ticket>0){ 
			System.out.println("ʣ��Ʊ����"+this.ticket -- );
			}
		return "Ʊ�����ˣ��´ΰɡ�����" ; 
		} 
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FutureTask<String> task = new FutureTask<>(new MyThread3()) ;
		new Thread(task).start(); 	
		
		new Thread(task).start(); 
		
		}
	
	
	}
