package yc.al.zuo.advanced;

/**
 * 不使用比较运算符，判断两个数中较大的元素
 * @author code_yc
 * @version 1.0
 * @date 2020/4/22 19:36
 */
public class Class1_Code1_GetMax {


    // 功能，输入只可能式0或者1，那么函数功能就是取反
    public static int flip(int n) {
        // 亦或 ： 若n == 0 ，返回1， 若n == 1, 返回0，其实就是取反
        return n ^ 1;
    }

    // 功能：若n为负数，返回0，否则返回1.
    public static int sign(int n) {
        // n >> 31 获取n的最高位，如果n为负数，返回-1，否则返回0
        // (n >> 31) & 1 : 0 & 1 = 0    -1 & 1 = 1;
        return flip((n >> 31) & 1);
    }

    // 有溢出的可能性，错误代码
    public static int getMax1(int a, int b) {
        int c = a - b;
        int scA = sign(c);  	// 如果c>0, 则返回1
        int scB = flip(scA);    // 另一位变反，保证结果只可以从a和b中选一个
        return a * scA + b * scB;
    }

    // 解决了溢出问题
    public static int getMax2(int a, int b) {
        int c = a - b;
        int sa = sign(a);   // 如果a>0, 则返回1
        int sb = sign(b);   // 如果b>0, 则返回1
        int sc = sign(c);   // 如果c>0, 则返回1
        int difSab = sa ^ sb;  // 同号返回0，异号返回1
        int sameSab = flip(difSab);  // 同号返回1，异号返回0
        int returnA = difSab * sa + sameSab * sc;  // 如果同号，计算sc，如果异号，计算sa
        int returnB = flip(returnA);
        return a * returnA + b * returnB;
    }

    public static void main(String[] args) {
        int a = -16;
        int b = 1;
        System.out.println(getMax1(a, b));
        System.out.println(getMax2(a, b));
        a = 2147483647;
        b = -2147480000;
        System.out.println(getMax1(a, b)); // wrong answer because of overflow
        System.out.println(getMax2(a, b));

    }

    // 基本思路：
    // 1. 首先明确一个f(n)函数的功能，给定一个元素，如果其值为正，则返回1，如果为负返回0
    // 2. 给定两个数a和b，两个数作差值
    // 2.1 如果两个数同号，则不可能出现溢出的可能，直接对差值c进行进行f(c)判断，结果为 f(c) * a + ^f(c) * b
    // 2.2 如果两个数异号，则只要找出为正数的哪个值就可以了，直接对a进行f(a)判断，结果为f(a) * a + ^f(a) * b
    // 3. 接下来就是具体的实现
    public int f(int n){
        // (n >> 31) & 1 如果n为整数，返回0 ，如果n为负数， 返回1， 这跟我们想要的结果正好相反，如果加上亦或操作
        return r((n >> 31) & 1);
    }

    // 输入只可能为0或者1，直接变反
    public int r(int n){
        return n ^ 1;
    }

    // 进入主函数
    public int getMax(int a, int b) {
        int fuA = f(a);   // 了解a的符号位
        int fuB = f(b);   // 了解b的符号位
        // 如果式异号，这里结果已经出来了，但是如果同号，还得计算差值
        int c = a - b;
        int fuC = f(c);
        int dif = fuA ^ fuB;   // 相同返回0，不相同返回1，如果dif不为0，则表示A,B异号
        int sam = r(dif);      // 同号标志
        int resA = dif * fuA + sam * fuC;
        int resB = r(resA);
        return resA * a + resB * b;
    }

}
