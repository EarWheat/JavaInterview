package Pattern.Decorator;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/6/16 下午5:10
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public void makeCoffee(){
        coffee.makeCoffee();
    }
}
