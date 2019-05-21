package com.example.demo;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Maps;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

import javax.script.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
        if(!context.isEmpty()){
            for(Map.Entry entry :  context.entrySet()) {
                engine.put("$"+entry.getKey(), entry.getValue());
            }
        }

        Object obj = engine.eval(script);
        System.out.println(obj);

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

        start = System.currentTimeMillis();
        Binding binding = new Binding();
        GroovyShell shell = new GroovyShell(binding);
        script = "def long sum=0;for(i=0;i<="+count+";i++){sum=sum+i};return sum;";
        sum = (Long) shell.evaluate(script);
        System.out.println("groovy cost time: "+ (System.currentTimeMillis() - start));
        System.out.println("result:"+ sum);

        start = System.currentTimeMillis();

        script = "sum = 0; for i = 0,"+count+" do sum = sum + i; end";
        Globals globals = JsePlatform.standardGlobals();
        LuaValue chunk = globals.load(script);
        chunk.call();
        System.out.println("lua cost time:" + (System.currentTimeMillis() - start));
        System.out.println("result:"+globals.get("sum"));
    }

    public static void main(String args[]) throws ScriptException {

        Map<String, Object> data = new HashMap<>();
        List<User> eles = Lists.newArrayList(new User(1,"A"), new User(2, "B"));
        data.put("eles", eles);

        //System.out.println(eles.get(0).getName());

        String script = "var aa=$eles;for(var i=0; i<aa.size()-1;i++){" +
                "aa.get(i).getName()}";
        testScript(data, script);


    }
}
