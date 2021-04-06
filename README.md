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


### 1.5 HashMap源码解读

终于要开始研究HashMap了，之前欠了不少的债，这次一定要研究透彻。最好自己实现一遍。本文是关于自己解读HashMap源码的文章，我是从上往下读的，看到什么有意思的方法就会记录。

#### 1.5.1 AbstractMap和Map

首先我们来看看HashMap的父类和关键接口。

```java
public class HashMap<K,V> extends AbstractMap<K,V>
    implements Map<K,V>, Cloneable, Serializable {

    private static final long serialVersionUID = 362498820763181265L;
}
```

**AbstractMap** 是一个抽象类，里面包含了size(), isEmpty(), put(), get()等方法。

来看看最重要的put()和get()。

```java
/**
     * {@inheritDoc}
     *
     * @implSpec
     * This implementation iterates over <tt>entrySet()</tt> searching
     * for an entry with the specified key.  If such an entry is found,
     * the entry's value is returned.  If the iteration terminates without
     * finding such an entry, <tt>null</tt> is returned.  Note that this
     * implementation requires linear time in the size of the map; many
     * implementations will override this method.
     *
     * @throws ClassCastException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     */
    public V get(Object key) {
        Iterator<Entry<K,V>> i = entrySet().iterator();
        if (key==null) {
            while (i.hasNext()) {
                Entry<K,V> e = i.next();
                if (e.getKey()==null)
                    return e.getValue();
            }
        } else {
            while (i.hasNext()) {
                Entry<K,V> e = i.next();
                if (key.equals(e.getKey()))
                    return e.getValue();
            }
        }
        return null;
    }

		/**
     * {@inheritDoc}
     *
     * @implSpec
     * This implementation always throws an
     * <tt>UnsupportedOperationException</tt>.
     *
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws ClassCastException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     * @throws IllegalArgumentException      {@inheritDoc}
     */
    public V put(K key, V value) {
        throw new UnsupportedOperationException();
    }

```

get()方法中有个有意思的地方在于**if(key==null)**，可以看到key为null的情况下也是可以获取value值的。



**Map**:是非常关键的接口了，其中最重要的莫过于Entry<K, V>了。

```java
    /**
     * A map entry (key-value pair).  The <tt>Map.entrySet</tt> method returns
     * a collection-view of the map, whose elements are of this class.  The
     * <i>only</i> way to obtain a reference to a map entry is from the
     * iterator of this collection-view.  These <tt>Map.Entry</tt> objects are
     * valid <i>only</i> for the duration of the iteration; more formally,
     * the behavior of a map entry is undefined if the backing map has been
     * modified after the entry was returned by the iterator, except through
     * the <tt>setValue</tt> operation on the map entry.
     *
     * @see Map#entrySet()
     * @since 1.2
     */
    interface Entry<K,V> {
        /**
         * Returns the key corresponding to this entry.
         *
         * @return the key corresponding to this entry
         * @throws IllegalStateException implementations may, but are not
         *         required to, throw this exception if the entry has been
         *         removed from the backing map.
         */
        K getKey();

        /**
         * Returns the value corresponding to this entry.  If the mapping
         * has been removed from the backing map (by the iterator's
         * <tt>remove</tt> operation), the results of this call are undefined.
         *
         * @return the value corresponding to this entry
         * @throws IllegalStateException implementations may, but are not
         *         required to, throw this exception if the entry has been
         *         removed from the backing map.
         */
        V getValue();

        /**
         * Replaces the value corresponding to this entry with the specified
         * value (optional operation).  (Writes through to the map.)  The
         * behavior of this call is undefined if the mapping has already been
         * removed from the map (by the iterator's <tt>remove</tt> operation).
         *
         * @param value new value to be stored in this entry
         * @return old value corresponding to the entry
         * @throws UnsupportedOperationException if the <tt>put</tt> operation
         *         is not supported by the backing map
         * @throws ClassCastException if the class of the specified value
         *         prevents it from being stored in the backing map
         * @throws NullPointerException if the backing map does not permit
         *         null values, and the specified value is null
         * @throws IllegalArgumentException if some property of this value
         *         prevents it from being stored in the backing map
         * @throws IllegalStateException implementations may, but are not
         *         required to, throw this exception if the entry has been
         *         removed from the backing map.
         */
        V setValue(V value);
				...
    }
```



