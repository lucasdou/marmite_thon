package mainpackage;

public class Person {

	public Person(String nom, Integer age) {
		super();
		this.nom = nom;
		this.age = age;
	}
	String nom;
	Integer age;
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Person [nom=" + nom + ", age=" + age + "]";
	}	
	
}
