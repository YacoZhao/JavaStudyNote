package yc.al.zuo.base;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 基础班————猫狗队列
 * @author code_yc
 * @version 1.0
 * @date 2020/4/24 10:42
 */
public class Class3_Code4_DogCatQueue {

    //宠物类
    public static class Pet {
        private String type;

        public Pet(String type) {
            this.type = type;
        }

        public String getPetType() {
            return this.type;
        }
    }

    //狗类
    public static class Dog extends Pet {
        public Dog() {
            super("dog");
        }
    }

    //猫类
    public static class Cat extends Pet {
        public Cat() {
            super("cat");
        }
    }

    //创建添加的对象类
    public static class EnterPet{
        private Pet pet;
        private long count;

        EnterPet(Pet pet, long count) {
            this.pet = pet;
            this.count = count;
        }

        public Pet getPet() {
            return pet;
        }

        public void setPet(Pet pet) {
            this.pet = pet;
        }

        public long getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }

    // 猫狗队列
    public static class DogCatQueue{
        private Queue<EnterPet> dogQueue;   // 狗队列
        private Queue<EnterPet> catQueue;   // 猫队列
        private long count;                 // 计数器

        public DogCatQueue(){
            this.dogQueue = new LinkedList<EnterPet>();
            this.catQueue = new LinkedList<EnterPet>();
            this.count = 0;
        }

        // 入队列
        public void add(Pet pet) {
            if(pet.getPetType().equals("dog")) {
                dogQueue.add(new EnterPet(pet,count++));
            } else if(pet.getPetType().equals("cat")) {
                catQueue.add(new EnterPet(pet,count++));
            } else {
                throw new RuntimeException("err, not dog and cat!");
            }
        }

        // 出队列（猫和狗都考虑）
        public Pet pollAll() {
            if(!this.dogQueue.isEmpty() && !this.catQueue.isEmpty()) {
                if(this.dogQueue.peek().getCount() > this.catQueue.peek().getCount()){
                    return this.dogQueue.poll().getPet();
                }else{
                    return this.catQueue.poll().getPet();
                }
            }else if(!this.catQueue.isEmpty()){
                return this.catQueue.poll().getPet();
            }else if(!this.dogQueue.isEmpty()) {
                return this.dogQueue.poll().getPet();
            }else{
                throw new RuntimeException("err, queue id empty!");
            }
        }

        // 出队列（只考虑猫）
        public Cat pollCat() {
            if(!this.catQueue.isEmpty()) {
                return (Cat)this.catQueue.poll().getPet();
            }else{
                throw new RuntimeException("err, queue id empty!");
            }
        }

        // 出队列（只考虑狗）
        public Dog pollDog() {
            if(!this.dogQueue.isEmpty()) {
                return (Dog)this.dogQueue.poll().getPet();
            }else{
                throw new RuntimeException("err, queue id empty!");
            }
        }

        // 查看队列是否为空
        public boolean isEmpty() {
            return this.catQueue.isEmpty() && this.dogQueue.isEmpty();
        }

        // 查看狗队列是否为空
        public boolean isDogQueueEmpty() {
            return this.dogQueue.isEmpty();
        }

        // 查看猫队列是否为空
        public boolean isCatQueueEmpty() {
            return this.catQueue.isEmpty();
        }

        // 测试函数
        public static void main(String[] args) {
            DogCatQueue test = new DogCatQueue();

            Pet dog1 = new Dog();
            Pet cat1 = new Cat();
            Pet dog2 = new Dog();
            Pet cat2 = new Cat();
            Pet dog3 = new Dog();
            Pet cat3 = new Cat();

            test.add(dog1);
            test.add(cat1);
            test.add(dog2);
            test.add(cat2);
            test.add(dog3);
            test.add(cat3);

            test.add(dog1);
            test.add(cat1);
            test.add(dog2);
            test.add(cat2);
            test.add(dog3);
            test.add(cat3);

            test.add(dog1);
            test.add(cat1);
            test.add(dog2);
            test.add(cat2);
            test.add(dog3);
            test.add(cat3);
            while (!test.isDogQueueEmpty()) {
                System.out.println(test.pollDog().getPetType());
            }
            while (!test.isEmpty()) {
                System.out.println(test.pollAll().getPetType());
            }
        }
    }
}
