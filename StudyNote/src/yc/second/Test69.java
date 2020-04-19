package yc.second;

public class Test69 {
    /**
     * 二分查找算法(注意指针的越界)
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if (x == 0) return 0;
        if (x < 4) return 1;
        long left = 1;
        long right = x / 2;
        while (left < right) {
            long mid = (left + right + 1) / 2;
            if (mid * mid > (long)x) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return (int)left;
    }

    //二刷
    public int mySqrt1(int x){
        if(x == 0) return 0;
        if(x < 4) return 1;
        long left = 1;
        long right = x / 2;
        while(left <= right){
            long mid = left + (right - left + 1) / 2;
            if(mid * mid > x){
                right = mid - 1;
            }else if(mid * mid < x){
                left = mid + 1;
            }else{
                return (int)mid;
            }
        }
        return (int)left - 1;
    }
}
