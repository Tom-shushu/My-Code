package JUC.volatileTest;

/**
 * volatile��Ӧ�ó���������ģʽ��˫��У������
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
 * <p>Title: SingletonDemo2</p>  
 * <p>Description: </p>  
 * @author �ܺ�  
 * @date 2019��8��2��  
 *
 */
public class SingletonDemo2 {
    private static volatile SingletonDemo2 instance = null;

    private SingletonDemo2() {
        System.out.println(Thread.currentThread().getName() + "\t ���췽��SingletonDemo����");
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
        //���췽��ֻ�ᱻִ��һ��
//        System.out.println(getInstance() == getInstance());
//        System.out.println(getInstance() == getInstance());
//        System.out.println(getInstance() == getInstance());

        //���췽������һЩ�����ִ�ж��
		
		
		  for (int i = 0; i < 10; i++) {
			  new Thread(() -> {
		  SingletonDemo2.getInstance();
		  }, "Thread " + i).start();
			  }
		 
    }
}