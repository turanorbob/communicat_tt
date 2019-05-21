package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author legend <legendl@synnex.com>
 * @Version v1.0.0
 * @Since 1.0
 * @Date 2019/5/21
 */
public class Test {
    public static void main(String args[]){
        List<String> data = new ArrayList<>();
        data.add("A");
        data.add("B");
        data.add("C");

        data.forEach(ele -> {
            if(ele.equals("B")){
                return;
            }
            System.out.println(ele);
        });

    }
}
