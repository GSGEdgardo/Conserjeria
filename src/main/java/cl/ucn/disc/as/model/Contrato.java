package cl.ucn.disc.as.model;

import io.ebean.annotation.NotNull;
import lombok.*;

import javax.persistence.Entity;
import java.time.Instant;
import java.util.List;

/**
 * The Contrato class.
 */
@ToString
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class Contrato extends BaseModel {

    @NotNull
    private Instant fechaPago;

    // Persona asociada al contrato
    @NotNull
    private Persona persona;

    // Departamento asociado al contrato
    @NotNull
    private Departamento departamento;

    // Lista de pagos asociados al contrato
    private List<Pago> pagos;

    public Contrato(Persona persona, Departamento departamento, Instant fechaPago) {
        this.persona = persona;
        this.departamento = departamento;
        this.fechaPago = fechaPago;
    }
}