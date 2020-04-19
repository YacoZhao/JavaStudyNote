### 泛型的实际应用

#### 实现最小值函数
自己设计一个泛型的获取数组最小值的函数.并且这个方法只能接受Number的子类并且实现了Comparable接口。
```java
public class MinValueOfArr {

    /**
     * 自己设计一个泛型的获取数组最小值的函数.并且这个方法只能接受Number的子类并且实现了Comparable接口。
     * @param values
     * @param <T>
     * @return
     */
    public static <T extends Number & Comparable<? super T>> T min(T[] values){
        if(values == null || values.length == 0){
            return null;
        }
        T min = values[0];
        for (int i = 1; i < values.length; i++) {
            min = min.compareTo(values[i]) > 0 ? values[i] : min;
        }
        return min;
    }

    // 测试函数
    public static void main(String[] args) {
        int minInteger = min(new Integer[]{1, 2, 3});//result:1
        double minDouble = min(new Double[]{1.2, 2.2, -1d});//result:-1d
        //String typeError = min(new String[]{"1","3"});//报错

        System.out.println("最小的整数为：" + minInteger);
        System.out.println("最小的浮点数为：" + minDouble);
    }
}
```
### 数据结构
#### 使用数组实现栈
自己实现一个栈，要求这个栈具有`push()`、`pop()`（返回栈顶元素并出栈）、`peek()` （返回栈顶元素不出栈）、`isEmpty()`、`size()`这些基本的方法。
提示：每次入栈之前先判断栈的容量是否够用，如果不够用就用Arrays.copyOf()进行扩容；
```java
/**
 * 使用数组实现栈
 * @author code_yc
 * @version 1.0
 * @date 2020/4/19 22:52
 */
public class ArrayToStack {

    public static class MyStack{
        private int[] storage;  // 存储数据的数组
        private int capacity;   // 当前数组的容量
        private int count;      // 当前栈中元素的个数
        private static final int GROW_FACTOR = 2;   // 数组扩容系数

        // TODO 创建空参构造器
        public MyStack(){
            this.capacity = 8;    // 初始容量为8
            this.storage = new int[8];
            this.count = 0;
        }

        // TODO 创建带初始数组容量的构造器
        public MyStack(int initCapacity){
            this.capacity = initCapacity;
            this.storage = new int[initCapacity];
            this.count = 0;
        }

        // TODO 向栈中添加元素，入栈操作（push）
        public void push(int num){
            if(this.count == this.capacity){
                // 数组扩容
                enlargeArr();
            }
            storage[count++] = num;
        }

        // TODO 从数组中弹出元素，出栈操作（pop）
        public int pop(){
            if(this.count == 0){
                throw new RuntimeException("Stack is empty");
            }
            count--;
            return storage[count];
        }

        // TODO 数组扩容操作
        public void enlargeArr(){
            int newCapacity = this.capacity * GROW_FACTOR;
            storage = Arrays.copyOf(storage,newCapacity);
            this.capacity = newCapacity;
        }

        // TODO 查看栈顶元素（peek）
        public int peek(){
            if(this.count == 0){
                throw new RuntimeException("Stack is empty");
            }
            return storage[count - 1];
        }

        // TODO 检查当前栈是否为空（isEmpty）
        public boolean isEmpty(){
            return this.count == 0;
        }

        // TODO 查询当前栈的存储的记录条数（size）
        public int size() {
            return this.count;
        }
    }


    // +++++测试集+++++
    public static void main(String[] args) {
        MyStack myStack = new MyStack(3);
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        myStack.push(5);
        myStack.push(6);
        myStack.push(7);
        myStack.push(8);
        System.out.println(myStack.peek());//8
        System.out.println(myStack.size());//8
        for (int i = 0; i < 8; i++) {
            System.out.println(myStack.pop());
        }
        System.out.println(myStack.isEmpty());//true
        // myStack.pop();//报错：java.lang.IllegalArgumentException: Stack is empty.
    }
}

```