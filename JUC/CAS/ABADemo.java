package JUC.CAS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {

	   static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
	    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);

	    public static void main(String[] args) {
	    	System.out.println("============================以下时ABA问题的解决=================================");
	        new Thread(() -> {
	        	//一条线程将100 改为 101之后又改回来
	        	System.out.println("A线程将100 改为 101之后又改回来");
	            atomicReference.compareAndSet(100, 101);
	            atomicReference.compareAndSet(101, 100);
	        }, "Thread 1").start();

	        
	        new Thread(() -> {
	            try {
	                //保证线程1完成一次ABA操作
	                TimeUnit.SECONDS.sleep(1);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            System.out.println(atomicReference.compareAndSet(100, 2019) + "\t" + atomicReference.get());
	            System.out.println("B线程误以为100没有改变将100改为2019，成功的实现了比较交换");
	        }, "Thread 2").start();
	        try {
	            TimeUnit.SECONDS.sleep(2);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        
	        
	        System.out.println("============================以下时ABA问题的解决=================================");
	       //时间戳的原子引用
	        new Thread(() -> {
	        	System.out.println("A线程将100 改为 101之后又改回来");
	            int stamp = atomicStampedReference.getStamp();
	            System.out.println(Thread.currentThread().getName() + "\t线程A操作初始版本号" + stamp);
	            try {
	                TimeUnit.SECONDS.sleep(2);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
	            System.out.println(Thread.currentThread().getName() + "\t线程A操作第1次版本号" + atomicStampedReference.getStamp());
	            atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
	            System.out.println(Thread.currentThread().getName() + "\t线程A操作第2次版本号" + atomicStampedReference.getStamp());
	        }, "Thread 3").start();

	        new Thread(() -> {
	        	
	            int stamp = atomicStampedReference.getStamp();
	            System.out.println(Thread.currentThread().getName() + "\t线程B操作版本号" + stamp);
	            try {
	                TimeUnit.SECONDS.sleep(4);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            boolean result = atomicStampedReference.compareAndSet(100, 2019, stamp, stamp + 1);
	            System.out.println("B线程进行CAS操作，由于版本号不同，CAS操作失败，打印出原来的值");
	            System.out.println(Thread.currentThread().getName() + "\t修改是否成功" + result + "\t当前最新实际版本号：" + atomicStampedReference.getStamp());
	            System.out.println(Thread.currentThread().getName() + "\t当前最新实际值：" + atomicStampedReference.getReference());
	        }, "Thread 4").start();
	    }
}
