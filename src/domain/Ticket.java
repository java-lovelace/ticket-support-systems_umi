package domain;

import java.time.OffsetDateTime;

public class Ticket {
    private Long id;
    private String titulo;
    private String descripcion;
    private Long reporterId;   // usuario que reporta
    private Long assigneeId;   // usuario asignado (nullable)
    private Long categoriaId;
    private Long estadoId;
    private OffsetDateTime fechaCreacion;

    public Ticket() {}

    public Ticket(Long id, String titulo, String descripcion,
                  Long reporterId, Long assigneeId, Long categoriaId, Long estadoId,
                  OffsetDateTime fechaCreacion) {
        this.id = id; this.titulo = titulo; this.descripcion = descripcion;
        this.reporterId = reporterId; this.assigneeId = assigneeId;
        this.categoriaId = categoriaId; this.estadoId = estadoId;
        this.fechaCreacion = fechaCreacion;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Long getReporterId() { return reporterId; }
    public void setReporterId(Long reporterId) { this.reporterId = reporterId; }
    public Long getAssigneeId() { return assigneeId; }
    public void setAssigneeId(Long assigneeId) { this.assigneeId = assigneeId; }
    public Long getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Long categoriaId) { this.categoriaId = categoriaId; }
    public Long getEstadoId() { return estadoId; }
    public void setEstadoId(Long estadoId) { this.estadoId = estadoId; }
    public OffsetDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(OffsetDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}
