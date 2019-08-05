package JUC.CAS;

import java.util.concurrent.atomic.AtomicInteger;

public class CASTest {

	/**
	 * 1.cas:比较并交换
	 */
	public static void main(String[] args) {
		checkCAS();
	}
	public static void checkCAS() {
		AtomicInteger atomicInteger = new AtomicInteger(5);
		System.out.println(atomicInteger.compareAndSet(5, 100) +"更改后的值"+ atomicInteger.get());
		System.out.println(atomicInteger.compareAndSet(5, 100) +"更改后的值"+ atomicInteger.get());
		System.out.println(atomicInteger.compareAndSet(100, 500) +"更改后的值"+ atomicInteger.get());
		
	}
}
