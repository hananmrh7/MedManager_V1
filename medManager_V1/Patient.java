package medManager_V1;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;

public class Patient {

    private String id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String telephone;
    private String groupeSanguin;
    private ServiceHospitalier serviceActuel;

    public Patient(String id, String nom, String prenom,
                   LocalDate dateNaissance) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
    }

    // GETTERS
    public String getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public LocalDate getDateNaissance() { return dateNaissance; }
    public String getTelephone() { return telephone; }
    public String getGroupeSanguin() { return groupeSanguin; }
    public ServiceHospitalier getServiceActuel() { return serviceActuel; }

    // SETTERS
    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setTelephone(String telephone) {
        if (telephone == null || !telephone.matches("[+\\d\\s]{10,}")) {
            throw new IllegalArgumentException(
                "Numéro invalide (min 10 caractères, chiffres, espaces, +)"
            );
        }
        this.telephone = telephone;
    }

    public void setGroupeSanguin(String groupe) {
        List<String> valides = List.of(
            "A+", "A-", "B+", "B-",
            "AB+", "AB-", "O+", "O-"
        );
        if (!valides.contains(groupe)) {
            throw new IllegalArgumentException("Groupe sanguin invalide");
        }
        this.groupeSanguin = groupe;
    }

    public void setServiceActuel(ServiceHospitalier service) {
        this.serviceActuel = service;
    }

    public int getAge() {
        return Period.between(dateNaissance, LocalDate.now()).getYears();
    }

    public String getIdentiteComplete() {
        return prenom + " " + nom + " (ID: " + id + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient p = (Patient) o;
        return Objects.equals(id, p.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return getIdentiteComplete() + " - " + getAge() + " ans";
    }
}