package gorodilov;

public class Main {

    static final int size = 10000000;
    static final int h = size / 2;


    static class Metod1 implements Runnable {
        @Override
        public void run() {

            float[] arr = new float[size];
            for (int i = 0; i < size; i++) {
                arr[i] = 1;
            }

            long before = System.currentTimeMillis();

            for (int i = 0; i < size; i++) {
                arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            long after = System.currentTimeMillis();
            long diff = after - before;
            System.out.println("Metod1 общее затраченное время: " + diff + "ms");
        }
    }
    static class Metod2 implements Runnable {
        @Override
        public void run() {

            float[] arr = new float[size];
            for (int i = 0; i < size; i++) {
                arr[i] = 1;
            }

            long before = System.currentTimeMillis();

            float[] a1 = new float[h];
            float[] a2 = new float[h];

            long beforetwin = System.currentTimeMillis();

            System.arraycopy(arr, 0, a1, 0, h);
            System.arraycopy(arr, h, a2, 0, h);
            long aftertwin = System.currentTimeMillis()- beforetwin;
            System.out.println("Metod2 после разбивки массивов на 2: " + aftertwin + "ms");

            for (int i = 0; i < h; i++) {
                a1[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                            }
            long firstend = System.currentTimeMillis()-aftertwin-beforetwin;
            System.out.println("Metod2 время просчета первого массива: " + firstend  + "ms");

            for (int i = 0; i < h; i++) {
                a2[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            long secondend = System.currentTimeMillis()- aftertwin-beforetwin-firstend;
            System.out.println("Metod2 время просчета второго массива: " + secondend + "ms");

            System.arraycopy(a1, 0, arr, 0, h);
            System.arraycopy(a2, 0, arr, h, h);

            long arrcopy = System.currentTimeMillis()-aftertwin - secondend -firstend-beforetwin;
            System.out.println("Metod2 время склейки массивов: " + arrcopy + "ms");

            long after = System.currentTimeMillis();
            long diff = after - before;

            System.out.println("Metod2 общее затраченное время: " + diff + "ms");
        }

    }








    public static void main(String[] args) {

        new Thread(new Metod1()).start();
        new Thread(new Metod2()).start();



    }
}