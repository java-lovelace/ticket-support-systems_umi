package domain;

import java.time.OffsetDateTime;

public class Comentario {
    private Long id;
    private Long ticketId;
    private String texto;
    private OffsetDateTime fecha;

    public Comentario() {}

    public Comentario(Long id, Long ticketId, String texto, OffsetDateTime fecha) {
        this.id = id; this.ticketId = ticketId; this.texto = texto; this.fecha = fecha;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getTicketId() { return ticketId; }
    public void setTicketId(Long ticketId) { this.ticketId = ticketId; }
    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }
    public OffsetDateTime getFecha() { return fecha; }
    public void setFecha(OffsetDateTime fecha) { this.fecha = fecha; }
}
