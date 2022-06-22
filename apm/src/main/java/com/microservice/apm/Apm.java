package com.microservice.apm;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * @author wangqianlong
 * create at: 2022/6/22
 */
public class Apm {


    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("agentArgs : " + agentArgs);
        //加入自定义转换器
        inst.addTransformer(new MyTransformer(), true);
    }


    //通过ClassFileTransformer接口，可以在类加载之前，重写字节码
    public static class MyTransformer implements ClassFileTransformer {

        /**
         * 参数：
         * loader - 定义要转换的类加载器；如果是引导加载器，则为 null
         * className - 完全限定类内部形式的类名称和 The Java Virtual Machine Specification 中定义的接口名称。例如，"java/util/List"。
         * classBeingRedefined - 如果是被重定义或重转换触发，则为重定义或重转换的类；如果是类加载，则为 null
         * protectionDomain - 要定义或重定义的类的保护域
         * classfileBuffer - 类文件格式的输入字节缓冲区（不得修改）
         * 返回：
         * 一个格式良好的类文件缓冲区（转换的结果），如果未执行转换,则返回 null。
         * 抛出：
         * IllegalClassFormatException - 如果输入不表示一个格式良好的类文件
         */
        @Override
        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                                ProtectionDomain protectionDomain, byte[] classfileBuffer) {

            if (!className.equals("com/micro/produce/controller/ProduceController")) {
                return null; // 如果返回null则字节码不会被修改
            }

            try {
                //借助JavaAssist工具，进行字节码重写
                ClassPool classPool = ClassPool.getDefault();
                CtClass cc = classPool.get("com.micro.produce.controller.ProduceController");
                CtMethod personFly = cc.getDeclaredMethod("get111");

                //在目标方法前后，插入代码
                personFly.insertBefore("System.out.println(\"--- apm before get ---\");");
                personFly.insertAfter("System.out.println(\"--- apm after get ---\");");

                return cc.toBytecode();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }
    }
}