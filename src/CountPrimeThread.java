import java.math.BigInteger;

class CountPrimeThread extends Thread{
    private int end;
    private int start;
    private int[] array;
    public int countPrime;
    public int sumNumbers;

    public CountPrimeThread(int end, int start, int[] array) {
        this.end = end;
        this.start = start;
        this.array = array;
    }

    private boolean checkPrimeNumber(int number) {
        BigInteger bint = BigInteger.valueOf(number);
        return bint.isProbablePrime((int) Math.log(number));
    }

    private int countPrimeNumbers() {
        int count = 0;
        for (int i = start; i <= end; i++) {
            if (checkPrimeNumber(i)) {
                count++;
            }
        }
        return count;
    }

    private int getSumNumbers() {
        int sumNumbers = 0;
        for (int i = start; i <= end; i++) {
            sumNumbers += i;
        }
        return sumNumbers;
    }


    public void run() {
        countPrime = countPrimeNumbers();
        sumNumbers = getSumNumbers();
    }
}