#### 1.5.2 HashMap

HashMap中最重要的数据结构

```java
/**
     * Basic hash bin node, used for most entries.  (See below for
     * TreeNode subclass, and in LinkedHashMap for its Entry subclass.)
     */
static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        V value;
        Node<K,V> next;

        Node(int hash, K key, V value, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey()        { return key; }
        public final V getValue()      { return value; }
        public final String toString() { return key + "=" + value; }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                if (Objects.equals(key, e.getKey()) &&
                    Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }
```

从源码中从上往下读，接下来是hash函数

```java
   /**
     * Computes key.hashCode() and spreads (XORs) higher bits of hash
     * to lower.  Because the table uses power-of-two masking, sets of
     * hashes that vary only in bits above the current mask will
     * always collide. (Among known examples are sets of Float keys
     * holding consecutive whole numbers in small tables.)  So we
     * apply a transform that spreads the impact of higher bits
     * downward. There is a tradeoff between speed, utility, and
     * quality of bit-spreading. Because many common sets of hashes
     * are already reasonably distributed (so don't benefit from
     * spreading), and because we use trees to handle large sets of
     * collisions in bins, we just XOR some shifted bits in the
     * cheapest possible way to reduce systematic lossage, as well as
     * to incorporate impact of the highest bits that would otherwise
     * never be used in index calculations because of table bounds.
     */
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
```

hash策略是高16位和低16位做**异或运算**。目的是为了让hash**对应的index均匀分布**，

Q.高16位异或有什么用？

A.由Q1我们知道idx取决于hash的后log10(n)+1长度的数值分布，那问题来了，当n本身较小时，高位等价于被截断，hash的意义存在局限性（想象一下多个数高位不同低位相同的冲突），因此需要高位的运算参与，尽可能满足最后结果的分布均匀

Q.那高16位参与运算咋不用&、|？

A.首先，如果使用&，当长度大于216216时高16位将全部置零，也就是说空间完全被浪费（你永远算不到那里），下一个|是处于0和1的概率分布而言，1出现的可能比0高得多，也不是好方法

Q.这样哈希并不一定很靠谱啊？

A.确实，但源码内部的注释有说明到，由于已经采用了大量哈希碰撞时交由红黑树处理的策略，因此这里的哈希策略只是高性能下比较好的实现，而非严谨的实现





接下来是

```java
    /**
     * Returns x's Class if it is of the form "class C implements
     * Comparable<C>", else null.
     */
    static Class<?> comparableClassFor(Object x) {
        if (x instanceof Comparable) {
            Class<?> c; Type[] ts, as; Type t; ParameterizedType p;
            if ((c = x.getClass()) == String.class) // bypass checks
                return c;
            if ((ts = c.getGenericInterfaces()) != null) {
                for (int i = 0; i < ts.length; ++i) {
                    if (((t = ts[i]) instanceof ParameterizedType) &&
                        ((p = (ParameterizedType)t).getRawType() ==
                         Comparable.class) &&
                        (as = p.getActualTypeArguments()) != null &&
                        as.length == 1 && as[0] == c) // type arg is c
                        return c;
                }
            }
        }
        return null;
    }

    /**
     * Returns k.compareTo(x) if x matches kc (k's screened comparable
     * class), else 0.
     */
    @SuppressWarnings({"rawtypes","unchecked"}) // for cast to Comparable
    static int compareComparables(Class<?> kc, Object k, Object x) {
        return (x == null || x.getClass() != kc ? 0 :
                ((Comparable)k).compareTo(x));
    }
```

这个理解起来比较复杂，查阅了很多资料。大致意思是**判断传入的Object对象x是否实现了Comparable接口**

