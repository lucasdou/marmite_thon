package pluginloader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class PluginLoader {

	private HashMap<String, Plugin> plugins;	
	private static PluginLoader instance = new PluginLoader();
	
	public static PluginLoader getInstance() {
		return instance;
	}
	
	public HashMap<String, Plugin> getPlugins () {
		return this.plugins;
	}
	
	/**
	 * Méthode pour récupérer les fichiers de config 
	 * 
	 * @param path : chemin où sont stockés les fichiers de config
	 * @return une liste de fichiers de config
	 */
	private File[] returnFiles(String path) {
		File repertoire = new File(path);
		File[] files = repertoire.listFiles();
		return files;
	}
	
	/**
	 * méthode pour parser les fichiers de config des plugins
	 * 
	 * 
	 * @throws IOException 
	 */
	private void parsePlugins()  {
		this.plugins = new HashMap<String, Plugin>();
		File[] files = returnFiles("src/config");
		
		for(File f : files) {
			Properties property = new Properties();
			FileReader file = null;
			try {
				file = new FileReader(f);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				property.load(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			Plugin plugin = new Plugin(property.getProperty("name"),property.getProperty("classe"),
					property.getProperty("description"), property.getProperty("autorun"));
			plugins.put(plugin.getName(), plugin);
		}
	}
	
	/**
	 * Méthode pour charger les plugins 
	 * 
	 * @param plugin
	 * @return
	 * @throws ClassNotFoundException 
	 */
	public static Object loadPlugin(Plugin plugin) {
			Class<?> classe = null;
			Object p = null;
			try {
				classe = Class.forName(plugin.getClasse());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			try {
				p = classe.getDeclaredConstructor().newInstance();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
			PluginLoader.getInstance().getPlugins().get(plugin.getName()).setLoaded(true);
			return p;
	}
	
	/**
	 * Vérifie les plugins qui sont configurés comme autorun et les charge si c'est le cas
	 */
	private void autoRun() {
		for(Plugin p : plugins.values()) {
			if(p.getAutoRun().contentEquals("true")) {
				try {
					Thread t = new Thread ((Runnable) PluginLoader.loadPlugin(p));
					t.start();
				} catch( SecurityException | IllegalArgumentException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		PluginLoader pluginLoader = PluginLoader.getInstance();
		pluginLoader.parsePlugins();
		pluginLoader.autoRun();
	}
}	
