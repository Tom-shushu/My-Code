package �̴߳���;

	/**
	 * �̳�Thread��ʵ�ֶ��߳�
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
	 * <p>Title: MyThread</p>  
	 * <p>Description: </p>  
	 * @author �ܺ�  
	 * @date 2019��8��2��  
	 *
	 */
/**
 * ͨ���̳�Threadʵ���߳�
 */
public class MyThread1 extends Thread{

	private int i = 0 ;

    @Override
    public void run() {
        for(;i<50;i++){
            System.out.println(Thread.currentThread().getName() + " is running " + i );
        }
    }

    public static void main(String[] args) {
        for(int j=0;j<50;j++){
        	if(j==20) {
        		new MyThread1().start() ;
                new MyThread1().start() ;
        	}
        }
    }
}