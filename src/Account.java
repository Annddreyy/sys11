import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private final Lock LOCK_OBJECT = new ReentrantLock();
    private final Condition balanceIncreased = LOCK_OBJECT.newCondition();
    private int accountBalance;

    Account(int balance) {
        this.accountBalance = balance;
    }

    int getBalance() {
        LOCK_OBJECT.lock();
        try {
            return accountBalance;
        } finally {
            LOCK_OBJECT.unlock();
        }
    }

    void deposit(int amount) {
        LOCK_OBJECT.lock();
        try {
            accountBalance += amount;
            balanceIncreased.signalAll();
        } finally {
            LOCK_OBJECT.unlock();
        }
    }

    public void withdraw(int amount) {
        LOCK_OBJECT.lock();
        try {
            if (accountBalance < amount) {
                System.out.println("Недостатоно средств на счете для снятия.");
            }
            accountBalance -= amount;
        } finally {
            LOCK_OBJECT.unlock();
        }
    }

    void waitAndWithdraw(int amount) throws InterruptedException {
        LOCK_OBJECT.lock();
        try {
            while (accountBalance < amount) {
                System.out.println("Не хватает средст для снятия. На данный момент есть: " + accountBalance);
                balanceIncreased.await();
            }
            accountBalance -= amount;
        } finally {
            LOCK_OBJECT.unlock();
        }
    }
}
