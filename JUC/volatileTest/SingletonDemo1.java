package JUC.volatileTest;

public class SingletonDemo1 {
    private static volatile SingletonDemo1 instance = null;

    private SingletonDemo1() {
        System.out.println(Thread.currentThread().getName() + "\t ���췽��SingletonDemo����");
    }

    public static SingletonDemo1 getInstance() {

        
                if (instance == null) {
                    instance = new SingletonDemo1();
        }
        return instance;
    }

    public static void main(String[] args) {

        //���췽������һЩ�����ִ�ж��
		
		
		  for (int i = 0; i < 10; i++) {
			  new Thread(() -> {
		  SingletonDemo1.getInstance();
		  }, "Thread " + i).start();
			  }
		 
    }
}