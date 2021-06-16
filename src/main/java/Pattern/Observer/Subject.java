package Pattern.Observer;

import java.util.List;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/6/9 下午8:17
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public class Subject {
    private String data;
    private List<Observer> observers;

    /**
     * 订阅主题
     * @param observer
     */
    public void attach(Observer observer){
        observers.add(observer);
    }

    /**
     * 离开主题
     * @param observer
     */
    public void remove(Observer observer){
        observers.remove(observer);
    }


}
