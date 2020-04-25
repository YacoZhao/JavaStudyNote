package yc.al.zuo.base;

import java.util.Arrays;
import java.util.Stack;

/**
 * 设计一个可以获取最小元素的栈
 * @author code_yc
 * @version 1.0
 * @date 2020/4/24 10:16
 */
public class Class3_Code2_GetMinStack {

    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //                             方法一： 直接用类库进行实现
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // 直接用类库实现
    public static class MyStack {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack() {
            this.stackData = new Stack<Integer>();
            this.stackMin = new Stack<Integer>();
        }

        public void push(int newNum) {
            if (this.stackMin.isEmpty()) {
                this.stackMin.push(newNum);
            } else if (newNum <= this.getMin()) {
                this.stackMin.push(newNum);
            }
            this.stackData.push(newNum);
        }

        public int pop() {
            if (this.stackData.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            int value = this.stackData.pop();
            if (value == this.getMin()) {
                this.stackMin.pop();
            }
            return value;
        }

        public int getMin() {
            if (this.stackMin.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            return this.stackMin.peek();
        }
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //                                方法二： 通过自定义的栈来进行实现
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // 使用数组实现栈（自己用数组实现）
    static class ArrayStackMin{
        private Integer[] arrData;  // 存放元素
        private Integer[] arrMin;   // 存放当前的最小值
        private Integer size;

        // 构造方法
        public ArrayStackMin(int initSize) {
            if(initSize < 0) {
                throw new IllegalArgumentException("The init size is less than 0");
            }
            arrData = new Integer[initSize];
            arrMin = new Integer[initSize];
            size = 0;
        }

        // 入栈
        public void push(int obj) {
            if(isFull()) {
                throw new ArrayIndexOutOfBoundsException("The stack is Full");
            }
            arrData[size] = obj;
            arrMin[size] = isEmpty() ? obj : obj > arrMin[size - 1] ? arrMin[size - 1] : obj;
            size++;
        }

        // 出栈
        public Integer pop() {
            if(isEmpty()) {
                throw new ArrayIndexOutOfBoundsException("The stack is empty!");
            }
            return arrData[--size];
        }

        // 栈顶元素
        public Integer peek() {
            if(isEmpty()) {
                throw new ArrayIndexOutOfBoundsException("The stack is empty!");
            }
            return arrData[size - 1];
        }

        //toString方法
        @Override
        public String toString() {
            return "ArrayStack{" +
                    "arr=" + Arrays.toString(arrData) +
                    '}';
        }

        // 获取当前栈中的最小值
        public Integer getMin(){
            if(isEmpty()) {
                throw new ArrayIndexOutOfBoundsException("The stack is empty!");
            }
            return arrMin[size - 1];
        }

        // 栈是否为空
        public boolean isEmpty() {
            return size == 0 ? true : false;
        }

        // 栈是否已满
        public boolean isFull() {
            return size == arrData.length ? true : false;
        }
    }

    // 测试函数
    public static void main(String[] args) {
        MyStack stack1 = new MyStack();
        stack1.push(3);
        System.out.println(stack1.getMin());
        stack1.push(4);
        System.out.println(stack1.getMin());
        stack1.push(1);
        System.out.println(stack1.getMin());
        System.out.println(stack1.pop());
        System.out.println(stack1.getMin());

        System.out.println("=============");

        ArrayStackMin stack2 = new ArrayStackMin(10);
        stack2.push(3);
        System.out.println(stack2.getMin());
        stack2.push(4);
        System.out.println(stack2.getMin());
        stack2.push(1);
        System.out.println(stack2.getMin());
        System.out.println(stack2.pop());
        System.out.println(stack2.getMin());
    }
}
