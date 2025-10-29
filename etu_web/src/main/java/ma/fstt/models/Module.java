package ma.fstt.models;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Module  implements Serializable {

    private Long id_module;

    private String nomModule;

}
