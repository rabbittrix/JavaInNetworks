package com.jrsf;

public class ThreadCounter {
    public static void main(String[] args) {
        Accountant c = new Accountant();
        new Thread(c).start();
    }
}

class Accountant implements Runnable{
    static int counter = 0;

    public void run(){
        while (counter++ < 100){
            System.out.println("Counter variable value: " + counter);

            try{
                Thread.sleep(500 * (int)(Math.random() * 5 + 1));
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println("End of count.");
    }
}