package JUC.lock;

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
public class SynchronizedReentrantDemo {

	public static void main(String[] args) {
		Phone phone = new Phone();
		new Thread(()->{
			try {
			phone.sendSMS();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		},"Thread1").start();
	
	new Thread(()->{
		try {
		phone.sendSMS();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	},"Thread2").start();
}
}
	class Phone{
		public synchronized void sendSMS() throws InterruptedException {
			System.out.println(Thread.currentThread().getName()+"\t =========������sendSMS()======");
			Thread.sleep(3000);
			sendEmail();
		}
		
		public synchronized void sendEmail() throws InterruptedException {
			System.out.println(Thread.currentThread().getName()+"\t =========������sendEmail()======");
		}
	}

