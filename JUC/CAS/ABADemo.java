package JUC.CAS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {

	   static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
	    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);

	    public static void main(String[] args) {
	    	System.out.println("============================����ʱABA����Ľ��=================================");
	        new Thread(() -> {
	        	//һ���߳̽�100 ��Ϊ 101֮���ָĻ���
	        	System.out.println("A�߳̽�100 ��Ϊ 101֮���ָĻ���");
	            atomicReference.compareAndSet(100, 101);
	            atomicReference.compareAndSet(101, 100);
	        }, "Thread 1").start();

	        
	        new Thread(() -> {
	            try {
	                //��֤�߳�1���һ��ABA����
	                TimeUnit.SECONDS.sleep(1);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            System.out.println(atomicReference.compareAndSet(100, 2019) + "\t" + atomicReference.get());
	            System.out.println("B�߳�����Ϊ100û�иı佫100��Ϊ2019���ɹ���ʵ���˱ȽϽ���");
	        }, "Thread 2").start();
	        try {
	            TimeUnit.SECONDS.sleep(2);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        
	        
	        System.out.println("============================����ʱABA����Ľ��=================================");
	       //ʱ�����ԭ������
	        new Thread(() -> {
	        	System.out.println("A�߳̽�100 ��Ϊ 101֮���ָĻ���");
	            int stamp = atomicStampedReference.getStamp();
	            System.out.println(Thread.currentThread().getName() + "\t�߳�A������ʼ�汾��" + stamp);
	            try {
	                TimeUnit.SECONDS.sleep(2);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
	            System.out.println(Thread.currentThread().getName() + "\t�߳�A������1�ΰ汾��" + atomicStampedReference.getStamp());
	            atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
	            System.out.println(Thread.currentThread().getName() + "\t�߳�A������2�ΰ汾��" + atomicStampedReference.getStamp());
	        }, "Thread 3").start();

	        new Thread(() -> {
	        	
	            int stamp = atomicStampedReference.getStamp();
	            System.out.println(Thread.currentThread().getName() + "\t�߳�B�����汾��" + stamp);
	            try {
	                TimeUnit.SECONDS.sleep(4);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            boolean result = atomicStampedReference.compareAndSet(100, 2019, stamp, stamp + 1);
	            System.out.println("B�߳̽���CAS���������ڰ汾�Ų�ͬ��CAS����ʧ�ܣ���ӡ��ԭ����ֵ");
	            System.out.println(Thread.currentThread().getName() + "\t�޸��Ƿ�ɹ�" + result + "\t��ǰ����ʵ�ʰ汾�ţ�" + atomicStampedReference.getStamp());
	            System.out.println(Thread.currentThread().getName() + "\t��ǰ����ʵ��ֵ��" + atomicStampedReference.getReference());
	        }, "Thread 4").start();
	    }
}
