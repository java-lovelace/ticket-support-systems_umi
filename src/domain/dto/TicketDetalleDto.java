package domain.dto;

import java.time.OffsetDateTime;

public class TicketDetalleDto {
    public Long id;
    public String titulo;
    public String reporterNombre;
    public String categoriaNombre;
    public String estadoNombre;
    public OffsetDateTime fechaCreacion;

    public TicketDetalleDto(Long id, String titulo, String reporterNombre,
                            String categoriaNombre, String estadoNombre,
                            OffsetDateTime fechaCreacion) {
        this.id = id; this.titulo = titulo; this.reporterNombre = reporterNombre;
        this.categoriaNombre = categoriaNombre; this.estadoNombre = estadoNombre;
        this.fechaCreacion = fechaCreacion;
    }

    @Override public String toString() {
        return "TicketDetalleDto{id=%d, titulo='%s', reporter='%s', categoria='%s', estado='%s', fecha=%s}"
                .formatted(id, titulo, reporterNombre, categoriaNombre, estadoNombre, fechaCreacion);
    }
}
