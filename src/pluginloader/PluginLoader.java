package pluginloader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import tiers.Monitor;



public class PluginLoader {

	private static HashMap<String, Plugin> plugins;	
	private static PluginLoader instance = new PluginLoader();
	
	public static PluginLoader getInstance() {
		return instance;
	}
	
	public HashMap<String, Plugin> getPlugins () {
		return PluginLoader.plugins;
	}
	
	/**
	 * M�thode renvoyant les plugins utilisables par l'utilisateur
	 * @return
	 */
	public static HashMap<String, Plugin> getPluginsForUser () {
		HashMap<String, Plugin> plugins =  new HashMap<String, Plugin>();
		for(Plugin p : PluginLoader.getInstance().getPlugins().values()) {
			if(!p.getName().contentEquals("Afficheur")) {
				plugins.put(p.getName(), p);
			}
		}
		return plugins;
	}
	
	/**
	 * M�thode pour r�cup�rer les fichiers de config 
	 * 
	 * @param path : chemin o� sont stock�s les fichiers de config
	 * @return une liste de fichiers de config
	 */
	private File[] returnFiles(String path) {
		File repertoire = new File(path);
		File[] files = repertoire.listFiles();
		return files;
	}
	
	/**
	 * m�thode pour parser les fichiers de config des plugins
	 * 
	 * 
	 * @throws IOException 
	 */
	private void parsePlugins()  {
		plugins = new HashMap<String, Plugin>();
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
			Plugin plugin = new Plugin(property.getProperty("name"), property.getProperty("interface"), property.getProperty("classe"),
					property.getProperty("description"), property.getProperty("autorun"), dependenciesToList(property.getProperty("dependencies")));
			
			plugins.put(plugin.getName(), plugin);
		}
	}

	/**
	 * M�thode pour convertir les d�pendances des fichiers de config en une liste de string pour pouvoir les lire
	 * @param dependencies
	 * @return
	 */
	private List<String> dependenciesToList(String dependencies) {
		List<String> dependenciesList = new ArrayList<String>();
		if(!dependencies.isEmpty()) {
			String[] dependanciesArray = dependencies.split("\\;");
			for(String dependency : dependanciesArray)
			{
				dependenciesList.add(dependency);
			}
		}
		return dependenciesList;
	}

	/**
	 * M�thode pour charger un plugin
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
			if(!plugin.isLoaded()) {
				verifPluginLoaded(plugin);
				PluginLoader.getInstance().getPlugins().get(plugin.getName()).setLoaded(true);
				System.out.println("Le plugin "+ plugin.getName() + " est charg�");
				if(!plugin.getName().contentEquals("Monitor")) {
					
					Monitor.updateMonitor(plugin.getName(), "charg�");
				}
				return p;
			} else {
				System.out.println("Le plugin "+ plugin.getName() + " est d�j� charg�");
				return null;
			}			
	}
	
	/**
	 * V�rifie si il y a un plugin charg� agissant sur le m�me comportement que le plugin
	 * qui va �tre charg�. Si c'est le cas, ce plugin doit passer en �tat "non charg�" 
	 * @param p
	 */
	private static void verifPluginLoaded(Plugin p) {
		for(Plugin pl : plugins.values()) {
			if (pl.getInterf().contentEquals(p.getInterf()) && pl.isLoaded() && !pl.getName().contains(p.getName())) {
				pl.setLoaded(false);
				Monitor.updateMonitor(pl.getName(), "d�charg�");
				System.out.println("Le plugin "+ pl.getName() + " est d�charg�");
			}
		}		
	}
	
	/**
	 * M�thode qui renvoie un objet li� � un plugin
	 * @param p plugin qui a d�j� le param�tre loaded � true
	 * @return
	 */
	public static Object getPluginLoaded(Plugin p) {
		Class<?> classe = null;
		Object plugin = null;
		try {
			classe = Class.forName(p.getClasse());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			plugin = classe.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return plugin;
	}

	/**
	 * V�rifie les plugins qui sont configur�s comme autorun et les charge si c'est le cas
	 */
	private void autoRun() {
		loadMonitor();
		for(Plugin p : plugins.values()) {
			if(p.getAutoRun().contentEquals("true") && !p.getName().contentEquals("Monitor")) {
				try {
					if(!PluginLoader.getInstance().getPlugins().get(p.getName()).getDependency().isEmpty()) {
						PluginLoader.getInstance().getPlugins().get(p.getName()).getDependency().forEach(d -> {
							if (!PluginLoader.getInstance().getPlugins().get(d).isLoaded()) {
								Thread t = new Thread ((Runnable) PluginLoader.loadPlugin(PluginLoader.getInstance().getPlugins().get(d)));
								t.start();
							}
						});	
					}
					if(!PluginLoader.getInstance().getPlugins().get(p.getName()).isLoaded()) {
						Thread t = new Thread ((Runnable) PluginLoader.loadPlugin(p));
						t.start();
					}
				} catch( SecurityException | IllegalArgumentException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * M�thode qui lance le plugin Monitor
	 * @return 
	 */
	public void loadMonitor() {
		for(Plugin p : plugins.values()) {
			if(p.getName().contentEquals("Monitor")) {
				Thread t = new Thread ((Runnable) PluginLoader.loadPlugin(p));
				t.start();
			}
		}
	}

	public static void main(String[] args) {
		PluginLoader pluginLoader = PluginLoader.getInstance();
		pluginLoader.parsePlugins();
		pluginLoader.autoRun();
	}
}	
