# 🚀 APP - Punto de Entrada

### ¿Qué es esta carpeta?
Es la carpeta principal de la aplicación. Aquí está el `Main.java` que inicia todo el sistema.

### ¿Qué hace?
- Inicializa la conexión a la base de datos  
- Crea todas las capas del sistema (DAO, Service, Controller, View)  
- Conecta todas las piezas  
- Inicia la aplicación  

### Ejemplo práctico:
```java
public class Main {
    public static void main(String[] args) {
        // 1. Configurar BD
        Connection conexion = DbConfig.getConnection();
        
        // 2. Crear capas
        TicketDao dao = new TicketDaoJdbc(conexion);
        TicketService service = new TicketService(dao);
        TicketController controller = new TicketController(service);
        TicketView view = new TicketView(controller);
        
        // 3. Iniciar
        view.ejecutar();
    }
}
```
### 🔄 Flujo:
 - ⬅️ Recibe de: Nadie (es el inicio)
 - ➡️ Envía a: VIEW (para iniciar la interfaz)
 - 🔗 Usa: CONFIG (para configuración), todas las demás capas

### 📝 Responsabilidad:
 - ✅ Arrancar la aplicación
 - ✅ Conectar todas las capas
 - ❌ No tiene lógica de negocio
 - ❌ No interactúa con el usuario
