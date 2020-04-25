package yc.al.zuo.base;

import java.util.Arrays;

/**
 * 用数组实现栈和队列
 * @author code_yc
 * @version 1.0
 * @date 2020/4/24 10:12
 */
public class Class3_Code1_Array_To_Stack_Queue {

    // 主函数进行测试
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack);
    }
}

// 使用数组实现栈
class ArrayStack{
    private Integer[] arr;
    private Integer size;

    // 构造方法
    public ArrayStack(int initSize) {
        if(initSize < 0) {
            throw new IllegalArgumentException("The init size is less than 0");
        }
        arr = new Integer[initSize];
        size = 0;
    }

    // 入栈
    public void push(int obj) {
        if(isFull()) {
            throw new ArrayIndexOutOfBoundsException("The stack is Full");
        }
        arr[size++] = obj;
    }

    // 出栈
    public Integer pop() {
        if(isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("The stack is empty!");
        }
        return arr[--size];
    }

    // 栈顶元素
    public Integer peek() {
        if(isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("The stack is empty!");
        }
        return arr[size - 1];
    }

    //toString方法
    @Override
    public String toString() {
        return "ArrayStack{" +
                "arr=" + Arrays.toString(arr) +
                '}';
    }

    // 栈是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 栈是否已满
    public boolean isFull() {
        return size == arr.length;
    }
}

// 使用数组实现队列
class ArrayQueue{
    private Integer[] arr;
    private Integer start;
    private Integer end;
    private Integer size;

    public void ArrayStack(int initSize) {
        if(initSize < 0) {
            throw new IllegalArgumentException("The size is less on 0");
        }
        arr = new Integer[initSize];
        start = 0;
        end = 0;
        size = 0;
    }

    // 入队列
    public void push(Integer obj) {
        if(isFull()) {
            throw new ArrayIndexOutOfBoundsException("The queue is full!");
        }
        arr[end] = obj;
        end = arr.length == end + 1 ? 0 : end + 1;
        size++;
    }

    // 出队列
    public Integer poll() {
        if(isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("The queue is empty!");
        }
        size--;
        int temp = arr[start];
        start = arr.length == start + 1 ? 0 : start + 1;
        return temp;
    }

    // 弹出队列头
    public Integer peek() {
        if(isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("The queue is empty!");
        }
        return arr[start];
    }

    // 检测队列是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    // 检查队列是否已满
    public boolean isFull() {
        return size == arr.length;
    }
}
