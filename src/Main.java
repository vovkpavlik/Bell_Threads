public class Main {
    public static void main(String[] args) throws InterruptedException {
        final int threadsNum = 4;
        final int size = 4;
        int[] nums = new int[size];

        int sum_count_prime = 0; // Общий счетик простых числе по всем потокам
        int sum_massive_number = 0; // Общая сумма всех числе в массиве

        for (int i = 0; i < size; i++) { // Заполнили массив
            nums[i] = i+1;
        }

        int countNumsInThread = size/threadsNum; // Сколько чисел обработает каждый поток

        CountPrimeThread[] threads = new CountPrimeThread[threadsNum];

        for (int i = 0; i < threadsNum; i++) {
            int start = i * countNumsInThread + 1;
            int end = (i+1) * countNumsInThread;
            if (end > size - 1) {
                end = size - 1;
            }
            threads[i] = new CountPrimeThread(end, start, nums);
            threads[i].start();
        }

        for (int i = 0; i < threadsNum; i++) {
            threads[i].join();
            sum_count_prime += threads[i].countPrime;
        }

        for (int i: nums) {
            sum_massive_number += i;
        }

        System.out.println("Простых чисел в массиве: " + sum_count_prime);
        System.out.println("Сумма чисел в массиве: " + sum_massive_number);
    }
}
