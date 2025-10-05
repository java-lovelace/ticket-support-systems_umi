package domain.dto;

public class CategoriaEstadisticaDto {
    public String categoria;
    public long cantidad;

    public CategoriaEstadisticaDto(String categoria, long cantidad) {
        this.categoria = categoria; this.cantidad = cantidad;
    }

    @Override public String toString() { return categoria + " => " + cantidad; }
}
