package thread;

/**
 * 测试Join方法
 * @author code_yc
 * @version 1.0
 * @date 2020/4/21 9:07
 */
public class Join {

    static class ThreadA implements Runnable{

        @Override
        public void run() {
            try {
                System.out.println("我是子线程，我先睡1s");
                Thread.sleep(1000L);
                System.out.println("我是子线程，我睡醒了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ThreadA());
        thread.start();
        //thread.join();
        System.out.println("如果不加join方法，我会先被打出来，加了就不一样了");
    }
}
