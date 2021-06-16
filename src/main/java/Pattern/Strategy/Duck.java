package Pattern.Strategy;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/6/6 下午3:54
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public abstract class Duck {
    FlyBehavior flyBehavior;
    QuarkBehavior quarkBehavior;

    public void display(){};

    public void quark(){
        quarkBehavior.quark();
    }

    public void fly(){
        flyBehavior.fly();
    }
}
