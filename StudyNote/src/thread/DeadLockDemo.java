package thread;

/**
 * 死锁的简单案列
 * @author code_yc
 * @version 1.0
 * @date 2020/4/21 9:34
 */
public class DeadLockDemo {

    public static Object resource1 = new Object();  // 资源1
    public static Object resource2 = new Object();  // 资源2

    public static void main(String[] args) {
        // 首先创建A线程(函数式编程写法)
        new Thread(() -> {
            synchronized (resource1){  // 加上resource1的同步锁
                System.out.println(Thread.currentThread() + "get resource1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get resource2");
                synchronized (resource2) {
                    System.out.println(Thread.currentThread() + "get resource2");
                }
            }
        },"线程1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resource1){
                    System.out.println(Thread.currentThread() + "get resource2");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread() + "waiting get resource1");
                    synchronized (resource2) {
                        System.out.println(Thread.currentThread() + "get resource1");
                    }
                }
            }
        },"线程2").start();
    }
}
