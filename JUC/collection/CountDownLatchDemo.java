package JUC.collection;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 1.它允许一个或多个线程一直等待，知道其他线程的操作执行完后再执行
 *           例如，应用程序的主线程希望在负责启动框架服务的线程已经启动所有的框架服务之后再执行
   2.CountDownLatch主要有两个方法，当一个或多个线程调用await()方法时，调用线程会被阻塞。
   	其他线程调用countDown()方法会将计数器减1，当计数器的值变为0时，
	因调用await()方法被阻塞的线程才会被唤醒，继续执行
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
 * <p>Title: CountDownLatchDemo</p>  
 * <p>Description: </p>  
 * @author 周红  
 * @date 2019年8月3日  
 *
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
     general();
//      countDownLatchTest();
  }

  public static void general() throws InterruptedException{
	  //从六减法，减到0
	  CountDownLatch cdl = new CountDownLatch(6);
	  
	  
      for (int i = 1; i <= 6; i++) {
          new Thread(() -> {
              System.out.println(Thread.currentThread().getName()+"\t上完自习，离开教室");
              cdl.countDown();
          }, "Thread-->"+i).start();
      }
      cdl.await();
           
//      while (Thread.activeCount()>2){
//          try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
//      }
      
      System.out.println(Thread.currentThread().getName()+"\t=====班长最后关门走人");
  }

//  public static void countDownLatchTest() throws InterruptedException {
//      CountDownLatch countDownLatch = new CountDownLatch(6);
//      for (int i = 1; i <= 6; i++) {
//          new Thread(() -> {
//              System.out.println(Thread.currentThread().getName()+"\t被灭");
//              countDownLatch.countDown();
//          }, CountryEnum.forEach_CountryEnum(i).getRetMessage()).start();
//      }
//      countDownLatch.await();
//      System.out.println(Thread.currentThread().getName()+"\t=====秦统一");
//  }
}
