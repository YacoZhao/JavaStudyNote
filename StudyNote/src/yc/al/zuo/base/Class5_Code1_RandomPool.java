package yc.al.zuo.base;

import java.util.HashMap;

/**
 * 设计一种RandomPool结构
 * @author code_yc
 * @version 1.0
 * @date 2020/4/24 18:56
 */
public class Class5_Code1_RandomPool {
    // 设计的一种结构
    public static class Pool<K> {
        // 成员变量
        private HashMap<K,Integer> keyIndexMap;
        private HashMap<Integer,K> indexKeyMap;
        private int size;

        // 构造器
        public Pool() {
            keyIndexMap = new HashMap<K,Integer>();
            indexKeyMap = new HashMap<Integer, K>();
            this.size = 0;
        }

        // 向此结构中插入一个对象
        public void insert(K key) {
            if(!this.keyIndexMap.containsKey(key)){
                this.keyIndexMap.put(key,this.size);
                this.indexKeyMap.put(this.size++,key);
            }
        }

        // 从此结构中删除一个对象
        public void delete(K key) {
            if(this.keyIndexMap.containsKey(key)){
                int deleteIndex = this.keyIndexMap.get(key);
                K lastKey = this.indexKeyMap.get(this.size - 1);
                this.keyIndexMap.put(lastKey,deleteIndex);    // 把最后一个元素的位置放到要删除的节点的位置
                this.indexKeyMap.put(deleteIndex,lastKey);// 最后一个对向给删除(这里已经更新过了，所以感觉这部是不需要的)
                this.indexKeyMap.remove(--this.size);
            }
        }

        // 随机获取一个元素，要求是等可能性获取
        public K getRandom(){
            if(this.size == 0) return null;
            int randomIndex = (int)(Math.random() * this.size);
            return this.indexKeyMap.get(randomIndex);
        }

        public static void main(String[] args) {
            Pool<String> pool = new Pool<String>();
            pool.insert("zuo");
            pool.insert("cheng");
            pool.insert("yun");
            pool.delete("yun");
            System.out.println(pool.getRandom());
            System.out.println(pool.getRandom());
            System.out.println(pool.getRandom());
            System.out.println(pool.getRandom());
            System.out.println(pool.getRandom());
            System.out.println(pool.getRandom());
        }
    }
}
