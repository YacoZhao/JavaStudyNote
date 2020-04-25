package yc.al.zuo.base;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 用栈和队列实现互相转换
 * @author code_yc
 * @version 1.0
 * @date 2020/4/24 10:22
 */
public class Class3_Code3_StackAndQueueConvert {

    /**
     * 使用两个栈实现队列
     */
    public static class TwoStacksQueue {
        private Stack<Integer> stackPush;
        private Stack<Integer> stackPop;

        public TwoStacksQueue() {
            stackPush = new Stack<Integer>();
            stackPop =  new Stack<Integer>();
        }

        // 入队列
        public void push(Integer obj) {
            stackPush.push(obj);
        }

        // 出队列(当pop栈为空的时候，将push栈中的所有元素都放入pop栈中，然后弹出pop栈的栈顶元素，实现先进先出)
        public Integer pop() {
            if(stackPush.empty() && stackPop.empty()){
                throw new RuntimeException("The queue is empty!");
            } else if(stackPop.empty()) {
                while(!stackPush.empty()) {
                    stackPop.push(stackPush.pop());
                }
            }
            return stackPop.pop();
        }

        // 查看队列头的元素
        public Integer peek() {
            if(stackPush.empty() && stackPop.empty()){
                throw new RuntimeException("The queue is empty!");
            } else if(stackPop.empty()) {
                while(!stackPush.empty()) {
                    stackPop.push(stackPush.pop());
                }
            }
            return stackPop.peek();
        }
    }

    /**
     * 使用两个队列实现栈（先进后出）
     */
    public static class TwoQueuesStack {
        private Queue<Integer> queue;
        private Queue<Integer> help;

        // 构造器
        public TwoQueuesStack(){
            queue = new LinkedList<Integer>();
            help = new LinkedList<Integer>();
        }

        // 入栈
        public void push(Integer obj) {
            queue.add(obj);
        }

        // 出栈
        public Integer pop() {
            if(queue.isEmpty()) {
                throw new RuntimeException("Stack is Empty!");
            }
            while(queue.size() > 1) {
                help.add(queue.poll());
            }
            int res = queue.poll();
            swap();
            return res;
        }

        private void swap() {
            Queue<Integer> tmp = help;
            help = queue;
            queue = tmp;
        }

        // 查看栈顶元素
        public Integer peek() {
            if(queue.isEmpty()) {
                throw new RuntimeException("Stack is Empty!");
            }
            while(queue.size() > 1) {
                help.add(queue.poll());
            }
            int res = queue.peek();
            help.add(queue.poll());
            swap();
            return res;
        }
    }

    // 测试用栈模拟列
    public static void main(String[] args) {
//        TwoStacksQueue queue = new TwoStacksQueue();
//        queue.push(1);
//        queue.push(2);
//        queue.push(3);
//        queue.push(4);
//        queue.push(5);
//        System.out.println(queue.pop());
//        System.out.println(queue.pop());
//        System.out.println(queue.pop());
//        System.out.println(queue.pop());
//        System.out.println(queue.pop());

        // 测试用队列模拟栈
        TwoQueuesStack stack = new TwoQueuesStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
