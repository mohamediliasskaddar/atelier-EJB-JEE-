package ma.fstt.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "suivie")
public class Suivie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_suivie;

    @ManyToOne
    @JoinColumn(name = "id_etudiant")
    private Etudiant etudiant;

    @ManyToOne
    @JoinColumn(name = "id_module")
    private Module module;

    private Double note;
    private LocalDate date;

    // Getters & Setters
}
