package Base;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/3/22 下午5:40
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public class Person {
    Person(){};

    Person(String name, Integer age){
        this.name = name;
        this.age = age;
    }
    private String name;
    private Integer age;
}