**comparableClassFor**这个方法,主要用在查找元素的时候,而在HashMap中查找通过key进行查找,而多数情况下我们都会用String来作为这个key,那么在**comparableClassFor**中进行判断,可以跳过获得接口等一系列操作,大大优化了程序的执行效率.



接着往下看

```java
    /**
     * Returns a power of two size for the given target capacity.
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
```

该函数的作用是（不考虑大于最大容量的情况）是返回**大于输入参数且最近的2的整数次幂的数**。

首先先了解>>>这个运算符代表了什么

> / >>>表示无符号右移，也叫逻辑右移，即若该数为正，则高位补0
> 比如7的二进制是111，7>>>2表示右移2位，变成001，即为1

 详解如下：

先来分析有关n位操作部分：先来假设n的二进制为01xxx...xxx。接着

对n右移1位：001xx...xxx，再位或：011xx...xxx

对n右移2为：00011...xxx，再位或：01111...xxx

此时前面已经有四个1了，再右移4位且位或可得8个1

同理，有8个1，右移8位肯定会让后八位也为1。

综上可得，该算法让最高位的1后面的位全变为1。

最后再让结果n+1，即得到了2的整数次幂的值了。

现在回来看看第一条语句：

```
int n = cap - 1;
```

　　让cap-1再赋值给n的目的是另找到的目标值大于或**等于**原值。例如二进制1000，十进制数值为8。如果不对它减1而直接操作，将得到答案10000，即16。显然不是结果。减1后二进制为111，再进行操作则会得到原来的数值1000，即8。参考链接：https://www.cnblogs.com/loading4/p/6239441.html



接着是3个构造函数

```java
    /**
     * Constructs an empty <tt>HashMap</tt> with the specified initial
     * capacity and load factor.
     *
     * @param  initialCapacity the initial capacity
     * @param  loadFactor      the load factor
     * @throws IllegalArgumentException if the initial capacity is negative
     *         or the load factor is nonpositive
     */
    public HashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " +
                                               initialCapacity);
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " +
                                               loadFactor);
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initialCapacity);
    }

    /**
     * Constructs an empty <tt>HashMap</tt> with the specified initial
     * capacity and the default load factor (0.75).
     *
     * @param  initialCapacity the initial capacity.
     * @throws IllegalArgumentException if the initial capacity is negative.
     */
    public HashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    /**
     * Constructs an empty <tt>HashMap</tt> with the default initial capacity
     * (16) and the default load factor (0.75).
     */
    public HashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
    }
```

里面的**initialCapacity**和**loadFactor**需要重点讲解说明一下。

**loadFactor**：HashMap的加载因子，默认值为0.75。这个值最关键的作用在于判断是否**需要扩容**。即当容量到达75%后，进行resize操作。那为什么加载因子不是0.5或者1呢？如果是0.5 ， 那么每次达到容量的一半就进行扩容，默认容量是16， 达到8就扩容成32，达到16就扩容成64， 最终使用空间和未使用空间的差值会逐渐增加，空间利用率低下。  如果是1，那意味着每次空间使用完毕才扩容，在一定程度上会增加put时候的时间。 那为什么不是0.6或者0.8了，这个就跟数学有关了，具体可以参考：https://www.jianshu.com/p/64f6de3ffcc1



**initialCapacity**：就是容器数组的初始值，对于这个值需要注意的点如下：

- 如果不超过12个键值对，可以不设置
- 如果超出，按initialCapacity = (需要存储的元素个数 / 负载因子) + 1公式计算后设置
- 官方的建议是initailCapacity设置成2的n次幂

原因在于，比如HashMap需要放置1024个元素，由于没有设置容量初始大小，随着元素不断增加，容量7次被迫扩大，resize需要重建hash表，严重影响性能。

按HashMap源码里的那种重构方法，如果reHash过多，显然会影响性能。所以为了防止过多的reHash，我们需要自己配置HashMap的装载因子loadFactor和初始的table容量capacity的大小（可以在构造函数里配或者调用方法配）。



