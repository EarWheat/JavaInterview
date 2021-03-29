package Pattern.Generics;

import java.util.Collection;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/3/28 下午10:08
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public class generics<T extends Collection<T>> {

    public int getSize(T size){
        return size.size();
    }

}
