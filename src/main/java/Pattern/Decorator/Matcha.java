package Pattern.Decorator;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/6/16 下午5:08
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public class Matcha implements Coffee {

    @Override
    public void makeCoffee() {
        System.out.println("match");
    }
}
