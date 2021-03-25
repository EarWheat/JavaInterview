package Base;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/3/24 下午8:15
 * @desc static和final
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public class StaticAndFinal {
    public static String staticStr = "single one";
    public final String finalStr = "not change";
    public final int finalInt = 2;

    static class Person{
        String name;
        Integer age;
        Person(String name, Integer age){
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
    public static void main(String[] args) {
        StaticAndFinal staticAndFina = new StaticAndFinal();
      // staticAndFina.finalInt = 3; // 会报错：final修饰的变量不可变
        final Person person = new Person("张三",18);
        person.setAge(20);  // final修饰引用对象，内容可变
      //  person = new Person("李四",30); // 会报错：final修饰的person，不可指向新的引用。
    }
}
