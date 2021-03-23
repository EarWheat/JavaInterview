package Base;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/3/22 下午4:26
 * @desc ==和Equals的区别
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public class Equals {
    /**
     * String里面的Equals。
     * 1、使用""修饰的字符串会被加入到常量池中,当再次用 ""创建的时候,会首先从常量池中去获取。字符串常量池存在于运行时常量池中。也就存在于方法区中。
     * 2、String重写了equals方法，比较的是值。
     * 3、"" + new String 并赋值，赋值的是对象地址的引用。
     */
    public static void main(String[] args) {
        String s1 = "hello";    // s1指向的hello属于方法区的常量池
        String s2 = "hello";    // s1指向的hello属于方法区的常量池
        System.out.println(s1.equals(s2));  // true；值比较
        System.out.println(s1 == s2);   // true；内存地址比较
        String s3 = new String("hello"); // 在堆中新申请一个空间
        System.out.println(s3.equals(s1));  // true；值比较
        System.out.println(s1 == s3);   // false；地址比较
        String s4 = "hel" + "lo";
        System.out.println(s4.equals(s1));  // true; 值比较
        System.out.println(s1 == s4);   // true; 常量池拼接，仍指向常量池
        String s5 = "hel" + new String("lo");   // String是final的，"hel"是常量池，new String("lo")指向的是一个对象地址，=赋值之后，是将新的s5指向一个新的地址引用
        System.out.println(s5.equals(s1));  // true; 值比较
        System.out.println(s1 == s5);    // false; s5指向是新的引用地址。
    }
}
