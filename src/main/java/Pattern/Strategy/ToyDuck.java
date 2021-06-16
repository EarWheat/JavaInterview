package Pattern.Strategy;

import Pattern.Strategy.impl.FlyWithWings;
import Pattern.Strategy.impl.QuarkZizi;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/6/6 下午3:56
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public class ToyDuck extends Duck {

    ToyDuck(){
        flyBehavior = new FlyWithWings();
        quarkBehavior = new QuarkZizi();
    }
}
