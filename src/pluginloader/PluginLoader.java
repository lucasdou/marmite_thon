package pluginloader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import interfaces.IAffichage;

public class PluginLoader {

	static String filename = "src/affichage";
	
	public static Object getLoader(Class <?> interf) {
		
		Map<String, Object> pluginMap = new HashMap<>();
		Map<String, String> configMap =  new HashMap<>();
		
		Properties prop = new Properties();
		try {
			prop.load(new FileReader(filename));
		} catch (FileNotFoundException e) {	
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		prop.forEach((x,y)-> {
			configMap.put(x.toString(), y.toString());
		});
		
		
		Object result = pluginMap.get(interf.getName());
		if(result != null) {
			return result;
		}
			
		String pluginClassName = configMap.get(interf.getName());
		if(pluginClassName == null) {
			return null;
		}
		
		Class<?> plclass = null;
		try {
			plclass = Class.forName(pluginClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		if(!interf.isAssignableFrom(plclass)) {
			return null;
		}
		
		try {
			result = plclass.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		
		pluginMap.put(interf.getName(), result);
		return result;
		
		/*
		Properties prop = new Properties();
		try {
			prop.load(new FileReader(filename));
		} catch (FileNotFoundException e) {	
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Class <?> cl = null;
		try {
			cl = Class.forName(prop.getProperty(recupclass));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Object obj = null;
		try {
			obj = cl.getDeclaredConstructor().newInstance();
		} 
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			
			e.printStackTrace();
		}

		return obj; */
	}
}
