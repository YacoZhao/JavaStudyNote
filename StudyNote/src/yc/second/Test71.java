package yc.second;

import java.util.Stack;

//71. 简化路径
public class Test71 {
    /**
     *利用栈求解
     * @param path
     * @return
     */
    public String simplifyPath(String path) {
        //1. 进行边界条件判断
        if (path == null || path.length() == 0) {
            return null;
        }
        //2. 将给定的字符串按照'/'字符进行划分
        String[] split = path.split("/");
        //3. 创建存放结果的栈
        Stack<String> stack = new Stack<>();
        //4. 遍历字符串数组split
        for (int i = 0; i < split.length; i++) {
            if (split[i].equals("..") && !stack.isEmpty()) {
                //如果取出的字符串为“..” 栈空的话则不做任何操作，栈有元素的话，将栈顶元素弹出
                stack.pop();
            } else if (!split[i].equals(".") && !split[i].equals("") && !split[i].equals("..")) {
                stack.push(split[i]);
            }
        }
        //5. 如果栈为空，则直接返回“/”
        if (stack.isEmpty()) {
            return "/";
        }
        //6. 如果栈不为空，则按规则输出
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < stack.size(); i++) {
            ans.append("/" + stack.get(i));
        }
        return ans.toString();
    }
}
