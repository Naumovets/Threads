package bank;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();

        Thread dan = new Thread(new Runnable() {
            @Override
            public void run() {
                bank.getMoney("Даниил",800);
            }
        });

        Thread jane = new Thread(new Runnable() {
            @Override
            public void run() {
                bank.getMoney("Женя",500);
            }
        });

        dan.start();
        jane.start();

    }
}
