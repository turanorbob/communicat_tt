package com.example.demo;

import org.assertj.core.util.Maps;

import javax.script.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static jdk.nashorn.internal.runtime.ScriptingFunctions.exec;

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
        for(Map.Entry entry :  context.entrySet()) {
            engine.put("$"+entry.getKey(), entry.getValue());
        }
//        Bindings bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);
        Object obj = engine.eval(script);

        // 脚本上下文
        // 定义脚本变量
        // 定义脚本方法体
        // 定义脚本方法（公共方法和私有方法）
        Bindings engineVariable = engine.getBindings(ScriptContext.ENGINE_SCOPE);

        return engineVariable;
    }

    public static void main(String args[]) throws ScriptException {
        long start = System.currentTimeMillis();
        Map<String, Object> context = new HashMap<>();
        context.put("a", 1.234567890);

        String script="var sum=0;for(var i=0; i<1000000; i++){sum=sum+i;}";
        Map<String, Object> result = testScript(context, script);

        System.out.println("javascript cost time:" + (System.currentTimeMillis() - start));
        result.forEach((key, value) -> {
            System.out.println(key+"->" + value);
        });

        start = System.currentTimeMillis();

        int sum =0;
        for(int i=0; i<1000000; i++){
            sum = sum+i;
        }
        System.out.println("java cost time:" + (System.currentTimeMillis() - start));

    }
}
