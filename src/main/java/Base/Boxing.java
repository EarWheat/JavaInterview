package Base;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/3/23 下午3:51
 * @desc 自动装箱和自动拆箱
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public class Boxing {
    /**
     * 装箱过程是通过调用包装器的valueOf方法实现的，而拆箱过程是通过调用包装器的 xxxValue方法实现的。（xxx代表对应的基本数据类型）。
     * 1、Integer 与 int比较，先将Integer转换成int类型，再做值比较，所以返回的是true。
     * 2、基本数据类型是进行值比较。
     */
    public static void main(String[] args) {
        Integer a = 100;    // Integer a = Integer.valueOf(100);
        int b = a;    // int b = Integer.intValue;
        int c = 100;    // 上面两步
        System.out.println(a == b); // true 自动装箱
        System.out.println(a == c); // true

        Integer a1 = 200;
        Integer b1 = 200;
        int c1 = 200;
        System.out.println(a1 == b1);   // false
        System.out.println(a1 == c1);   // 一个Integer 与 int比较，先将Integer转换成int类型，再做值比较，所以返回的是true。

    }
}
