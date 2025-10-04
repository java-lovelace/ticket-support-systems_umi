# 🎮 CONTROLLER - Orquestador de Casos de Uso
## ¿Qué es esta carpeta?
Esta carpeta orquesta los casos de uso. Es el intermediario entre la VIEW y el SERVICE.
## ¿Qué hace?

Recibe peticiones de la VIEW
Llama al SERVICE apropiado
Maneja errores y excepciones
Devuelve respuestas a la VIEW

### Ejemplo práctico:
```java
javapublic class TicketController {
    private TicketService ticketService;
    
    public void manejarCreacionTicket(String titulo, String descripcion) {
        try {
            // Llama al service
            ticketService.crearTicket(titulo, descripcion);
            System.out.println("✓ Ticket creado exitosamente");
        } catch (IllegalArgumentException e) {
            System.out.println("✗ Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("✗ Error inesperado");
        }
    }
}
```
### 🔄 Flujo:

 - ⬅️ Recibe de: VIEW (peticiones del usuario)
 - ➡️ Envía a: SERVICE (para ejecutar lógica de negocio)
 - ⬅️ Recibe de: SERVICE (resultados o excepciones)
 - ➡️ Envía a: VIEW (respuestas formateadas)
 - 🔗 Usa: SERVICE, DOMAIN (para pasar/recibir entidades)

### 📝 Responsabilidad:
 - ✅ Orquestar casos de uso
 - ✅ Manejar excepciones
 - ✅ Controlar el flujo de la aplicación
 - ❌ NO tiene lógica de negocio
 - ❌ NO accede directamente a la base de datos
 - ❌ NO interactúa directamente con el usuario
