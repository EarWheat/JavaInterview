package Pattern.Factory;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/6/18 下午3:05
 * @desc 抽象工厂类
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public abstract class AbstractFactory {

    public abstract Color getColor(String color);
    public abstract Shape getShape(String shape);
}
