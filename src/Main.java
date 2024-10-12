public class Main {
    public static void main(String[] args) throws Exception {
        Account account = new Account(1000);

        new DepositThread(account).start();

        System.out.println("Баланс до снятия: " + account.getBalance());
        account.waitAndWithdraw(200000);
        System.out.println("Баланс после снятия: " + account.getBalance());

        System.out.println("Баланс до снятия: " + account.getBalance());
        account.waitAndWithdraw(100000);
        System.out.println("Баланс после снятия: " + account.getBalance());
    }
}
