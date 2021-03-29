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

### 1.4 抽象类和接口

#### 1.4 一句话总结

抽象类**不能实例化**，必须由**子类实现**。一个类只能**继承一个**抽象类，但可以**实现多个**接口。



#### 1.4.1 抽象类与接口的特性

**抽象类：**

- 抽象方法必须为public、protected。缺省时为public
- 抽象类不能直接用来创建对象，必须由子类继承并实现其父类方法才能创建对象；
- 只要包含一个抽象方法的抽象类，该方法必须要定义成抽象类，不管是否还包含有其他方法。

**接口：**

- 接口可以包含方法，方法会被隐式地指定为public abstract方法且只能是public abstract方法（用其他关键字，比如private、protected、static、 final等修饰会报编译错误），并且接口中所有的方法不能有具体的实现，也就是说，接口中的方法必须都是抽象方法；
- 一个类可以同时继承多个接口，且需要实现所继承接口的所有方法。

**区别：**

- 抽象类可以提供成员方法的实现细节，而接口中只能存在public abstract 方法；
- 抽象类中的成员变量可以是各种类型的，而接口中的成员变量只能是public static final类型的；
- 接口中不能含有静态代码块以及静态方法，而抽象类可以有静态代码块和静态方法
- 一个类只能继承一个抽象类，而一个类却可以实现多个接口。

#### 1.4.2 什么时候使用抽象类？什么时候使用接口？

这个问题也困扰了我很久，从网上看到了一篇文章，感觉说的挺对。

抽象类表示它**是什么**，接口表示它能**做什么**。类似于名次和动词的区别。比如一个人他有眼睛、肤色，这些描述一个人的特征可以定义在抽象类中，而一个人的行为如打篮球，所以这些可以定义在接口中。

```java
//抽象类Person
abstract class Person {
    abstract void eyes();
    abstract void skin();
}

//接口 Action
public interface Action {
    void playBasketball();
}

// 有个中国人，他不会打篮球
public class Chinese extends Person {
    @Override
    void eyes() {
        System.out.print("我的眼睛是黑色的");
    }

    @Override
    void skin() {
        System.out.print("我的皮肤是黄色的");
    }
}

// 有个俄罗斯人，他会打篮球
public class Russian extends Person implements Action{
    @Override
    void eyes() {
        System.out.print("我的眼睛是黑色的");
    }

    @Override
    void skin() {
        System.out.print("我的皮肤是白色的");
    }

    @Override
    public void playBasketball() {
        System.out.print("我能扣篮");
    }
}
```

