package �̴߳���;

/**
 * ʵ��Runable()�ӿ�ʵ�ֶ��߳�
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
 * <p>Title: Runable</p>  
 * <p>Description: </p>  
 * @author �ܺ�  
 * @date 2019��8��2��  
 *
 */

public class MyThread2 implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 10; i++) {
			System.out.println(i);
		}
	}
	
	public static void main(String[] args) {
		MyThread2 m1 = new MyThread2();
		MyThread2 m2 = new MyThread2();
		MyThread2 m3 = new MyThread2();
		MyThread2 m4 = new MyThread2();
		new Thread(m1).start();
		new Thread(m1).start();
		new Thread(m1).start();
		new Thread(m1).start();
	}
	
}
