package com.rick.chapter_14;

/**
 * @Author: Rick
 * @Date: 2022/10/23 21:32
 */
public class T08_Singleton_Enum_Holder {
    // 实例变量
    private byte[] bytes = new byte[1024];

    private T08_Singleton_Enum_Holder() {
    }

    // 使用枚举充当holder
    private enum EnumHolder {
        INSTANCE;
        private T08_Singleton_Enum_Holder instance;

        EnumHolder() {
            this.instance = new T08_Singleton_Enum_Holder();
        }

        private T08_Singleton_Enum_Holder getSingleton() {
            return instance;
        }
    }

    public static T08_Singleton_Enum_Holder getInstance() {
        return EnumHolder.INSTANCE.getSingleton();
    }


}
