package medManager_V1;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Medecin {

    private String id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String specialite;
    private String matricule;

    public Medecin(String id, String nom, String prenom,
                   LocalDate dateNaissance,
                   String specialite, String matricule) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.specialite = specialite;
        this.matricule = matricule;
    }
    

    public String getMatricule() {
		return matricule;
	}


	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}


	public String getId() { return id; }

    public int getAge() {
        return Period.between(dateNaissance, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "Dr. " + prenom + " " + nom
            + " (" + specialite + ") - ID:" + id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Medecin)) return false;
        Medecin m = (Medecin) o;
        return Objects.equals(id, m.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}