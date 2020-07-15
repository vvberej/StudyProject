package PluginManager;

public class Main {
    public static void main(String[] args) throws Exception {

        ExtensionLoader<Plugin> loader = new ExtensionLoader<Plugin>();
        Plugin somePlugin = loader.LoadClass("e:/JavaProjects/StudyProject/pluginRootDirectory/PluginCalc",
                "PluginImpl", Plugin.class);

        somePlugin.doUsefull();

        /*PluginManager pluginManager = new PluginManager(
                //"e:\\JavaProjects\\StudyProject\\out\\production\\StudyProject\\PluginManager",
                "e:\\JavaProjects\\StudyProject\\pluginRootDirectory\\PluginCalc",
                Main.class.getClassLoader());*/


       // Plugin plugin1 = (Plugin)pluginManager.loadClass("PluginManager.PluginImpl").newInstance();
      //  Class cls = Main.class.getClassLoader().loadClass("PluginManager.PluginImpl");
        // Plugin plugin1 = (Plugin)pluginManager.loadClass("PluginManager.PluginToCalculate").newInstance();
        //if (plugin1 != null)
        //    plugin1.doUsefull();



        //ClassLoader pluginManager = new PluginManager("e:/JavaProjects/StudyProject/pluginRootDirectory");

      /*  try {
            CustomLoader customLoader = new CustomLoader("e:/JavaProjects/StudyProject/pluginRootDirectory");
            customLoader.reloadPlugins();
            //((PluginManager)pluginManager).loadPlugins();

            //ru.sbt.pluginRootDirectory.PluginOne.PluginOne pluginOne =
            //        (ru.sbt.pluginRootDirectory.PluginOne.PluginOne) pluginManager.loadClass("ru.sbt.pluginRootDirectory.PluginOne.PluginOne").newInstance();
            //pluginOne.doUsefull();
        } catch (Exception e ) {
            e.printStackTrace();
        }*/
    }
}
