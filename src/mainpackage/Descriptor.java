package mainpackage;

public class Descriptor {
	String nomCourt;
	String Description;
	String classConcret;
	
	public String getNomCourt() {
		return nomCourt;
	}
	public void setNomCourt(String nomCourt) {
		this.nomCourt = nomCourt;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getClassConcret() {
		return classConcret;
	}
	public void setClassConcret(String classConcret) {
		this.classConcret = classConcret;
	}
	@Override
	public String toString() {
		return "Descriptor [nomCourt=" + nomCourt + ", Description=" + Description + ", classConcret=" + classConcret
				+ "]";
	}
}
