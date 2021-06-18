package Pattern.Factory;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/6/18 下午3:08
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public class ShapeFactory extends AbstractFactory{
    Shape shape = null;

    @Override
    public Color getColor(String color) {
        return null;
    }

    @Override
    public Shape getShape(String type) {
        if(type.equalsIgnoreCase("circle")){
            shape = new Circle();
        }
        if(type.equalsIgnoreCase("Square")){
            shape = new Square();
        }
        return shape;
    }
}
