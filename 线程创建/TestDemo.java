package �̴߳���;
class MyThread implements Runnable {
	private int ticket = 10 ; 
	// һ��ʮ��Ʊ
	@Override 
	public void run() {
		while(this.ticket>0) {
			// ����Ʊ 
			synchronized (this) {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
			}
			// ģ�������ӳ�
			System.out.println(Thread.currentThread().getName()+",����" +this.ticket -- +" ��Ʊ");
		}
	}
}
public class TestDemo {
	public static void main(String[] args) {
	MyThread mt = new MyThread() ;
	new Thread(mt,"��ţA").start();
	new Thread(mt,"��ţB").start();
	new Thread(mt,"��ţC").start();
} 
}