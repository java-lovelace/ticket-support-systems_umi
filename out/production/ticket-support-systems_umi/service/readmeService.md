# 💼 SERVICE - Lógica de Negocio
## ¿Qué es esta carpeta?
Esta carpeta contiene la lógica de negocio de la aplicación. Aquí están las reglas del negocio.
##¿Qué hace?

Valida datos según reglas del negocio
Ejecuta operaciones complejas
Coordina múltiples DAOs si es necesario
Aplica reglas de negocio
Transforma datos

### Ejemplo práctico:
```java
javapublic class TicketService {
    private TicketDao ticketDao;
    
    public void crearTicket(String titulo, String descripcion) {
        // VALIDACIONES (reglas de negocio)
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío");
        }
        
        if (descripcion.length() < 10) {
            throw new IllegalArgumentException("Descripción muy corta");
        }
        
        // CREAR entidad
        Ticket ticket = new Ticket(0, titulo, descripcion, "ABIERTO");
        
        // PERSISTIR
        ticketDao.guardar(ticket);
    }
}
```
### 🔄 Flujo:

 - ⬅️ Recibe de: CONTROLLER (peticiones con datos)
 - ➡️ Envía a: DAO (para persistir/consultar datos)
 - ⬅️ Recibe de: DAO (entidades de la BD)
 - ➡️ Envía a: CONTROLLER (resultados procesados)
 - 🔗 Usa: DAO, DOMAIN (entidades)

###📝 Responsabilidad:
 - ✅ Validar según reglas de negocio
 - ✅ Ejecutar lógica compleja
 - ✅ Coordinar múltiples operaciones
 - ✅ Transformar datos
 - ❌ NO maneja la interfaz de usuario
 - ❌ NO conoce detalles de SQL
 - ❌ NO maneja conexiones a BD
