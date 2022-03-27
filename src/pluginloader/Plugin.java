package pluginloader;

import java.util.List;

public class Plugin {

	String name;
	String interf;
	String classe;
	String description;
	String autoRun;
	List<String> dependencies;
	boolean loaded;
	
	public Plugin(String name, String interf, String classe, String description, String autoRun, List<String> dependencies) {
		this.name = name;
		this.interf = interf;
		this.classe = classe;
		this.description = description;
		this.autoRun = autoRun;
		this.dependencies = dependencies;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInterf() {
		return interf;
	}

	public void setInterf(String interf) {
		this.interf = interf;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAutoRun() {
		return autoRun;
	}

	public void setAutoRun(String autoRun) {
		this.autoRun = autoRun;
	}
	
	public List<String> getDependency() {
		return dependencies;
	}

	public void setDependency(List<String> dependencies) {
		this.dependencies = dependencies;
	}

	public boolean isLoaded() {
		return loaded;
	}

	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}	
}
