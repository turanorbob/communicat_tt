package com.example.demo;

import javax.script.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

/**
 * @Description
 * @Author legend <legendl@synnex.com>
 * @Version v1.0.0
 * @Since 1.0
 * @Date 2019/4/25
 */
public class Test {

    public static void test1(){
        String str = "@common_prov_status";
        String [] arr = str.split("@");
        System.out.println(arr.length);

        Stream.of(arr).forEach(System.out::println);
    }

    public static Map<String, Object> testScript(Map<String, Object> context, String script) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        if(!context.isEmpty()){
            for(Map.Entry entry :  context.entrySet()) {
                engine.put("$"+entry.getKey(), entry.getValue());
            }
        }

        Object obj = engine.eval(script);
        System.out.println(context.get("a")+","+script + "->" + obj+"," + (context.get("a")==obj));

        // 脚本上下文
        // 定义脚本变量
        // 定义脚本方法体
        // 定义脚本方法（公共方法和私有方法）
        Bindings engineVariable = engine.getBindings(ScriptContext.ENGINE_SCOPE);

        return engineVariable;
    }

    public static void compare() throws ScriptException {
        long start = System.currentTimeMillis();
        Map<String, Object> context = new HashMap<>();
        int count = 4000000;
        System.out.println("1 .. "+ count +" = ?");
        System.out.println("int max:"+ Integer.MAX_VALUE);

        String script="var sum=0;for(i=0; i<="+count+"; i++){sum=sum+i;}";
        Map<String, Object> result = testScript(context, script);

        System.out.println("javascript cost time:" + (System.currentTimeMillis() - start));
        Double rs = (Double)result.get("sum");
        System.out.println("sum:" + rs.longValue());

        start = System.currentTimeMillis();

        long sum =0;
        for(int i=0; i<=count; i++){
            sum = sum+i;
        }
        System.out.println("java cost time:" + (System.currentTimeMillis() - start));
        System.out.println("result:"+ sum);

    }

    public static void main(String args[]) throws ScriptException, InterruptedException {
        List<User> eles = new ArrayList<>();
        eles.add(new User(1,"A"));
        eles.add(new User(2, "B"));

        //System.out.println(eles.get(0).getName());

        for(int i=0; i<100; i++){
            final int t = i;
            CompletableFuture.runAsync(()->{
                Map<String, Object> data = new HashMap<>();
                data.put("eles", eles);
                data.put("isSale", false);
                data.put("a", t);
                String script = "var a=$a;a;";
                try {
                    testScript(data, script);
                } catch (ScriptException e) {
                    e.printStackTrace();
                }
            });

        }

        Thread.sleep(5000L);

    }
}
