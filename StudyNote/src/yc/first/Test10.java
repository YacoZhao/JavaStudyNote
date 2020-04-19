package yc.first;


//10. 正则表达式匹配
public class Test10 {

    //回溯算法

    /**
     * 思路：
     *
     * 1. 首先进行边界条件检查：如果p字符串为空，则结果取决于s字符串是否为空
     * 2. 如果p字符串的下一个字符为'*':
     *      2.1 当p字符串当前的元素不需要时（当前位置p和q不相等），则直接向p字符串的当前元素和*去掉，也就是直接将字符串向前推两位
     *      2.1 如果p当前字符串元素需要时（当前的p和q必须相等），则将s元素向前推一个，直到推成了2.1的样式
     * 3. 如果下个字符不是*，只要当前元素s和p相同，或者p为. 则直接向前推进
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch1(String s, String p) {
        //1. 首先进行边界条件检查：如果p字符串为空，则结果取决于s字符串是否为空
        if(p.isEmpty()) return s.isEmpty();
        //2. 判断s和p的首字母是否相等
        boolean curSite = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
        //2.1 当p字符串当前的元素不需要时（当前位置p和q不相等），则直接向p字符串的当前元素和*去掉，也就是直接将字符串向前推两位
        if(p.length() >= 2 && p.charAt(1) == '*'){
            return isMatch1(s,p.substring(2)) || (curSite && isMatch1(s.substring(1),p));
        }else if(curSite){
            return isMatch1(s.substring(1),p.substring(1));
        }else{
            return false;
        }
    }

    //动态规划
    public boolean isMatch2(String s, String p) {
        return false;
    }
}
