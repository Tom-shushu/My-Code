package JUC.collection;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * ���߳��������Դ������������ɱ
 * �����һ��ǿ��֮���ͷ���Դ����һ��������
 *  
 *  --,       .--,
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
 * <p>Title: SemaphoreDemo</p>  
 * <p>Description: </p>  
 * @author �ܺ�  
 * @date 2019��8��3��  
 *
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//ģ������ͣ��λ
        for (int i = 1; i <= 6; i++) {//ģ��6������
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t������λ");
                    try {
                        TimeUnit.SECONDS.sleep(3);//ͣ��3s
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "\tͣ��3s���뿪��λ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, "Car " + i).start();
        }
    }
}