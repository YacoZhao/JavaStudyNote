### 1. 进程和线程的区别

- **本质区别：** 是是否单独占有内存地址空间及其它系统资源（比如I/O），进程单独占有内存地址空间，线程共享进程的内存空间和系统资源
- **产生的影响：**
    - 进程数据共享复杂，线程数据共享简单
    - 进程同步简单，线程同步复杂
    - 进程如果出现问题，不会影响其他进程，不影响主程序的稳定性；但是一个线程崩溃可能影响整个程序的稳定性，可靠性较低。
    - 进程的创建和销毁开销较大，线程相对来说较小。
- **重要区别：** 进程是操作系统进行资源分配的基本单位，而线程是操作系统进行资源调度的基本单位，即CPU时间片段。
- 线程私有程序计数器，虚拟机栈和本地方法栈，线程共享堆、方法区、常量池和直接内存。
----
### 2. 上下文切换

**含义**：上下文切换（有时也称做进程切换或任务切换）是指 CPU 从一个进程（或线程）切换到另一个进程（或线程）。

概括来说就是：当前任务在执行完 CPU 时间片切换到另一个任务之前会先保存自己的状态，以便下次再切换回这个任务时，可以再加载这个任务的状态。任务从保存到再加载的过程就是一次上下文切换。

上下文切换通常是计算密集型的，意味着此操作会消耗大量的 CPU 时间，故线程也不是越多越好。如何减少系统中上下文切换次数，是提升多线程性能的一个重点课题。

----

### 3. 创建线程的几种方式

- 继承Thread类
- 实现Runnable接口
- 实现Callable接口（可以有返回值）
- Future接口（不常见）
- FutureTask类（Future接口的实现类）

----
### 4. Thread类与Runnable接口的比较

- 由于Java“单继承，多实现”的特性，Runnable接口使用起来比Thread更灵活。
- Runnable接口出现更符合面向对象，将线程单独进行对象的封装。
- Runnable接口出现，降低了线程对象和线程任务的耦合性。
- 如果使用线程时不需要使用Thread类的诸多方法，显然使用Runnable接口更为轻量。
----
### 5. 说说线程的生命周期和状态?
Java 线程在运行的生命周期中的指定时刻只可能处于下面 6 种不同状态的其中一个状态（图源《Java 并发编程艺术》4.1.4 节）。

