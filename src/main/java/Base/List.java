package Base;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/3/26 下午6:06
 * @desc ArrayList和LinkedList有什么区别。
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public class List {
    public static void main(String[] args) {

        Thread arrThread = new Thread(new Runnable() {
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                ArrayList<Integer> arrayList = new ArrayList<>();
                for (int i = 0; i < 1000000; i++) {
                    arrayList.add(i);
                }
                long end = System.currentTimeMillis();
                System.out.println("ArrList" + Thread.currentThread().getName() + ":" + (end - start));
            }
        });
        Thread linkedThread = new Thread(new Runnable() {
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                LinkedList<Integer> linkedList = new LinkedList<>();
                for (int i = 0; i < 1000000; i++) {
                    linkedList.add(i);
                }
                long end = System.currentTimeMillis();
                System.out.println("LinkedList" + Thread.currentThread().getName() + ":" + (end - start));
            }
        });
        linkedThread.start();
        arrThread.start();
    }
}
