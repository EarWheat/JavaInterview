package Pattern.Strategy.impl;

import Pattern.Strategy.QuarkBehavior;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/6/7 下午5:10
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public class QuarkGaga implements QuarkBehavior {
    @Override
    public void quark() {
        System.out.println("quark with gaga");
    }
}
