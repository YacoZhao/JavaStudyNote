package yc.no4;

public class Test278 {
    /**
     *二分查找算法
     * @param n
     * @return
     */
    public int firstBadVersion(int n) {
        if(n == 0) return 0;
        if(n == 1) return isBadVersion(1) ? 1 : 0;
        int left = 1;
        int right = n;
        while(left < right){
            int mid = left + (right - left) / 2;
            if(!isBadVersion(mid)){
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        return left;
    }

    private boolean isBadVersion(int version){
        return true;
    }
}
