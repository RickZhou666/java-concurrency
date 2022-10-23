package com.rick.chapter_01.d01_create_thread;

/**
 * @Author: Rick
 * @Date: 2022/9/26 23:51
 */
public class T04_TemplateMethod {
    public final void print(String message) {
        System.out.println("#########################");
        wrapPrint(message);
        System.out.println("#########################");
    }

    protected void wrapPrint(String message) {
    }

    public static void main(String[] args) {
        T04_TemplateMethod t1 = new T04_TemplateMethod() {
            @Override
            protected void wrapPrint(String message) {
                System.out.println("*" + message + "");
            }
        };

        t1.print("Hello World");


        T04_TemplateMethod t2 = new T04_TemplateMethod() {
            @Override
            protected void wrapPrint(String message) {
                System.out.println("*" + message + "");
            }
        };
        t2.print("This is Rick! Hello Thread!");
    }
}
