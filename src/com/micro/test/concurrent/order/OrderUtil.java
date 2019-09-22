package com.micro.test.concurrent.order;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Description:
 * 订单编号格式：D20190922-12345
 * Created by mycge at 10:42 on 2019-09-22.
 */
public class OrderUtil {
    private static AtomicLong al;

    static {
        //防止服务器宕机，重启导致订单号冲突；所以万一重启，要配置最大的序号作为初始值
        OrderInfo orderInfo = OrderInfo.getInstance();
        al = new AtomicLong(orderInfo.getOrderAinitalValue());
    }

    private static final String ORDER_NO_PREFIX = "D";
    private static final String ORDER_NO_CONNECT = "-";

    /**
     * 生成一个新的订单编号
     *
     * @return
     */
    public static String getOrderNo() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        String sMonth, sDay;
        if (month < 10) {
            sMonth = "0" + month;
        } else {
            sMonth = String.valueOf(month);
        }
        if (day < 10) {
            sDay = "0" + day;
        } else {
            sDay = String.valueOf(day);
        }
        long serialNo = al.getAndIncrement(); //CAS无锁模式，乐观锁的一种
        return ORDER_NO_PREFIX + year + sMonth + sDay + ORDER_NO_CONNECT + serialNo;
    }
}
