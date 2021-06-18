package Feature;

import java.util.concurrent.*;

/**
 * @author liuzhaoluliuzhaolu
 * @date 2021/6/16 下午7:05
 * @desc
 * @prd
 * @Modification History:
 * Date         Author          Description
 * ------------------------------------------ *
 */
public class Customer implements Runnable{

    @Override
    public void run() {
        try {
            synchronized (SaleTicket.class){
                while (TicketContainer.tickets > 0){
                    Thread.sleep((long) (Math.random() % 1000));
                    TicketContainer.buy();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
