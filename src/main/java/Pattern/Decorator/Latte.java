package Pattern.Decorator;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/6/16 下午5:07
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public class Latte implements Coffee{


    @Override
    public void makeCoffee() {
        System.out.println("Latte");
    }
}
