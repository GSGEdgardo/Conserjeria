package cl.ucn.disc.as;

import io.ebean.DB;
import io.ebean.Database;
import lombok.extern.slf4j.Slf4j;
import cl.ucn.disc.as.model.Persona;

/**
 * THe main.
 * @Author Edgardo Ortiz Gonzalez
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        log.debug("Starting main");

        //Get database
        Database db = DB.getDefault();
        Persona persona = Persona.builder()

                .rut(204168539)
                .nombre("Edgardo")
                .apellidos("Ortiz")
                .email("edgardo@gmail.com")
                .telefono("48873074")
                .build();
        //a
        log.debug("The persona after: ${}", persona);

        // Save persona into database
        db.save(persona);
        log.debug("The persona before: ${}", persona);
        log.debug("Done.");
    }

}