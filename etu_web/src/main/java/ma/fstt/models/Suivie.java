package ma.fstt.models;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Suivie  implements Serializable {

    private Long id_suivie;

    private Etudiant etudiant;

    private Module module;

    private Double note;
    private LocalDate date;

}
