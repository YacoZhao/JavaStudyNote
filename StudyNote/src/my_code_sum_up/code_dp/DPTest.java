package my_code_sum_up.code_dp;

import com.sun.org.apache.xalan.internal.res.XSLTErrorResources;
import com.sun.org.apache.xpath.internal.operations.Bool;
import my_code_sum_up.code_tree.util.TreeNode;
import sun.reflect.generics.tree.Tree;

import java.util.*;

public class DPTest {


    public boolean wordBreak(String s, List<String> wordDict) {
        // 首先将字典加入一个集合中
        HashSet<String> set = new HashSet<>();
        int maxSize = Integer.MIN_VALUE; // 定义一个变量字典中字符串的最大长度
        for (String str : wordDict) {
            if(!set.contains(str)){
                maxSize = Math.max(maxSize,str.length());
                set.add(str);
            }
        }
        // 进入递归
        HashMap<Integer,Boolean> memo = new HashMap<>();
        return getRes(s,0,set,maxSize, s.length(), memo);
    }

    private boolean getRes(String s, int c, HashSet<String> set, int maxSize, int len, HashMap<Integer,Boolean> memo) {
        if(c == len) return true;
        if(memo.containsKey(c)) return memo.get(c);
        for (int i = c; i < c + maxSize && i < len; i++) {
            String cur = s.substring(c,i + 1);
            if(set.contains(cur)){
                if(getRes(s, i+1, set, maxSize, len, memo)){
                    memo.put(c,true);
                    return true;
                }
            }
        }
        memo.put(c,false);
        return false;
    }



