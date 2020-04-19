package yc.al.javaguide;

public class MinValueOfArr {

    /**
     * 自己设计一个泛型的获取数组最小值的函数.并且这个方法只能接受Number的子类并且实现了Comparable接口。
     * @param values
     * @param <T>
     * @return
     */
    public static <T extends Number & Comparable<? super T>> T min(T[] values){
        if(values == null || values.length == 0){
            return null;
        }
        T min = values[0];
        for (int i = 1; i < values.length; i++) {
            min = min.compareTo(values[i]) > 0 ? values[i] : min;
        }
        return min;
    }

    // 测试函数
    public static void main(String[] args) {
        int minInteger = min(new Integer[]{1, 2, 3});//result:1
        double minDouble = min(new Double[]{1.2, 2.2, -1d});//result:-1d
        //String typeError = min(new String[]{"1","3"});//报错

        System.out.println("最小的整数为：" + minInteger);
        System.out.println("最小的浮点数为：" + minDouble);
    }
}
