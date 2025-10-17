package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    // Devuelve la fecha actual en formato legible
    public static String ahora() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    // Convierte una fecha a texto
    public static String formatear(LocalDateTime fecha) {
        if (fecha == null) return "Sin fecha";
        return fecha.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
