package yc.second;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Test93 {
    /**
     * 主函数：返回结果集
     *
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        int len = s.length();
        //边界条件判断
        if (len < 4 || len > 12) {
            return res;
        }
        // 存放遍历结果集
        Deque<String> track = new ArrayDeque<>(4);
        int split_time = 0;
        backTrack(s, len, split_time, 0, track, res);
        return res;
    }

    /**
     * 回溯体——先判断是否满足结束条件——再判断是否有必要继续执行——然后按照123的长度顺序依次加入
     *
     * @param s
     * @param len
     * @param split_time
     * @param begin
     * @param track
     * @param res
     */
    private void backTrack(String s, int len, int split_time, int begin, Deque<String> track, List<String> res) {
        //结束条件，如果begin == 长度len，则可以添加
        if (begin == len) {
            if (split_time == 4) {
                res.add(String.join(".", track));
            }
            return;
        }

        //如果没满足当前的结束情况，则检查当前是否有必要继续向下执行
        if ((len - begin) < (4 - split_time) || (len - begin) > ((4 - split_time) * 3)) {
            return;
        }

        //循环遍历，可以向前选中1个，2个或者3个
        for (int i = 0; i < 3; i++) {
            //首先判断新选入之后，长度有没有溢出
            if (begin + i + 1 > len) {
                break;
            }
            //首先判断选中的结果是否是有效的ip段
            int ip_segment = judgeIfIpSegment(s, begin, begin + i);
            if (ip_segment != -1) {
                //将选出来的ip段加入track
                track.addLast(ip_segment + "");
                //下一层
                backTrack(s, len, split_time + 1, begin + i + 1, track, res);
                //撤销回溯
                track.removeLast();
            }
        }
    }

    /**
     * 判断选中的子字符串是否为有效ip
     *
     * @param s
     * @param begin 起始索引
     * @param end   结束索引
     * @return
     */
    private int judgeIfIpSegment(String s, int begin, int end) {
        int length = end - begin + 1;
        //当元素长度大于1时，不可以以0开头
        if (length > 1 && s.charAt(begin) == '0') {
            return -1;
        }

        //计算此段ip代表的值
        int ans = 0;
        for (int i = begin; i <= end; i++) {
            ans = ans * 10 + (s.charAt(i) - '0');
        }

        //如果ans大于255，则不是有效ip段
        if (ans > 255) {
            return -1;
        }

        //返回结果
        return ans;
    }
}
