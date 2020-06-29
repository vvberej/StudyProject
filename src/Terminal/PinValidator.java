package Terminal;

import java.util.*;
import java.util.List;

public class PinValidator {
    private final ArrayList<Integer> accountPin;

    public PinValidator(long accountID){
        Integer[] val = {4, 6, 8, 0}; //но в реальности взят, например из бд по accountID
        accountPin = new ArrayList<>(4);
        Collections.addAll(accountPin, val);
    }

    public boolean checkPin(ArrayList<Integer> pin) {
        boolean res = accountPin.equals(pin);
        if (!res)
            throw new RuntimeException("Pin code comparison showed a mismatch");
        return res;
    }
}
