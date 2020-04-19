package yc.first;

import java.util.HashMap;
import java.util.Stack;

//20. 有效的括号
public class Test20 {

    //辅助栈的思想
    public boolean isValid(String s) {
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (map.containsKey(ch)) {
                char topStack = stack.isEmpty() ? '#' : stack.pop();
                if (topStack != map.get(ch)) {
                    return false;
                }
            }else{
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }
}
