package com.rick.chapter_15;

/**
 * @Author: Rick
 * @Date: 2022/10/23 22:37
 */
public interface TaskLifecycle<T> {

    // 任务启动时会触发onStart方法
    void onStart(Thread thread);

    // 任务正在运行时会触发onRunning方法
    void onRunning(Thread thread);

    // 任务运行结束时会触发onFinish方法，其中result时任务执行结束后的结果
    void onFinish(Thread thread, T result);

    // 任务执行报错时会触发onError方法
    void onError(Thread thread, Exception e);

    class EmptyLifecycle<T> implements TaskLifecycle<T> {

        @Override
        public void onStart(Thread thread) {
            // do nothing
        }

        @Override
        public void onRunning(Thread thread) {
            // do nothing
        }

        @Override
        public void onFinish(Thread thread, T result) {
            // do nothing
        }

        @Override
        public void onError(Thread thread, Exception e) {
            // do nothing
        }
    }
}
