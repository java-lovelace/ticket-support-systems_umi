# 🛠️ UTIL - Utilidades y Helpers
## ¿Qué es esta carpeta?
Esta carpeta contiene funciones auxiliares reutilizables que pueden ser usadas por cualquier capa.
## ¿Qué hace?

Formatea fechas
Valida formatos (email, teléfono)
Convierte datos
Funciones matemáticas comunes
Helpers generales

### Ejemplo práctico:
```java
javapublic class DateUtil {
    public static String formatearFecha(Date fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(fecha);
    }
    
    public static Date parsearFecha(String fecha) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.parse(fecha);
    }
}

public class ValidadorUtil {
    public static boolean esEmailValido(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(regex);
    }
}

```
###🔄 Flujo:

 - ⬅️ Recibe de: Cualquier capa que necesite ayuda
 - ➡️ Envía a: Cualquier capa que la llamó
 - 🔗 Usado por: Todas las capas cuando necesitan funciones comunes

###📝 Responsabilidad:
 - ✅ Proporcionar funciones auxiliares reutilizables
 - ✅ Formatear datos
 - ✅ Validar formatos simples
 - ✅ Conversiones comunes
 - ❌ NO tiene lógica de negocio específica
 - ❌ NO accede a base de datos
 - ❌ NO maneja estado de la aplicación
