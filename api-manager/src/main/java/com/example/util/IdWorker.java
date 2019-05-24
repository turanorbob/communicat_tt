package com.example.util;

import java.util.UUID;

/**
 * @Description
 * @Author legend <legendl@synnex.com>
 * @Version v1.0.0
 * @Since 1.0
 * @Date 2019/5/24
 */
public class IdWorker {

    public static String getUUID32(){
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-","").toLowerCase();
    }
}
