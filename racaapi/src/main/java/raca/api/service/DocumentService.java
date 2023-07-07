package raca.api.service;

import raca.api.domain.entity.Documento;
import raca.api.domain.entity.Movimentacao;
import raca.api.rest.dto.DocumentoDTO;
import raca.api.rest.filter.FilterDocumentDTO;

import java.util.Iterator;
import java.util.List;

public interface DocumentService {
    List<?> toList(Iterator<?> iterator);

    List<Movimentacao> getAllMovimentacao();

    List<Documento> getFilterDocument(FilterDocumentDTO filter);

    Documento salvar(DocumentoDTO doc);
}
