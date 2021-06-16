package Pattern.Decorator;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/6/16 下午5:41
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public class CoffeeWithSugar extends CoffeeDecorator{
    public CoffeeWithSugar(Coffee coffee) {
        super(coffee);
    }

    @Override
    public void makeCoffee() {
        super.makeCoffee();
        addSugar();
    }

    public void addSugar(){
        System.out.println("with sugar");
    }
}
