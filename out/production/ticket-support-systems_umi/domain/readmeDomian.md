# 🏛️ DOMAIN - Entidades del Negocio
## ¿Qué es esta carpeta?
Esta carpeta contiene las entidades que representan los objetos del mundo real en tu sistema.
## ¿Qué hace?

Define la estructura de datos
Representa objetos del negocio (Usuario, Ticket, Categoría, etc.)
Contiene getters y setters
Puede tener métodos simples de la entidad

###Ejemplo práctico:
```java
javapublic class Ticket {
    private int id;
    private String titulo;
    private String descripcion;
    private String estado;
    
    public Ticket(int id, String titulo, String descripcion, String estado) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
    }
    
    // Getters y setters
    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    // ...
    
    // Método simple de la entidad
    public boolean estaAbierto() {
        return "ABIERTO".equals(estado);
    }
}
```
###🔄 Flujo:

 - ⬅️ Recibe de: Ninguna capa (es usado por todas)
 - ➡️ Envía a: Ninguna capa (es usado por todas)
 - 🔗 Usado por: SERVICE, DAO, CONTROLLER, VIEW

### 📝 Responsabilidad:
 - ✅ Representar objetos del negocio
 - ✅ Encapsular datos
 - ✅ Métodos simples de la entidad
 - ❌ NO tiene lógica de negocio compleja
 - ❌ NO accede a base de datos
 - ❌ NO maneja validaciones complejas
