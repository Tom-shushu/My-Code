package JUC.collection;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * set��ȫ������
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
 * <p>Title: HashSetTest</p>  
 * <p>Description: </p>  
 * @author �ܺ�  
 * @date 2019��8��3��  
 *
 */
public class HashSetTest {

	public static void main(String[] args) {
		//notSafe();
		safe1();
		safe2();
	}
	//java.util.ConcurrentModificationException
	/**
	 * ���������޸ĵ���
	 * һ��������д�룬��һ��ͬѧ�����ᣬ�������ݲ�һ�£������޸��쳣
	 */
	public static void  notSafe() {
		Set<String> list = new HashSet<String>();
		for (int i = 0; i <= 30; i++) {
			new Thread(()->{
//				for (int j = 0; j <= 100; j++) {
//					list.add(j);
//				}
				list.add(UUID.randomUUID().toString().substring(0,8));
				System.out.println(list);
			},"Thread" + i).start();
		}
	}
	//ʹ��Collections.synchronizedSet(new HashSet<>())
	public static void  safe1() {
		Set<Integer> list = Collections.synchronizedSet(new HashSet<>());
		for (int i = 0; i <= 30; i++) {
			new Thread(()->{
				for (int j = 0; j <= 100; j++) {
					list.add(j);
				}
				//list.add(UUID.randomUUID().toString().substring(0,8));
				System.out.println(list);
			},"Thread" + i).start();
		}
	}	
	
	/*
	 * CopyOnWriteArrayList.add������
	 * CopyOnWrite������дʱ���ƣ���һ��Ԫ�����������ʱ��
	 * ��ֱ������ǰ����Object[]��ӣ������Ƚ���ǰ����Object[]����copy�����Ƴ�һ���µ�����Object[] newElements��
	 * �ú��µ��������Ԫ�أ������Ԫ��֮���ٽ�ԭ����������ָ���µ�����setArray(newElements),
	 * ���������Զ�CopyOnWrite�������в����Ķ���������Ҫ��������Ϊ��ǰ������������κ�Ԫ�أ�
	 * ����CopyOnWrite����Ҳ��һ�ֶ�д�����˼�룬����д��ͬ������
	 */

	public static void  safe2() {
		Set<String> list = new CopyOnWriteArraySet<String>();
		for (int i = 0; i <= 30; i++) {
			new Thread(()->{
				list.add(UUID.randomUUID().toString().substring(0,8));
				System.out.println(list);
			},"Thread" + i).start();
		}
	}	
	
}
