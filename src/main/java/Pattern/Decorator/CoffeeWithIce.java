package Pattern.Decorator;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/6/16 下午5:40
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public class CoffeeWithIce extends CoffeeDecorator{
    public CoffeeWithIce(Coffee coffee) {
        super(coffee);
    }

    @Override
    public void makeCoffee() {
        super.makeCoffee();
        addIce();
    }

    private void addIce(){
        System.out.println("with ice");
    }
}
