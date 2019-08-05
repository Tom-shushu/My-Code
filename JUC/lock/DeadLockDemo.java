package JUC.lock;

/*
 * ���������ķ�����
 * 1.����һ���߳�ͬʱ��ȡ�����
 * 2.����һ���߳�������ͬʱռ�ö����Դ��������֤ÿ����ֻռ��һ����Դ
 * 3.����ʹ�ö�ʱ����ʹ��lock.tryLock(timeout)������ڲ�������
 * 4.�������ݿ����������ͽ���������һ�����ݿ��������������ֽ���ʧ�ܵ����
 */
public class DeadLockDemo {

	private static String A = "A";
	private static String B = "B";
	
	public static void main(String[] args) {
		new DeadLockDemo().deadLock();
	}
	
	private void deadLock() {
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				synchronized (A) {
					try {
						Thread.currentThread().sleep(2000);
					} catch (InterruptedException e) {
					e.printStackTrace();
					}
					
					synchronized (B) {
						System.out.println("1");
					}
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				synchronized (B) {
					synchronized (A) {
						System.out.println("2");
					}
				}
			}
		});
		t1.start();
		t2.start();
	}
}
