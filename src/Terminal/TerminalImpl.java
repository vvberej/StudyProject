package Terminal;

import java.util.ArrayList;
import java.util.List;

public class TerminalImpl {

    private TerminalServer server;
    private PinValidator pinValidator;
    private boolean access;
    private short attemptNumber;
    private boolean accountLocked;
    long startTimeLocked;
    long accountId;

    public TerminalImpl(long accountID){
        accountId = accountID;
        attemptNumber = 0;
        accountLocked = false;
        startTimeLocked = 0;
        pinValidator = new PinValidator(accountID);
        server = new TerminalServer(accountId);
        access = false;
    }

    public boolean inputPin(List<Integer> pin) throws AccountIsLockedException {
        boolean result = false;
        if (accountLocked) {
            Double timeInSecond = (Double)(startTimeLocked / 1000000000.0);
            if (timeInSecond < 5.) {
                String message = "Осталось времени до разблокировки аккаунта " + String.valueOf(timeInSecond.intValue()) + " сек";
                System.out.println(message);
                throw new AccountIsLockedException(message);
            }
            else {
                accountLocked = false;
                startTimeLocked = 0;
                attemptNumber = 0;
            }
        }
        attemptNumber++;
        if (attemptNumber == 3) {
            String message = "Использовано 3 попытки ввода неверного пароля. Блок на 5 сек.";
            accountLocked = true;
            startTimeLocked = System.nanoTime();
            System.out.println(message);
            throw new SecurityException(message);
        }
        else {
            boolean res = pinValidator.checkPin((ArrayList)pin);
            result = res;
            access = res;
        }
        return result;
    }

    public String getAccountState(MyDouble countMoney ) {
        if (access) {
            return server.getAccountState(countMoney);
        }
        return "Введите pin.";
    }

    public String putMoney(int money) {
        if (access) {
            return server.putMoney(money);
        }
        return "Введите pin.";
    }

    public String getMoney(int money) {
        if (access) {
            return server.getMoney(money);
        }
        return "Введите pin.";
    }


}
