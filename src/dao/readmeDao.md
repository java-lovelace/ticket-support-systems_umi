#🗄️ DAO - Acceso a Datos (Data Access Object)
##¿Qué es esta carpeta?
Esta carpeta se encarga del acceso a la base de datos. Contiene interfaces y sus implementaciones con JDBC.
##¿Qué hace?

Ejecuta consultas SQL (SELECT, INSERT, UPDATE, DELETE)
Convierte ResultSet a objetos Domain
Convierte objetos Domain a registros de BD
Maneja PreparedStatements

###Ejemplo práctico:
```java
javapublic class TicketDaoJdbc implements TicketDao {
    private Connection conexion;
    
    @Override
    public void guardar(Ticket ticket) {
        String sql = "INSERT INTO tickets (titulo, descripcion, estado) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, ticket.getTitulo());
            ps.setString(2, ticket.getDescripcion());
            ps.setString(3, ticket.getEstado());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar", e);
        }
    }
    
    @Override
    public Ticket buscarPorId(int id) {
        String sql = "SELECT * FROM tickets WHERE id = ?";
        // ... ejecuta query y convierte a Ticket
    }
}
```
### 🔄 Flujo:

 - ⬅️ Recibe de: SERVICE (entidades a persistir/consultar)
 - ➡️ Envía a: BASE DE DATOS (queries SQL)
 - ⬅️ Recibe de: BASE DE DATOS (ResultSets)
 - ➡️ Envía a: SERVICE (entidades Domain)
 - 🔗 Usa: DOMAIN (entidades), CONFIG (conexión)

### 📝 Responsabilidad:
 - ✅ Ejecutar operaciones SQL
 - ✅ Mapear ResultSet ↔️ Objetos Domain
 - ✅ Manejar PreparedStatements
 - ❌ NO tiene lógica de negocio
 - ❌ NO valida datos
 - ❌ NO conoce la interfaz de usuario
