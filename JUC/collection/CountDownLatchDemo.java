package JUC.collection;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 1.������һ�������߳�һֱ�ȴ���֪�������̵߳Ĳ���ִ�������ִ��
 *           ���磬Ӧ�ó�������߳�ϣ���ڸ���������ܷ�����߳��Ѿ��������еĿ�ܷ���֮����ִ��
   2.CountDownLatch��Ҫ��������������һ�������̵߳���await()����ʱ�������̻߳ᱻ������
   	�����̵߳���countDown()�����Ὣ��������1������������ֵ��Ϊ0ʱ��
	�����await()�������������̲߳Żᱻ���ѣ�����ִ��
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
 * <p>Title: CountDownLatchDemo</p>  
 * <p>Description: </p>  
 * @author �ܺ�  
 * @date 2019��8��3��  
 *
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
     general();
//      countDownLatchTest();
  }

  public static void general() throws InterruptedException{
	  //��������������0
	  CountDownLatch cdl = new CountDownLatch(6);
	  
	  
      for (int i = 1; i <= 6; i++) {
          new Thread(() -> {
              System.out.println(Thread.currentThread().getName()+"\t������ϰ���뿪����");
              cdl.countDown();
          }, "Thread-->"+i).start();
      }
      cdl.await();
           
//      while (Thread.activeCount()>2){
//          try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
//      }
      
      System.out.println(Thread.currentThread().getName()+"\t=====�೤����������");
  }

//  public static void countDownLatchTest() throws InterruptedException {
//      CountDownLatch countDownLatch = new CountDownLatch(6);
//      for (int i = 1; i <= 6; i++) {
//          new Thread(() -> {
//              System.out.println(Thread.currentThread().getName()+"\t����");
//              countDownLatch.countDown();
//          }, CountryEnum.forEach_CountryEnum(i).getRetMessage()).start();
//      }
//      countDownLatch.await();
//      System.out.println(Thread.currentThread().getName()+"\t=====��ͳһ");
//  }
}
