package PluginManager;

public class PluginToCalculate  implements Plugin {
    @Override
    public void doUsefull() {
        System.out.println("It's plugin for calculate!");
        System.out.println("1 + 11 + 22 = 34");
    }
}

