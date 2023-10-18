package cl.ucn.disc.as.model;

import io.ebean.annotation.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * The Edificio class.
 *
 * @author Edgardo Ortiz Gonzalez
 */
@ToString(callSuper = true)
@Entity
public class Departamento extends BaseModel {

    @Builder

    public Departamento(@NonNull Integer numero, @NonNull Integer piso) {
        this.numero = numero;
        this.piso = piso;
        this.edificio = null;
    }

    @NotNull
    @Getter
    private Integer numero;

    @NotNull
    @Getter
    private Integer piso;

    @ManyToOne
    @Getter
    @Setter
    private Edificio edificio;
}
