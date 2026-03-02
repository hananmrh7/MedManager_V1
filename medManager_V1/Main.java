package medManager_V1;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Main {

    static List<Patient> patients = new ArrayList<>();
    static List<Medecin> medecins = new ArrayList<>();
    static List<ServiceHospitalier> services = new ArrayList<>();
    static List<RendezVous> rendezVous = new ArrayList<>();

    static int prochainIdPatient = 1;
    static int prochainIdMedecin = 1;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        services.add(new ServiceHospitalier("CARD", "Cardiologie", 3));
        services.add(new ServiceHospitalier("URG", "Urgences", 5));

        int choix;

        do {
            afficherMenu();
            choix = Integer.parseInt(sc.nextLine());

            switch (choix) {
                case 1 -> ajouterPatient(sc);
                case 2 -> ajouterMedecin(sc);
                case 3 -> afficherPatients();
                case 4 -> supprimerPatient(sc);
                case 5 -> modifierPatient(sc);
                case 6 -> affecterPatient(sc);
                case 7 -> affecterMedecin(sc);
                case 8 -> planifierRdv(sc);
                case 9 -> services.forEach(ServiceHospitalier::afficherTableauDeBord);
                case 0 -> System.out.println("Au revoir 👋");
            }

        } while (choix != 0);
    }

    static void afficherMenu() {
        System.out.println("""
1. Ajouter patient
2. Ajouter médecin
3. Afficher patients
4. Supprimer patient
5. Modifier patient
6. Affecter patient → service
7. Affecter médecin → service
8. Planifier rendez-vous
9. Tableau de bord services
0. Quitter
""");
    }

    static void ajouterPatient(Scanner sc) {
        String id = "P" + prochainIdPatient++;
        System.out.print("Nom: ");
        String nom = sc.nextLine();
        System.out.print("Prénom: ");
        String prenom = sc.nextLine();
        System.out.print("Date naissance (AAAA-MM-JJ): ");
        LocalDate dn = LocalDate.parse(sc.nextLine());

        Patient p = new Patient(id, nom, prenom, dn);

        patients.add(p);
        System.out.println("✅ Patient ajouté");
    }

    static void ajouterMedecin(Scanner sc) {
        String id = "M" + prochainIdMedecin++;
        System.out.print("Nom: ");
        String nom = sc.nextLine();
        System.out.print("Prénom: ");
        String prenom = sc.nextLine();
        System.out.print("Date naissance: ");
        LocalDate dn = LocalDate.parse(sc.nextLine());
        System.out.print("Spécialité: ");
        String spe = sc.nextLine();
        System.out.print("Matricule: ");
        String mat = sc.nextLine();

        medecins.add(new Medecin(id, nom, prenom, dn, spe, mat));
    }

    static void afficherPatients() {
        patients.forEach(System.out::println);
    }

    static void supprimerPatient(Scanner sc) {
        System.out.print("ID: ");
        String id = sc.nextLine();

        Patient p = patients.stream()
                .filter(pa -> pa.getId().equals(id))
                .findFirst().orElse(null);

        if (p == null) return;

        if (p.getServiceActuel() != null) {
            p.getServiceActuel().retirerPatient(p);
        }

        patients.remove(p);
        System.out.println("Supprimé");
    }

    static void modifierPatient(Scanner sc) {
        System.out.print("ID: ");
        String id = sc.nextLine();

        Patient p = patients.stream()
                .filter(pa -> pa.getId().equals(id))
                .findFirst().orElse(null);

        if (p == null) return;

        System.out.print("Nouveau nom ("+p.getNom()+"): ");
        String s = sc.nextLine();
        if (!s.isEmpty()) p.setNom(s);
    }

    static void affecterPatient(Scanner sc) {
        System.out.print("ID patient: ");
        String id = sc.nextLine();

        Patient p = patients.stream()
                .filter(pa -> pa.getId().equals(id))
                .findFirst().orElse(null);

        if (p == null) return;

        services.forEach(System.out::println);
        int choix = Integer.parseInt(sc.nextLine());

        services.get(choix-1).admettre(p);
    }

    static void affecterMedecin(Scanner sc) {
        System.out.print("ID médecin: ");
        String id = sc.nextLine();

        Medecin m = medecins.stream()
                .filter(me -> me.getId().equals(id))
                .findFirst().orElse(null);

        if (m == null) return;

        services.forEach(System.out::println);
        int choix = Integer.parseInt(sc.nextLine());

        services.get(choix-1).ajouterMedecin(m);
    }

    static void planifierRdv(Scanner sc) {
        System.out.print("ID patient: ");
        String idP = sc.nextLine();
        System.out.print("ID médecin: ");
        String idM = sc.nextLine();
        System.out.print("Date (AAAA-MM-JJTHH:MM): ");
        LocalDateTime dt = LocalDateTime.parse(sc.nextLine());

        Patient p = patients.stream()
                .filter(pa -> pa.getId().equals(idP))
                .findFirst().orElse(null);

        Medecin m = medecins.stream()
                .filter(me -> me.getId().equals(idM))
                .findFirst().orElse(null);

        if (p != null && m != null) {
            rendezVous.add(new RendezVous(p, m, dt));
            System.out.println("Rendez-vous ajouté");
        }
    }
}