    /**
     * leecode10:正则表达式匹配
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch1(String s, String p) {
        if (s == null || p == null) return false;
        // dp[i][j]表示s的前i个字符组成的字符串是否可以和p的前j个字符组成的字符串匹配
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        // 初始化dp[0][2] - dp[0][p.length()]： 当p当前为'*'时，前面的字符串可以置null
        for (int k = 2; k <= p.length(); k++) {
            dp[0][k] = p.charAt(k - 1) == '*' && dp[0][k - 2];
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                // 如果当前的s和p可以匹配，那么当前值等于各自的前一个值
                if (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)) {
                    dp[i + 1][j + 1] = dp[i][j];
                }
                // 如果当前p为'*'
                if (p.charAt(j) == '*') {
                    if (p.charAt(j - 1) != '.' && p.charAt(j - 1) != s.charAt(i)) {
                        dp[i + 1][j + 1] = dp[i + 1][j - 1];
                    } else {
                        dp[i + 1][j + 1] = dp[i + 1][j] || dp[i][j + 1] || dp[i + 1][j - 1];
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    /**
     * leeCode32：最长有效括号
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        // 1. 初始化，当s长度小于2时，一定不能构成有效的括号
        if (s == null || s.length() < 2) return 0;
        int len = s.length();
        // 2. 创建dp数组，dp[i]表示0-i位置的子串可以构成的最长的包含有效括号的子串的长度
        int[] dp = new int[len];
        // 3. 设置结果存储变量
        int maxLen = 0;
        for (int i = 1; i < len; i++) {
            // 如果当前匹配到')'
            if (s.charAt(i) == ')') {
                // 检查前一个元素是否为'('
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i - 1 > 0 ? dp[i - 2] : 0) + 2;
                    // 如果当前位置前一个为')',则累加前面的计算结果
                } else if ((i - dp[i - 1] - 1) >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = (i - dp[i - 1] - 1 > 0 ? dp[i - dp[i - 1] - 2] : 0) + dp[i - 1] + 2;
                }
                maxLen = Math.max(dp[i], maxLen);
            }
        }
        return maxLen;
    }

    /**
     * LeeCode44 ：通配符匹配问题
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch2(String s, String p) {
        // 1. 边界条件判断： 当p为空时，结果取决于s是否为空字符串
        if (p.equals("")) return s.equals("");
        // 2. 创建dp数组，标签s的前i个元素和p的前j个元素是否可以匹配
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        // 3. 初始化:两者都为“”时匹配成功，如果s为空，则p必须全为'*',才可以匹配成功
        dp[0][0] = true;
        for (int k = 1; k <= p.length(); k++) {
            dp[0][k] = (p.charAt(k - 1) == '*') && dp[0][k - 1];
        }
        // 4. 遍历两个字符串，填数组
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i)) {
                    dp[i + 1][j + 1] = dp[i][j];
                }
                if (p.charAt(j) == '*') {
                    dp[i + 1][j + 1] = dp[i][j] || dp[i][j + 1] || dp[i + 1][j];
                }
            }
        }
        // 5. 返回结果
        return dp[s.length()][p.length()];
    }

    /**
     * leeCode53 : 最大子序和
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 创建辅助数组
        int[] dp = new int[nums.length];
        // 初始化
        dp[0] = nums[0];
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 当前元素为前面和加上当前值和当前值中取最大值，保证每次nums[i]都在选择范围中
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            maxSum = Math.max(maxSum, dp[i]);
        }
        return maxSum;
    }

    /**
     * leeCode62： 不同路径
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        // 1. 边界条件
        if (m == 1 || n == 1) return 1;
        // 2. 创建dp数组并初始化
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * leeCode63: 不同路径2
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // 1. 初始化边界条件等等
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        // 2. 创建数组
        int[][] dp = new int[row][col];
        // 3. 遍历数组，填表
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (obstacleGrid[i][j] == 0) {
                    if (i == 0 && j == 0) {
                        dp[i][j] = 1;
                    } else if (j == 0) {
                        dp[i][j] = dp[i - 1][j];
                    } else if (i == 0) {
                        dp[i][j] = dp[i][j - 1];
                        ;
                    } else {
                        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                    }
                }
            }
        }
        return dp[row - 1][col - 1];
    }

    /**
     * leecode64: 最小路径和
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        // 初始化
        int row = grid.length;
        int col = grid[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = grid[0][0];
        // 遍历填数组
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[0][0];
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
        }
        return dp[row - 1][col - 1];
    }

    /**
     * leecode70: 爬楼梯
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        int dp[] = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * leecode72：编辑距离
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        if (word1.equals("")) return word2.length();
        if (word2.equals("")) return word1.length();
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                if (i == 0) {
                    dp[i][j] = j;  // 如果word1为空，则操作次数等于word2的长度
                } else if (j == 0) {
                    dp[i][j] = i;  // 如果word2为空，则操作次数等于word1的长度
                } else {
                    if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                        // 如果当前word1和word2的字符不一样，增删改操作取最小然后加1
                        dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    } else {
                        // 如果一样，则直接在原始的基础上+1即可
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[len1][len2];
    }

    /**
     * leecode87 扰乱字符串————递归求解
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean isScramble(String s1, String s2) {
        // 首先判断终止情况
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.equals(s2)) {
            return true;
        }
        // 遍历s1和s2的所有字符，如果不一样则返回false
        int[] dp = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            dp[s1.charAt(i) - 'a']++;
            dp[s2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] != 0) {
                return false;
            }
        }
        // 递归进行遍历
        for (int i = 1; i < s1.length(); i++) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) &&
                    isScramble(s1.substring(i), s2.substring(i))
            ) {
                return true;
            }
            if (isScramble(s1.substring(0, i), s2.substring(s2.length() - i)) &&
                    isScramble(s1.substring(i), s2.substring(0, s2.length() - i))
            ) {
                return true;
            }
        }
        return false;
    }

    // 动态规划求解
    public boolean isScramble2(String s1, String s2) {
        // 首先判断终止情况
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.equals(s2)) {
            return true;
        }
        // 遍历s1和s2的所有字符，如果不一样则返回false
        int[] cp = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            cp[s1.charAt(i) - 'a']++;
            cp[s2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < cp.length; i++) {
            if (cp[i] != 0) {
                return false;
            }
        }
        // 创建dp数组
        int len = s1.length();
        boolean[][][] dp = new boolean[len + 1][len][len];
        // dp[k][i][j] 表示从s1的i位置开始长度为k的字符串是否可以匹配s2从j位置开始长度为k的字符串。返回值为dp[s1.length()][0][0];
        // 遍历添数组
        for (int lens = 1; lens <= len; lens++) {
            // 遍历，从长度1开始
            for (int i = 0; i + lens <= len; i++) {
                for (int j = 0; j + lens <= len; j++) {
                    if (lens == 1) {
                        dp[lens][i][j] = s1.charAt(i) == s2.charAt(j);
                    } else {
                        // 遍历切割后左半部分的长度
                        for (int k = 1; k < lens; k++) {
                            dp[lens][i][j] = (dp[k][i][j] && dp[lens - k][i + k][j + k])
                                    || (dp[k][i][j + lens - k] && dp[lens - k][i + k][j]);
                            // 如果当前true直接break
                            if (dp[lens][i][j]) {
                                break;
                            }
                        }
                    }
                }
            }
        }
        return dp[len][0][0];
    }

    /**
     * 91.解码方法： 暴力递归
     *
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        return getAns(s, 0);
    }

    private int getAns(String s, int start) {
        if (start == s.length()) return 1;
        if (s.charAt(start) == '0') return 0;   //如果遇到了以0开头的匹配符直接返回0；
        int ans1 = getAns(s, start + 1);
        int ans2 = 0;
        if (start < s.length() - 1) {
            int a = s.charAt(start) - '0';
            int b = s.charAt(start + 1) - '0';
            if (a * 10 + b <= 26) {
                ans2 = getAns(s, start + 2);
            }
        }
        return ans1 + ans2;
    }

    //暴力递归改动态规划
    public int numDecodings2(String s) {
        // 从数组尾部开始计算
        int len = s.length();
        int[] dp = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            // 1.如果当前位置的元素为0.那么直接返回0
            if (s.charAt(i) == '0') {
                dp[i] = 0;
                continue;
            }
            // 2.计算最后一位的时候，直接赋值
            if (i == len - 1) {
                dp[i] = 1;
            } else {
                //3.非最后一位，计算前后和是否在26的范围内
                int a = s.charAt(i) - '0';
                int b = s.charAt(i + 1) - '0';
                if (a * 10 + b <= 26) {
                    dp[i] = dp[i + 1] + (i < len - 2 ? dp[i + 2] : 1);
                } else {
                    dp[i] = dp[i + 1];
                }
            }
        }
        return dp[0];
    }

    /**
     * 91：不同的二叉树：： 递归
     *
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> ans = new ArrayList<TreeNode>();
        if (n != 0) {
            return getTreeList(1, n);
        }
        return ans;
    }

    private List<TreeNode> getTreeList(int s, int e) {
        List<TreeNode> ans = new ArrayList<TreeNode>();
        if (s > e) {
            ans.add(null);
            return ans;
        }
        if (s == e) {
            ans.add(new TreeNode(s));
            return ans;
        }
        for (int cur = s; cur <= e; cur++) {
            List<TreeNode> lefts = getTreeList(s, cur - 1);
            List<TreeNode> rights = getTreeList(cur + 1, e);
            for (TreeNode right : rights) {
                for (TreeNode left : lefts) {
                    TreeNode root = new TreeNode(cur);
                    root.left = left;
                    root.right = right;
                    ans.add(root);
                }
            }
        }
        return ans;
    }

    /**
     * 96不同的二叉树2 ————动态规划
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {
        if (n < 2) return 1;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            for (int j = i - 1; j >= 0; j--) {
                dp[i] += dp[j] * dp[i - 1 - j];
            }
        }
        return dp[n];
    }

    public String testManacher(String s) {
        // 首先将字符串加上虚轴
        char[] chars = new char[s.length() * 2 + 1];
        int n = chars.length;
        for (int i = 0; i < n; i++) {
            chars[i] = i % 2 == 0 ? '#' : s.charAt(i/2);
        }
        // 创建记录最大回文半径的数组, 最大回文右边界和回文中心
        int[] radius = new int[n];
        int R = -1;
        int C = -1;
        for (int i = 0; i < n; i++) {
            radius[i] = R > i ? Math.min(R - i, radius[2*C-i]) : 1;
            // 进行中心扩展
            while(i + radius[i] < n && i - radius[i] >= 0) {
                if(chars[i - radius[i]] == chars[i + radius[i]]){
                    radius[i]++;
                }else{
                    break;
                }
            }
            // 更新最大回文半径
            if(i + radius[i] > R){
                R = i + radius[i];
                C = i;
            }
        }
        // 然后就是找出最长的回文字符
        int maxLen = radius[0];
        int maxC = 0;
        for (int i = 1; i < n; i++) {
            if(radius[i] > maxLen){
                maxLen = radius[i];
                maxC = i;
            }
        }
        // 计算出回文串的左右边界
        int start = (maxC - maxLen + 1) / 2;
        int end = start + (maxLen - 1);
        return s.substring(start,end);
    }


    public static void main(String[] args) {
        new StringBuilder();
        new StringBuffer();
    }
}
