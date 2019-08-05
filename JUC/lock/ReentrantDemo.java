package JUC.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ����������
 * ָ��ʱͬһ�߳���㺯�������֮���ڲ�ݹ麯����Ȼ�ܻ�ȡ�����Ĵ��룬
 * ��ͬһ���߳�����㷽����ȡ����ʱ���ڽ����ڲ㷽�����Զ���ȡ����Ҳ����˵���߳̿��Խ����κ�һ�����Ѿ�ӵ�е�����ͬ���ŵĴ����
 * ͬ���������Խ�����һ��ͬ������
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
 * <p>Title: SynchronizedReentrantDemo</p>  
 * <p>Description: </p>  
 * @author �ܺ�  
 * @date 2019��8��2��  
 *
 */
public class ReentrantDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Mobile m = new Mobile();
		new Thread(m).start();
		new Thread(m).start();
	}
}
	class Mobile implements Runnable{

		Lock lock = new ReentrantLock();
		@Override
		public void run() {
		get();
		}
		public void get() {
			lock.lock();
			try {
				System.out.println(Thread.currentThread().getName()+ "\t");
			} finally {
				lock.unlock();
			}
		}

		public void set() {
			lock.lock();
			try {
				System.out.println(Thread.currentThread().getName()+"\t");
			} finally {
				lock.unlock();
			}
		}
	}

