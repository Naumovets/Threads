package bank;

public class Bank {
    private long bank = 1000;
    private Object monitor = new Object();

    public void getMoney(String name, long money){
        synchronized (monitor){
            try {
                System.out.println(name + " подошел к банкомату.");
                Thread.sleep(2000);

                if(bank >= money){
                    bank-=money;
                    System.out.println(name + " вывел " + money + " рублей. В банкомате осталось " + bank + " рублей");
                }else{
                    System.out.println("В банкомате недостаточно денег для " + name);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
