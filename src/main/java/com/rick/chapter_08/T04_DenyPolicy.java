package com.rick.chapter_08;

/**
 * @Author: Rick
 * @Date: 2022/10/4 23:30
 */
@FunctionalInterface
public interface T04_DenyPolicy {
    void reject(Runnable runnable, T01_ThreadPool threadPool);

    // 该拒绝策略会直接将任务丢弃
    class DiscardDenyPolicy implements T04_DenyPolicy{
        @Override
        public void reject(Runnable runnable, T01_ThreadPool threadPool) {
            // do nothing
        }
    }

    // 该拒绝策略会向任务提交者抛出异常
    class AbortDenyPolicy implements T04_DenyPolicy{

        @Override
        public void reject(Runnable runnable, T01_ThreadPool threadPool) {
            throw new T05_RunnableDenyException("The runnable " + runnable + " will be abort.");
        }
    }

    // 该拒绝策略会使任务在提交者所在的线程中执行任务
    class RunnerDenyPolicy implements T04_DenyPolicy{

        @Override
        public void reject(Runnable runnable, T01_ThreadPool threadPool) {
            if (!threadPool.isShutdown()){
                runnable.run();
            }
        }
    }
}
