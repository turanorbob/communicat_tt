package com.example.test;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * description
 * </p>
 *
 * @author Legendl
 * @date 09/10/19 13:35
 */
public class Test {
    public static void print(Map<String, List<Adder>> data){
        data.forEach((key, value) -> {
            System.out.println();
            System.out.println("key:" + key);
            value.forEach(ele -> {
                System.out.print(ele + ",");
            });
            System.out.println();
        });
    }

    public static void main(String args[]){
        Map<String, List<Adder>> data = new LinkedHashMap<>();
        List<String> dateList = Lists.newArrayList("03/2018", "04/2018", "05/2018", "06/2018");
        int start = 1;
        for (String ele : dateList) {
            List<Adder> adders = new ArrayList<>();
            for(int k = 1; k < 6; k++){
                Adder a1 = new Adder();
                a1.setTotal(new BigDecimal(start++));
                adders.add(a1);
            }
            data.put(ele, adders);
        }

        for(String name: data.keySet()){
            BigDecimal baseTotal = new BigDecimal(0);
            for(Adder p:  data.get(name)){
                baseTotal = baseTotal.add(p.getTotal());
                p.setTotal(baseTotal);
            }
        }

        print(data);
    }
}
