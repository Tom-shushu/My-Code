package JUC.CAS;

import java.util.concurrent.atomic.AtomicInteger;

public class CASTest {

	/**
	 * 1.cas:�Ƚϲ�����
	 */
	public static void main(String[] args) {
		checkCAS();
	}
	public static void checkCAS() {
		AtomicInteger atomicInteger = new AtomicInteger(5);
		System.out.println(atomicInteger.compareAndSet(5, 100) +"���ĺ��ֵ"+ atomicInteger.get());
		System.out.println(atomicInteger.compareAndSet(5, 100) +"���ĺ��ֵ"+ atomicInteger.get());
		System.out.println(atomicInteger.compareAndSet(100, 500) +"���ĺ��ֵ"+ atomicInteger.get());
		
	}
}
