package Pattern.Decorator;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/6/16 下午5:24
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public class Order {
    public static void main(String[] args) {
        Coffee coffee = new CoffeeWithSugar(new CoffeeWithIce(new Latte()));
        coffee.makeCoffee();
    }
}
