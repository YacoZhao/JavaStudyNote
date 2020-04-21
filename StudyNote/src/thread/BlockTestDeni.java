package thread;

import org.junit.Test;

/**
 * @author code_yc
 * @version 1.0
 * @date 2020/4/21 8:14
 */
public class BlockTestDeni {

    @Test
    public void blockedTest() throws InterruptedException {

        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                testMethod();
            }
        }, "a");
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                testMethod();
            }
        }, "b");

        a.start();
        // Thread.sleep(1000L);  // 主线程休眠1s
        //a.join();                // a线程join一下
        a.join(1000L);       // a线程指定执行1s向下走
        b.start();
        System.out.println(a.getName() + ":" + a.getState()); // 输出？
        System.out.println(b.getName() + ":" + b.getState()); // 输出？
    }

    // 同步方法争夺锁
    private synchronized void testMethod() {
        try {
            Thread.sleep(2000L);  // 抢到锁的线程休眠2s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
