package cl.ucn.disc.as.model;

import io.ebean.annotation.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.Instant;
import java.util.List;

@ToString(callSuper = true)
@AllArgsConstructor
@Builder
@Entity
@Getter
public class Contrato extends BaseModel {

    @NotNull
    private Instant fechaPago;

    // Relación con Persona
    @ManyToOne
    @NotNull
    private Persona persona;

    // Relación con Departamento
    @ManyToOne
    @NotNull
    private Departamento departamento;

    // Relación con Pago
    @OneToMany(cascade = CascadeType.ALL)
    private List<Pago> pagos;

}