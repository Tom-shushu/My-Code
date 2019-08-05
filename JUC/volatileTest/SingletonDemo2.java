package JUC.volatileTest;

/**
 * volatile的应用场景（单例模式的双重校验锁）
 * .--,       .--,
 *( (  \.---./  ) )
 * '.__/o   o\__.'   _______________________________________________________
 *    {=  ^  =}  <===代码无BUG!                                              |
 *     >  -  <       | Code is far away from bug with the animal protecting|
 *    /       \      ```````````````````````````````````````````````````````
 *   //       \\
 *  //|   .   |\\
 *  "'\       /'"_.-~^`'-.
 *     \  _  /--'         `
 *   ___)( )(___       
 *  (((__) (__)))    
 *
 * <p>Title: SingletonDemo2</p>  
 * <p>Description: </p>  
 * @author 周红  
 * @date 2019年8月2日  
 *
 */
public class SingletonDemo2 {
    private static volatile SingletonDemo2 instance = null;

    private SingletonDemo2() {
        System.out.println(Thread.currentThread().getName() + "\t 构造方法SingletonDemo（）");
    }

    public static SingletonDemo2 getInstance() {

        if (instance == null) {
            synchronized (SingletonDemo2.class) {
                if (instance == null) {
                    instance = new SingletonDemo2();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        //构造方法只会被执行一次
//        System.out.println(getInstance() == getInstance());
//        System.out.println(getInstance() == getInstance());
//        System.out.println(getInstance() == getInstance());

        //构造方法会在一些情况下执行多次
		
		
		  for (int i = 0; i < 10; i++) {
			  new Thread(() -> {
		  SingletonDemo2.getInstance();
		  }, "Thread " + i).start();
			  }
		 
    }
}