![Java线程的不同状态](https://my-blog-to-use.oss-cn-beijing.aliyuncs.com/19-1-29/Java%E7%BA%BF%E7%A8%8B%E7%9A%84%E7%8A%B6%E6%80%81.png)

- **NEW状态：**处于NEW状态的线程此时尚未启动。这里的尚未启动指的是还没调用Thread实例的start()方法。

  - 反复调用同一个线程的start()方法是否可行？---- 不可行

  - 假如一个线程执行完毕（此时处于TERMINATED状态），再次调用这个线程的start()方法是否可行？ ----不可行

  - 原因：只要线程状态不为0，那么就会报` IllegalThreadStateException`异常。

  - ```java
    public synchronized void start() {
        if (threadStatus != 0)
            throw new IllegalThreadStateException();
    
        group.add(this);
    
        boolean started = false;
        try {
            start0();
            started = true;
        } finally {
            try {
                if (!started) {
                    group.threadStartFailed(this);
                }
            } catch (Throwable ignore) {
    
            }
        }
    }
    ```

    

- **RUNNABLE状态：**表示当前线程正在运行中。处于RUNNABLE状态的线程在Java虚拟机中运行，也有可能在等待其他系统资源（比如I/O）。Java线程的**RUNNABLE**状态其实是包括了传统操作系统线程的**ready**和**running**两个状态的。

- **BLOCKED状态：**阻塞状态。处于BLOCKED状态的线程正等待锁的释放以进入同步区。

-  **WAITING状态：**等待状态。处于等待状态的线程变成RUNNABLE状态需要其他线程唤醒。

  调用如下3个方法会使线程进入等待状态

  - `Object.wait()`：使当前线程处于等待状态直到另一个线程唤醒它；
  - `Thread.join()`：等待线程执行完毕，底层调用的是Object实例的wait方法;
  - `LockSupport.park()`：除非获得调用许可，否则禁用当前线程进行线程调度。  

- **TIMED_WAITING状态：**超时等待状态。线程等待一个具体的时间，时间到后会被自动唤醒。

  - `Thread.sleep(long millis)`：使当前线程睡眠指定时间；

  - `Object.wait(long timeout)`：线程休眠指定时间，等待期间可以通过`notify()/notifyAll()`唤醒；

  - `Thread.join(long millis)`：等待当前线程最多执行`millis`毫秒，如果`millis`为0，则会一直执行；

  - `LockSupport.parkNanos(long nanos)`： 除非获得调用许可，否则禁用当前线程进行线程调度指定时间；

  - `LockSupport.parkUntil(long deadline)`：同上，也是禁止线程进行调度指定时间；

- **TERMINATED状态：**终止状态。此时线程已执行完毕。

  

---

### 6. 并发与并行的区别？

- 并发：同一时间段，多个任务都在进行（单位时间内不一定同时执行），多个线程抢占CPU时间片段，就是一种并发。
- 并行：单位时间内，多个任务同时执行。

---

### 7. 为什么要使用多线程

- 线程可以比作轻量级的进程，线程之间的切换和调度成本远远小于进程。
- 在多核CPU时代意味着多个线程可以同时运行，这减少了线程上下文切换的开销。
- 当代互联网系统并发访问量大，合理的利用多线程机制可以大大提高系统整体的并发能力和性能。
- 同时多核CPU时代，多线程可以大大提高CPU的利用率。

---

### 8. 使用多线程可能回带来什么问题？

并发编程的目的就是为了能提高程序的执行效率提高程序运行速度，但是并发编程并不总是能提高程序运行速度的，而且并发编程可能会遇到很多问题，比如：**内存泄漏、死锁、线程不安全**等等。

---

### 9.  什么是线程死锁?如何避免死锁?

#### 9.1 认识线程死锁

- 定义：多个线程同时被阻塞，它们中的一个或者全部都在等待某个资源被释放。由于线程被无限期地阻塞，因此程序不可能正常终止。
- 如下图所示，线程 A 持有资源 2，线程 B 持有资源 1，他们同时都想申请对方的资源，所以这两个线程就会互相等待而进入死锁状态。

![img](https://my-blog-to-use.oss-cn-beijing.aliyuncs.com/2019-4/2019-4%E6%AD%BB%E9%94%811.png)

下面通过一个例子来说明线程死锁,代码模拟了上图的死锁的情况 (代码来源于《并发编程之美》)：

```java
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
                synchronized (resource2){
                    System.out.println(Thread.currentThread() + "get resource2");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread() + "waiting get resource1");
                    synchronized (resource1) {
                        System.out.println(Thread.currentThread() + "get resource1");
                    }
                }
            }
        },"线程2").start();
    }
}
```

Output:

```IDEA
Thread[线程1,5,main]get resource1
Thread[线程2,5,main]get resource2
Thread[线程1,5,main]waiting get resource2
Thread[线程2,5,main]waiting get resource1
```

线程 A 通过 `synchronized (resource1)` 获得` resource1` 的监视器锁，然后通过`Thread.sleep(1000);`让线程 A 休眠 1秒为的是让线程 B 得到执行然后获取到 `resource2` 的监视器锁。线程 A 和线程 B 休眠结束了都开始企图请求获取对方的资源，然后这两个线程就会陷入互相等待的状态，这也就产生了死锁。上面的例子符合产生死锁的四个必要条件。



学过操作系统的朋友都知道产生死锁必须具备以下四个条件：

1. 互斥条件：该资源任意一个时刻只由一个线程占用。
2. 请求与保持条件：一个进程因请求资源而阻塞时，对已获得的资源保持不放。
3. 不剥夺条件:线程已获得的资源在末使用完之前不能被其他线程强行剥夺，只有自己使用完毕后才释放资源。
4. 循环等待条件:若干进程之间形成一种头尾相接的循环等待资源关系。

#### 9.2 如何避免线程死锁?

我上面说了产生死锁的四个必要条件，为了避免死锁，我们只要破坏产生死锁的四个条件中的其中一个就可以了。现在我们来挨个分析一下：

1. **破坏互斥条件** ：这个条件我们没有办法破坏，因为我们用锁本来就是想让他们互斥的（临界资源需要互斥访问）。
2. **破坏请求与保持条件**  ：一次性申请所有的资源。
3. **破坏不剥夺条件** ：占用部分资源的线程进一步申请其他资源时，如果申请不到，可以主动释放它占有的资源。
4. **破坏循环等待条件** ：靠按序申请资源来预防。按某一顺序申请资源，释放资源则反序释放。破坏循环等待条件。

我们对线程 2 的代码修改成下面这样就不会产生死锁了； 这里也就是让线程1和线程2获取到锁的顺序是一致的。

```java
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

```

OUTPUT;

```
Thread[线程1,5,main]get resource1
Thread[线程1,5,main]waiting get resource2
Thread[线程1,5,main]get resource2
Thread[线程2,5,main]get resource2
Thread[线程2,5,main]waiting get resource1
Thread[线程2,5,main]get resource1
```

- 原因： 这里线程1首先拿到`resource1`的资源，线程2也就拿不到`resource1`的资源, 线程1再去获取`resource2`的资源时候，就不会被线程2影响，当线程1全部执行结束之后，线程2获取`resource1`和`resource2`的相应资源，正常执行.

---

### 10. 说说 sleep() 方法和 wait() 方法区别和共同点?

- **wait释放CPU资源，同时释放锁；sleep释放CPU资源，但是不释放锁，所以易死锁。**
- wait 通常被用于线程间交互/通信，sleep 通常被用于暂停执行。
- wait() 无参方法被调用后，线程不会自动苏醒，需要别的线程调用同一个对象上的 notify() 或者` notifyAll() `方法。sleep() 方法执行完成后，线程会自动苏醒。或者可以使用 wait(long timeout)超时后线程会自动苏醒。
- wait必须放在同步块或同步方法中，而sleep可以在任意位置。

---

### 11. 为什么我们调用 start() 方法时会执行 run() 方法，为什么我们不能直接调用 run() 方法？

new 一个 Thread，线程进入了新建状态;调用 start() 方法，会启动一个线程并使线程进入了就绪状态，当分配到时间片后就可以开始运行了。 start() 会执行线程的相应准备工作，然后自动执行 run() 方法的内容，这是真正的多线程工作。 而直接执行 run() 方法，会把 run 方法当成一个 main 线程下的普通方法去执行，并不会在某个线程中执行它，所以这并不是多线程工作。



**总结： 调用 start 方法方可启动线程并使线程进入就绪状态，而 run 方法只是 thread 的一个普通方法调用，还是在主线程里执行。**

---

