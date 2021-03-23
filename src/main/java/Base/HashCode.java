package Base;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/3/23 下午4:38
 * @desc equals返回true，hashcode一定相同，hashcode相同equals不一定为true
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public class HashCode {
    public static void main(String[] args) {
        String str1 = "重地";
        String str2 = "通话";
        System.out.println("str1 hash code:" + str1.hashCode());    // 1179395
        System.out.println("str2 hash code:" + str1.hashCode());    // 1179395
        System.out.println(str1.equals(str2));  // false;
    }
}
