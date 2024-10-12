class DepositThread extends Thread {

    private final Account account;

    DepositThread(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; ++i) {
            account.deposit((int) (Math.random() * 10000000));
            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}