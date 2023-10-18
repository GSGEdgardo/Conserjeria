package cl.ucn.disc.as.model;

import io.ebean.annotation.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@ToString(callSuper = true)
@AllArgsConstructor
@Builder
@Entity
@Getter
public class Edificio extends BaseModel {

    /**
     * Nombre edificio.
     */
    @NotNull
    @Getter
    private String nombre;

    /**
     * Dirección edificio.
     */
    @NotNull
    @Getter
    private String direccion;

    /**
     * Lista departamentos.
     */
    @OneToMany(cascade = CascadeType.ALL)
    @Getter
    private List<Departamento> departamentos;

    /**
     * Añadir departamento.
     *
     * @param departamento: departamento a añadir.
     */
    public void add(Departamento departamento) {
        this.departamentos.add(departamento);
    }
}