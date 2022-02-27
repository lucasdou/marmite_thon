package pluginloader;

public class Plugin {

	String name;
	String classe;
	String description;
	String autoRun;
	boolean loaded;
	
	public Plugin(String name, String classe, String description, String autoRun) {
		super();
		this.name = name;
		this.classe = classe;
		this.description = description;
		this.autoRun = autoRun;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public boolean isLoaded() {
		return loaded;
	}

	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}

	@Override
	public String toString() {
		return "Plugin [name=" + name + ", classe=" + classe + ", autoRun=" + autoRun + "]";
	}
	
	
	
}
