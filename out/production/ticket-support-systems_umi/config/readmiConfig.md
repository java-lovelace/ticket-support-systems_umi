# ⚙️ CONFIG - Configuraciones
## ¿Qué es esta carpeta?
Esta carpeta contiene las configuraciones del sistema: base de datos, URLs, credenciales, drivers.
## ¿Qué hace?

Gestiona conexiones a base de datos
Carga configuraciones (URL, usuario, contraseña)
Carga drivers JDBC
Proporciona objetos de configuración

### Ejemplo práctico:
```java
javapublic class DbConfig {
    private static final String URL = "jdbc:mysql://localhost:3306/tickets_db";
    private static final String USER = "root";
    private static final String PASSWORD = "password";
    
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar", e);
        }
    }
}
```
### 🔄 Flujo:

 - ⬅️ Recibe de: APP (solicitud de configuración)
 - ➡️ Envía a: APP, DAO (objetos de configuración)
 - 🔗 Usado por: APP (principalmente), DAO

### 📝 Responsabilidad:
 - ✅ Gestionar configuraciones
 - ✅ Proporcionar conexiones a BD
 - ✅ Cargar propiedades del sistema
 - ❌ NO tiene lógica de negocio
 - ❌ NO manipula datos
 - ❌ NO interactúa con el usuario
