# JavaInterview
Java面试题-实践

## Java基础
### 1.1 ==和equals
#### 1.1.1 一句话总结：
**==**是比较的内存地址，**equals**在不重写的情况下也是比较的内存地址，但是**String**重写了**equals**方法，变成了比较值是否相等，即内容。

```java
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
```





#### 1.1.2 Hash和内存地址

##### hashCode与equals

**hash值相等，equals一定相等吗，equals相等，hash值一定相等吗？**

1.对于普通对象来说（没有重写equals方法），equals返回true代表两个相同的对象，所以必然有相同的hashCode

2.但是具有相同的hashCode不代表是同一对象，即equals不一定相等。

```java
public class HashCode {
    public static void main(String[] args) {
        String str1 = "重地";
        String str2 = "通话";
        System.out.println("str1 hash code:" + str1.hashCode());    // 1179395
        System.out.println("str2 hash code:" + str1.hashCode());    // 1179395
        System.out.println(str1.equals(str2));  // false;
    }
}
```

3.Java中出于安全问题的考虑，无法直接查看对象在内存的地址，所以一般通过hashCode来反映内存地址，但是hashCode和内存地址**没有任何关联**。一定不要误认为hashCode就是内存地址

#### 1.1.3 为什么重写equals方法必须重写hashcode方法

在Map中，为了保证Key的唯一性，会先比较对象的hashCode，如果hashCode一致，则会继续比较对象的equals方法，如果equals返回的也为true，则会存入或者覆盖。如果只重写了equals方法，会造成不同的两个对象（拥有相同的hashCode），结果重写的equals返回true，最终会导致不同的对象，在Map中拥有相同的地址（而且非hash冲突，Map在hash冲突之后会有冲突解决策略，但是依赖的是equals）。

![image-20210307153243348](https://img-blog.csdnimg.cn/20201127230059231.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L01pbmRfcHJvZ3JhbW1vbmtleQ==,size_16,color_FFFFFF,t_70)

### 1.2 static和final
#### 1.2.1一句话总结

static重在**强调只有一份**，final重在**强调不变**



#### 1.2.2 Static

**修饰变量**：static强调的是一份副本，但是是可变的。但因为是一份副本，所有类实例**共享**，其变化是对所有可见的。

**修饰方法**：基本同变量，但是static的方法不能用abstract修饰。

**修饰代码块**：类加载之后就会执行代码块中的内容。

#### 1.2.3 Final

**修饰变量**：对于基本类型变量，初始化后不可修改其值。对于引用对象或引用数据类型，引用不可变，但引用的对象内容可变。

```java
public class StaticAndFinal {
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

```

**修饰方法**：不能被继承和修改

**修饰类**：不能被继承

