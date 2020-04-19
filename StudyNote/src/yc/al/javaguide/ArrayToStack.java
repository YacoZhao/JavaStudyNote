package yc.al.javaguide;

import java.net.InetAddress;
import java.util.Arrays;

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
