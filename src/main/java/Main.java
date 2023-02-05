import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        withoutConcurrency();
        withConcurrency();
    }

    public static void withoutConcurrency(){
        float[] list = new float[10000000];
        for(int i = 0; i < 10000000; i++){
            list[i] = 1f;
        }
        long before = System.currentTimeMillis();
        for(int i = 0; i < list.length; i++){
            float f = (float) i;
            list[i] = (float) (list[i] * Math.sin(0.2f + f/5) * Math.cos(0.2f + f/5) * Math.cos(0.4f + f/2));
        }
        long after = System.currentTimeMillis();
        System.out.println(after - before);
    }

    public static void withConcurrency(){
        float[] list = new float[10000000];
        for(int i = 0; i < 10000000; i++){
            list[i] = 1f;
        }
        long before = System.currentTimeMillis();
        float[] list1 = new float[5000000];
        float[] list2 = new float[5000000];
        System.arraycopy(list,0,list1,0,5000000);
        System.arraycopy(list,5000000,list2,0,5000000);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < list1.length; i++){
                    float f = (float) i;
                    list1[i] = (float) (list2[i] * Math.sin(0.2f + f/5) * Math.cos(0.2f + f/5) * Math.cos(0.4f + f/2));
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < list2.length; i++){
                    float f = (float) (i + 5000000);

                    list2[i] = (float) (list2[i] * Math.sin(0.2f + f/5) * Math.cos(0.2f + f/5) * Math.cos(0.4f + f/2));
                }
            }
        });

        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.arraycopy(list1,0,list,0,5000000);
        System.arraycopy(list2,0,list,5000000,5000000);
        long after = System.currentTimeMillis();
        System.out.println(after-before);

    }

}