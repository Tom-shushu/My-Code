package JUC.collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Map��ȫ������
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
 * <p>Title: MapSafe</p>  
 * <p>Description: </p>  
 * @author �ܺ�  
 * @date 2019��8��3��  
 *
 */
public class MapSafe {
    public static void main(String[] args) {
        notSafe();
    }

    public static void notSafe() {
//������
    	//Map<String, String> map = new HashMap<>();
//���һ
    	//Map<String, String> map = new ConcurrentHashMap<>();
//�����
    	Map<String, String> map = Collections.synchronizedMap(new HashMap<>());

        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString());
                System.out.println(map);
            }, "Thread " + i).start();
        }
    }
}