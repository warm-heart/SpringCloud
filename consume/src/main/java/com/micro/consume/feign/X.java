package com.micro.consume.feign;

import lombok.Data;

/**
 * @author xuweizhi
 * @since 2022/05/16 20:37
 */
public class X {

    public static interface Context {

        Context createContext(Param param);

    }

    public static interface Handler<T> {
        T handle(Context context);
    }

    public static interface Param {

    }

    @Data
    public static class DefaultParam implements Param {

    }

    @Data
    public static class CarParam implements Param {

        private Long carId;

    }

    public static interface Application<T> extends Context, Handler<T> {

        T execute(Param param);

    }

    public static interface Extension {

    }

    public static class CarExtension implements Extension{

    }

    @Data
    public static class DefaultApplication<T> implements Application<T> {
        // ps 某些公用字段
        private Extension extension;

        @Override
        public Context createContext(Param param) {
            DefaultApplication<T> context = new DefaultApplication<T>();
            // todo init context content
            context.setExtension(extension);
            System.out.println("default 创建上下文");
            return context;
        }

        @Override
        public T handle(Context context) {
            // 各自的实现类型,对用的context 类型
            DefaultApplication<T> defaultApplication = (DefaultApplication<T>) context;
            Extension extension = defaultApplication.getExtension();
            // todo execute actually business operate
            System.out.println("default execute");
            return (T) "默认实现";
        }

        /**
         * execute 这一步实际被 bdp 包含
         *
         * @param param 入参
         * @return 返回值
         */
        @Override
        public T execute(Param param) {
            // 1. 创建
            Context context = createContext(param);
            // 2. 执行
            return handle(context);
        }
    }


    @Data
    public static class CarApplication extends DefaultApplication<String> {
        @Override
        public Context createContext(Param param) {
            CarApplication carContext = new CarApplication();
            CarParam carParam = (CarParam) param;
            CarExtension carExtension = new CarExtension();
            // 差异化处理
            carContext.setExtension(carExtension);
            System.out.println("car 创建上下文");
            return carContext;
        }


        @Override
        public String handle(Context context) {
            System.out.println("car execute");
            return "11";
        }
    }

    @Data
    public static class DogApplication extends DefaultApplication<Integer> {

        @Override
        public Integer handle(Context context) {
            System.out.println("car execute");
            return 12;
        }
    }

    public static void main(String[] args) {

        DefaultApplication<String> defaultApplication = new DefaultApplication<>();
        String execute1 = defaultApplication.execute(new DefaultParam());
        System.out.println("=====================\n");

        CarApplication carApplication = new CarApplication();
        String execute2 = carApplication.execute(new CarParam());

        System.out.println("=====================\n");
        DogApplication dogApplication = new DogApplication();
        Integer execute = dogApplication.execute(new DefaultParam());
    }


}

