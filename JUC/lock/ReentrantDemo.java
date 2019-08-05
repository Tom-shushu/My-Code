package JUC.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁：
 * 指的时同一线程外层函数获得锁之后，内层递归函数仍然能获取该锁的代码，
 * 在同一个线程在外层方法获取锁的时候，在进入内层方法会自动获取锁，也就是说，线程可以进入任何一个它已经拥有的锁所同步着的代码块
 * 同步方法可以进入另一个同步方法
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
 * <p>Title: SynchronizedReentrantDemo</p>  
 * <p>Description: </p>  
 * @author 周红  
 * @date 2019年8月2日  
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

