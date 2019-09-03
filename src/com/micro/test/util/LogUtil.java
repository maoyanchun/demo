package com.micro.test.util;

import org.apache.log4j.Logger;

/**
 * Description:
 * Created by mycge at 22:15 on 2019-09-03.
 */
public class LogUtil {
    private static final Logger logger = Logger.getLogger(LogUtil.class);

    public static Logger getLogger(){
        return logger;
    }

}
