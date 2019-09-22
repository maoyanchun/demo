package com.micro.test.concurrent.order;

import java.io.*;
import java.util.Properties;

/**
 * Description:
 * Created by mycge at 11:20 on 2019-09-22.
 */
public class OrderInfo {
    private static OrderInfo orderInfo;

    private long orderAinitalValue;
    private long orderBinitalValue;
    private long orderCinitalValue;

    private OrderInfo() {
        String fileName = OrderInfo.class.getResource("/").getPath() + "/order.properties";
        InputStream in = null;
        try {
            in = new FileInputStream(new File(fileName));
            Properties properties = new Properties();
            properties.load(in);
            this.orderAinitalValue = Long.parseLong(properties.getProperty("orderA.initialValue"));
            this.orderBinitalValue = Long.parseLong(properties.getProperty("orderB.initialValue"));
            this.orderCinitalValue = Long.parseLong(properties.getProperty("orderC.initialValue"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static {
        orderInfo = new OrderInfo();
    }

    public static OrderInfo getInstance() {
        return orderInfo;
    }

    public long getOrderAinitalValue() {
        return orderAinitalValue;
    }

    public long getOrderBinitalValue() {
        return orderBinitalValue;
    }

    public long getOrderCinitalValue() {
        return orderCinitalValue;
    }
}
