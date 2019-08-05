package JUC.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * List安全问题解决
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
 * <p>Title: ContainerNotSafeDemo</p>  
 * <p>Description: </p>  
 * @author 周红  
 * @date 2019年8月3日  
 *
 */
public class ListSefe {

    public static void main(String[] args) {
//      notSafe();
//      vectorTest();
//      collectionsTest();
//      copyOnWriteArrayListTest();
  }

  /**
   * 故障现象
   * java.util.ConcurrentModificationException
   */
  public static void notSafe() {
      List<String> list = new ArrayList<>();
      for (int i = 1; i <= 30; i++) {
          new Thread(() -> {
              list.add(UUID.randomUUID().toString().substring(0, 8));
              System.out.println(list);
          }, "Thread " + i).start();
      }
  }

  /**
   * 解决方案1：使用Vector
   */
  public static void vectorTest(){
      List<String> list = new Vector<>();
      for (int i = 1; i <= 30; i++) {
          new Thread(() -> {
              list.add(UUID.randomUUID().toString().substring(0, 8));
              System.out.println(list);
          }, "Thread " + i).start();
      }
  }
  /**
   * 解决方案2
   * 使用Collections辅助类
   */
  public static void collectionsTest(){
      List<String> list = Collections.synchronizedList(new ArrayList<>());
      for (int i = 1; i <= 30; i++) {
          new Thread(() -> {
              list.add(UUID.randomUUID().toString().substring(0, 8));
              System.out.println(list);
          }, "Thread " + i).start();
      }
  }
  /**
   * 解决方案3
   * CopyOnWriteArrayList
   */
  public static void copyOnWriteArrayListTest(){
      List<String> list = new CopyOnWriteArrayList<>();
      for (int i = 1; i <= 3; i++) {
          new Thread(() -> {
              list.add(UUID.randomUUID().toString().substring(0, 8));
              System.out.println(list);
          }, "Thread " + i).start();
      }
  }
}
	
