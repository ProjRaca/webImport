package raca.api.util;

public enum StatusImportacao {
    A("A","Ativo"),
    P("P","Processado"),
    E("E","Exportado");

    private final String tipo;
    private final String descricao;

    public String getTipo() {
        return tipo;
    }

    StatusImportacao(String tipo, String descricao) {
        this.descricao = descricao;
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }



}
