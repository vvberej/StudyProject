package PluginManager;
import PluginManager.Plugin;

public class PluginToConsole implements Plugin {
    @Override
    public void doUsefull() {
        System.out.println("It's plugin for print to console! Bla-bla-bla!");
    }
}