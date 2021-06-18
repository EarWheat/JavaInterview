package Pattern.Factory;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/6/18 下午3:17
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public class ColorFactory extends AbstractFactory{
    Color color = null;

    @Override
    public Color getColor(String type){
        if(type.equalsIgnoreCase("red")){
            color = new Red();
        }
        if(type.equalsIgnoreCase("blue")){
            color = new Blue();
        }
        return color;
    }

    @Override
    public Shape getShape(String shape) {
        return null;
    }
}
