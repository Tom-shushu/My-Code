package JUC.volatileTest;

public class SingletonDemo1 {
    private static volatile SingletonDemo1 instance = null;

    private SingletonDemo1() {
        System.out.println(Thread.currentThread().getName() + "\t 构造方法SingletonDemo（）");
    }

    public static SingletonDemo1 getInstance() {

        
                if (instance == null) {
                    instance = new SingletonDemo1();
        }
        return instance;
    }

    public static void main(String[] args) {

        //构造方法会在一些情况下执行多次
		
		
		  for (int i = 0; i < 10; i++) {
			  new Thread(() -> {
		  SingletonDemo1.getInstance();
		  }, "Thread " + i).start();
			  }
		 
    }
}