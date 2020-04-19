package yc.no4;

public class Test275 {
    /**
     * 遍历
     * @param citations
     * @return
     */
    public int hIndex(int[] citations) {
        if(citations.length == 0) return 0;
        int h = citations.length;
        for (int i = 0; i < citations.length; i++) {
            if(citations[i] >= h){
                return h;
            }
            h--;
        }
        return h;
    }
}


