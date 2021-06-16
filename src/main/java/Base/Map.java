package Base;

import java.util.HashMap;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/3/30 下午4:43
 * @desc Map研究
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public class Map {

    public String myMethod(){
       return "Hello";
    }

    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("hello","world");
    }
}
