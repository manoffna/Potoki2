package com.company;

public class Main {

    public static void main(String[] args) {
                
        SecondMethod();
    };

        public static void SecondMethod () {
            int size = 10_000_000;
            final int half = size / 2;
            float[] arr = new float[size];
            Object monLeft = new Object();
            Object monRight = new Object();


            for (int i = 0; i < arr.length; i++) {
                arr[i] = 1;
            }
            ;
            long a = System.currentTimeMillis();
            System.out.println(a);

            float[] leftHalf = new float[arr.length / 2];
            float[] rightHalf = new float[arr.length / 2];
            float[] bigRightHalf = new float[arr.length / 2];
            float[] bigLeftHalf = new float[arr.length / 2];
            System.arraycopy(arr, 0, leftHalf, 0, arr.length / 2);
            System.arraycopy(arr, arr.length / 2, rightHalf, 0, arr.length / 2);

            Thread thread1 = new Thread(() -> {
                synchronized (monLeft) {
                    for (int i = 0; i < rightHalf.length; i++) {
                        bigLeftHalf[i] = (float) (leftHalf[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                    }
                    ;
                }
                System.out.println("First thread time: " + (System.currentTimeMillis() - a) + " ms.");
            });

            Thread thread2 = new Thread(() -> {
                synchronized (monRight) {
                    for (int i = 0; i < rightHalf.length; i++) {
                        bigRightHalf[i] = (float) (rightHalf[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                    }
                    ;
                }
                System.out.println("Second thread time: " + (System.currentTimeMillis() - a) + " ms.");
            });

            thread1.start();
            thread2.start();

            float[] mergedArray = new float[arr.length];
            System.arraycopy(bigLeftHalf, 0, mergedArray, 0, arr.length / 2);
            System.arraycopy(bigRightHalf, 0, mergedArray, arr.length / 2, arr.length / 2);
            System.out.println("Gluing time: " + (System.currentTimeMillis() - a) + " ms.");

        }

        public void Rhalf ( float half[]){
            Object monRight = new Object();

        }

        public void Lhalf ( float half[]){
            Object monLeft = new Object();
            synchronized (monLeft) {
                for (int i = 0; i < half.length; i++) {
                    half[i] = (float) (half[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
                ;
            }
        }
    };
