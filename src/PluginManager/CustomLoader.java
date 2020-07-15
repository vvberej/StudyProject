package PluginManager;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CustomLoader {
    private final String pluginRootDirectory;
    private HashMap<String, Class<?>> classes = new HashMap<>();//<полный путь к плагину, класс, который загужен>
    private final String pluginClassName = "PluginImpl";

    public CustomLoader(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
        classes = new HashMap<>();
    }

    public boolean findPlugins(String path) {
        //ArrayList<String> retList = new ArrayList<>();
        File root = new File(path);
        File[] list = root.listFiles();

        if (list == null) return false;

        for (File f : list) {
            classes.put(f.getPath(), null);
            //if (!f.isDirectory()) {
            //    String ext = getFileExtension(f);
            //    if (ext.equals("class"))
            //        classes.put(f.getAbsolutePath(), null);
            //}
            //else {
            //    findPlugins(f.getAbsolutePath());
            //}
        }
        if (classes.size() >0)
            return true;
        return false;
    }
    //метод определения расширения файла
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        // если в имени файла есть точка и она не является первым символом в названии файла
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            // то вырезаем все знаки после последней точки в названии файла, то есть ХХХХХ.txt -> txt
            return fileName.substring(fileName.lastIndexOf(".")+1);
            // в противном случае возвращаем заглушку, то есть расширение не найдено
        else return "";
    }

    public void reloadPlugins() throws Exception {
        classes.clear();
        boolean res = findPlugins(pluginRootDirectory);

        for (Map.Entry entry : classes.entrySet()) {
            //Plugin plugin1 = (Plugin)this.loadClass(pluginClassName).newInstance();
            //if (plugin1 != null)
            //    plugin1.doUsefull();
            ClassLoader pluginManager = new PluginManager(entry.getKey().toString(), this.getClass().getClassLoader());


            Plugin plugin1 = (Plugin)pluginManager.loadClass(pluginClassName + ".class").newInstance();
            if (plugin1 != null) {
                entry.setValue(plugin1);
                plugin1.doUsefull();
            }
        }

    }
}
