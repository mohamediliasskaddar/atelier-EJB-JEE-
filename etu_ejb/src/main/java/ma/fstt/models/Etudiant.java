package ma.fstt.models;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

// POJO modelsss
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "etudiant")
public class Etudiant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_etudiant;
    private String nom;
    private String niveau;

}
