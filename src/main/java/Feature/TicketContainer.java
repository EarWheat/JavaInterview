package Feature;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/6/16 下午7:38
 * @desc 票容器类
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public class TicketContainer {

    public volatile static Integer tickets = 50;

    public synchronized static void buy(){
        System.out.println(Thread.currentThread().getName() + "------" + tickets);
        tickets--;
    }
}
