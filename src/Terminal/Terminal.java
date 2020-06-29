package Terminal;

import java.util.*;

public class Terminal {
    public static void main(String[] args) throws AccountIsLockedException {
        long accountId = 1_665_445_655;
        TerminalImpl accessToTerminal = new TerminalImpl(accountId);
        List<Integer> myPin = new ArrayList<Integer>();
        myPin.add(4);
        myPin.add(6);
        myPin.add(8);
        myPin.add(0);

        boolean access = accessToTerminal.inputPin(myPin);
        System.out.println(access);
        if (access) {
            MyDouble monetCount = new MyDouble();
            String message = accessToTerminal.getAccountState(monetCount);
            System.out.println(message + "  " + monetCount.retDouble);

            monetCount.retDouble = 0.;
            message = accessToTerminal.putMoney(100);
            System.out.println(message);
            message = accessToTerminal.getAccountState(monetCount);
            System.out.println(monetCount.retDouble);

            monetCount.retDouble = 0.;
            message = accessToTerminal.getMoney(100);
            System.out.println(message);
            message = accessToTerminal.getAccountState(monetCount);
            System.out.println(monetCount.retDouble);
        }
    }
}
