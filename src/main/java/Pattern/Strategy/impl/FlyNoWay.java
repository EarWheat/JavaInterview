package Pattern.Strategy.impl;

import Pattern.Strategy.FlyBehavior;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/6/6 下午4:11
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I can't fly!");
    }
}
