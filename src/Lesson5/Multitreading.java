package Lesson5;

import java.util.Arrays;

public class Multitreading  {
    static final int size = 2000000;
    public static void main(String[] args) {
        linear(size);
        multiple2(size);
        multiple4(size);

    }

    public static void  linear(int size) {
    float[] arr = new float[size];
    long a = System.currentTimeMillis();
        Arrays.fill(arr, 1);
    long b = System.currentTimeMillis();
    for (int i = 0; i < arr.length; i++) {
        arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
    }
        System.out.println(b - a);
        System.out.println("Линейная обработка за " + (System.currentTimeMillis() - b) + "мс");
        System.out.println("Значение элемента 1000500 = " + arr[1000500]); //вывожу в консоль контрольное значение из массива
    }

    /*
    Метод multiple2 замеряет скорость вычисление значений массива длиной size при разделении расчета на 2 потока
     */
    public static void multiple2(int size) {

        float[] arr = new float[size];
        final int h = size / 2;

        Arrays.fill(arr, 1);
        long b = System.currentTimeMillis();

        float[] a1 = new float[h];
        float[] a2 = new float[h];

        ArrayAccounting arr1 = new ArrayAccounting(a1, 1, 2, arr.length);
        ArrayAccounting arr2 = new ArrayAccounting(a2, 2, 2, arr.length);
        System.arraycopy(arr, arr1.startedPositionInMainArray, a1, 0, h);
        System.arraycopy(arr, arr2.startedPositionInMainArray, a2, 0, h);
        Thread first = new Thread(arr1);
        Thread second = new Thread(arr2);
        first.start();
        second.start();
        try {
            first.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.arraycopy(arr1.getArr(), 0, arr, arr1.startedPositionInMainArray, h);

        try {
            second.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.arraycopy(arr2.getArr(), 0, arr, arr2.startedPositionInMainArray, h);
        System.out.println("Двухпоточная обработка за " + (System.currentTimeMillis() - b) + "мс");
        System.out.println("Значение элемента 1000500 = " + arr[1000500]); //вывожу в консоль контрольное значение из массива
    }
/*
Метод multiple4 замеряет скорость вычисление значений массива длиной size при разделении расчета на 4 потока
*/

    public static void multiple4(int size) {
        final int h = size / 4;
        float[] arr = new float[size];
        long a = System.currentTimeMillis();
        Arrays.fill(arr, 1);
        long b = System.currentTimeMillis();
        float[] a1 = new float[h];
        float[] a2 = new float[h];
        float[] a3 = new float[h];
        float[] a4 = new float[h];

        ArrayAccounting arr1 = new ArrayAccounting(a1, 1, 4, arr.length);
        ArrayAccounting arr2 = new ArrayAccounting(a2, 2, 4, arr.length);
        ArrayAccounting arr3 = new ArrayAccounting(a3, 3, 4, arr.length);
        ArrayAccounting arr4 = new ArrayAccounting(a4, 4, 4, arr.length);
        System.arraycopy(arr, arr1.startedPositionInMainArray, a1, 0, h);
        System.arraycopy(arr, arr2.startedPositionInMainArray, a2, 0, h);
        System.arraycopy(arr, arr3.startedPositionInMainArray, a3, 0, h);
        System.arraycopy(arr, arr4.startedPositionInMainArray, a4, 0, h);

        Thread first =  new Thread(arr1);
        Thread second = new Thread(arr2);
        Thread third =  new Thread(arr3);
        Thread fourth = new Thread(arr4);
        first.start();
        second.start();
        third.start();
        fourth.start();
        try {
            first.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            second.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            third.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            fourth.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.arraycopy(arr1.getArr(), 0, arr,arr1.startedPositionInMainArray, h);
        System.arraycopy(arr2.getArr(), 0, arr,arr2.startedPositionInMainArray, h);
        System.arraycopy(arr3.getArr(), 0, arr,arr3.startedPositionInMainArray, h);
        System.arraycopy(arr4.getArr(), 0, arr,arr4.startedPositionInMainArray, h);

        System.out.println("Четырехпоточная обработка за " + (System.currentTimeMillis() - b) + "мс");
        System.out.println("Значение элемента 1000500 = " + arr[1000500]); //вывожу в консоль контрольное значение из массива
    }

    private static class ArrayAccounting implements Runnable {
        private float[] arr;
        private int startedPositionInMainArray;

        public ArrayAccounting(float[] array, int arrayNumber, int quantity, int mainArraySize) {
            arr = array;
            startedPositionInMainArray = mainArraySize / quantity * (arrayNumber - 1);
                   }

        public float[] getArr() {
            return arr;
        }

        public int getStartedPositionInMainArray() {
            return startedPositionInMainArray;
        }

        @Override
        public void run() {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = (float)(arr[i] * Math.sin(0.2f + (i + startedPositionInMainArray) / 5)
                        * Math.cos(0.2f + (i + startedPositionInMainArray) / 5)
                        * Math.cos(0.4f + (i + startedPositionInMainArray) / 2));
            }

        }
    }


}
