package cl.ucn.disc.as.model;

import io.ebean.annotation.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import java.time.Instant;

@Getter
@ToString(callSuper = true)
@Builder
@Entity
public class Pago extends BaseModel {

    @NotNull
    private Instant fechaPago;

    @NotNull
    private Integer monto;

    public Pago(Instant fechaPago, Integer monto) {
        this.fechaPago = fechaPago;
        this.monto = monto;
    }
}