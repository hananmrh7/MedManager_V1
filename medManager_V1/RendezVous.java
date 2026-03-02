package medManager_V1;

import java.time.LocalDateTime;

public class RendezVous {

    private Patient patient;
    private Medecin medecin;
    private LocalDateTime dateHeure;

    public RendezVous(Patient p, Medecin m, LocalDateTime d) {
        this.patient = p;
        this.medecin = m;
        this.dateHeure = d;
    }

    @Override
    public String toString() {
        return dateHeure + " | "
            + patient.getIdentiteComplete()
            + " avec " + medecin;
    }
}