package Pattern.Generics;

import java.util.Collection;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/3/28 下午10:18
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public class ProfitsCalculateService<T extends Items> {
    public Boolean addSellingRecord(T item){
        return item.addItemsRecord;
    }

    public <E> int getItemHashCode(E e){

        return e.hashCode();
    }
}
