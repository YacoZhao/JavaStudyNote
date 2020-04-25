package yc.al.zuo.base;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 会议室安排问题
 * @author code_yc
 * @version 1.0
 * @date 2020/4/25 8:35
 */
public class Class7_Code6_BestArrange {

    // 创建项目的实体类
    public static class Program{
        int start;  //项目开始时间
        int end;    //项目结束时间

        public Program(int start, int end){
            this.start = start;
            this.end = end;
        }
    }

    // 创建一个项目按照结束时间从小到大的排列顺序
    public static class MinEndTimeComparator implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }

    /**
     * 主函数入口
     * @param programs  给定项目的数组（包含项目的开始时间和结束时间）
     * @param start     会议室可以使用的开始时间
     * @return          最多会议室的使用次数
     */
    public static int bestArrange(Program[] programs, int start) {
        PriorityQueue<Program> queue = new PriorityQueue<>(new MinEndTimeComparator());
        for (int i = 0; i < programs.length; i++) {
            queue.add(programs[i]);
        }
        int res = 0;
        while(!queue.isEmpty()){
            if(start <= queue.peek().start) {
                res++;
                start = queue.poll().end;
            }else{
                queue.poll();
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Program p1 = new Program(1,3);
        Program p2 = new Program(2,4);
        Program p3 = new Program(3,6);
        Program p4 = new Program(5,6);
        Program p5 = new Program(7,8);
        Program[] ps = new Program[]{p1,p2,p3,p4,p5};
        int count = bestArrange(ps, 1);
        System.out.println(count);
    }
}
