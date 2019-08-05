package 线程创建;
class MyThread implements Runnable {
	private int ticket = 10 ; 
	// 一共十张票
	@Override 
	public void run() {
		while(this.ticket>0) {
			// 还有票 
			synchronized (this) {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
			}
			// 模拟网络延迟
			System.out.println(Thread.currentThread().getName()+",还有" +this.ticket -- +" 张票");
		}
	}
}
public class TestDemo {
	public static void main(String[] args) {
	MyThread mt = new MyThread() ;
	new Thread(mt,"黄牛A").start();
	new Thread(mt,"黄牛B").start();
	new Thread(mt,"黄牛C").start();
} 
}