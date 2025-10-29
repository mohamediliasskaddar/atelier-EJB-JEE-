package ma.fstt.models;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Etudiant implements Serializable {

    private Long id_etudiant;
    private String nom;
    private String niveau;

}
