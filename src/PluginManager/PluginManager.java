package PluginManager;

import java.util.*;
import java.io.*;

public class PluginManager extends ClassLoader {
    //private final String pluginRootDirectory;
    private final String pluginClassName;
    //private HashMap<String, Class<?>> classes = new HashMap<>();//<полный путь к плагину, класс, который загужен>

    public PluginManager(String pluginRootDirectory, ClassLoader parent) {
        super(parent);
        //this.pluginRootDirectory = pluginRootDirectory;
        pluginClassName = "PluginImpl";
        //classes = new HashMap<>();
    }

    /*public boolean findPlugins() {
        //ArrayList<String> retList = new ArrayList<>();
        File root = new File(pluginRootDirectory);
        File[] list = root.listFiles();

        if (list == null) return false;

        for (File f : list) {
            if (!f.isDirectory()) {
                String ext = getFileExtension(f);
                if (ext == "class")
                    classes.put(f.getAbsolutePath(), null);
            }
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

    public void loadPlugins() throws Exception {
        classes.clear();
        boolean res = findPlugins();

        for (Map.Entry pluginPath : classes.entrySet()) {
            Plugin plugin1 = (Plugin)this.loadClass(pluginClassName).newInstance();
            if (plugin1 != null)
                plugin1.doUsefull();
        }

    }*/
}

/*public class PluginManager extends ClassLoader {
    private final String pluginRootDirectory;
    private HashMap<String, Class<?>> classes = new HashMap<>();

    PluginManager(String pluginRootDirectory, ClassLoader parent) {
        super(parent);
        this.pluginRootDirectory = pluginRootDirectory;
    }


    public Class<?> loadClass(String pluginName)  throws ClassNotFoundException{
        Class<?> result = classes.get(pluginName);
        if(result != null)
            return result;
     //   result = findClass(pluginName);
      //  if(result != null)
            result = super.loadClass(pluginName);
        return result;

    }

    protected Class<?> findClass(String pluginName) throws ClassNotFoundException {
        String pluginPath = pluginRootDirectory + "/" + pluginName.replace('.', '/') + ".class";
        File plugin = new File(pluginPath);
        byte[] classData;

        if (!plugin.exists())
            return null;

        try {
            classData = loadClassData(pluginName.replace('.', '/') + ".class");
            classes.put(pluginName, defineClass(pluginName, classData, 0, classData.length));
            return classes.get(pluginName);
        } catch (IOException e) {
            return null;
        }

    }
    private byte[] loadClassData(String name) throws IOException {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(name);
        int size = stream.available();
        byte buff[] = new byte[size];
        DataInputStream in = new DataInputStream(stream);
        in.readFully(buff);
        in.close();
        return buff;
    }


}*/
