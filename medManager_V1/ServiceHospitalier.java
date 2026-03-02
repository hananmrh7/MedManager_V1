package medManager_V1;

import java.util.ArrayList;
import java.util.List;

public class ServiceHospitalier {

    private String code;
    private String nom;
    private int capaciteLits;
    private List<Medecin> medecins = new ArrayList<>();
    private List<Patient> patients = new ArrayList<>();

    public ServiceHospitalier(String code, String nom, int capaciteLits) {
        this.code = code;
        this.nom = nom;
        this.capaciteLits = capaciteLits;
    }
    

    public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public boolean admettre(Patient patient) {
        if (patients.size() >= capaciteLits) {
            System.out.println("⚠ Service complet");
            return false;
        }
        if (patient.getServiceActuel() != null) {
            System.out.println("⚠ Patient déjà dans un service");
            return false;
        }
        patients.add(patient);
        patient.setServiceActuel(this);
        return true;
    }

    public void retirerPatient(Patient patient) {
        patients.remove(patient);
        patient.setServiceActuel(null);
    }

    public void ajouterMedecin(Medecin medecin) {
        if (!medecins.contains(medecin)) {
            medecins.add(medecin);
        }
    }

    public String getNom() { return nom; }
    public List<Patient> getPatients() { return patients; }

    public void afficherTableauDeBord() {
        System.out.println("\n🏥 " + nom);
        System.out.println("Lits disponibles : "
            + (capaciteLits - patients.size()) + "/" + capaciteLits);

        System.out.println("Médecins :");
        medecins.forEach(m -> System.out.println("  - " + m));

        System.out.println("Patients :");
        patients.forEach(p -> System.out.println("  - " + p));
    }

    @Override
    public String toString() {
        return nom + " (" + (capaciteLits - patients.size())
            + "/" + capaciteLits + " lits)";
    }
}