package raca.api.service;

import raca.api.domain.entity.Documento;
import raca.api.domain.entity.Movimentacao;
import raca.api.rest.dto.DocumentoDTO;
import raca.api.rest.filter.FilterDocumentDTO;

import java.util.Iterator;
import java.util.List;

public interface DocumentService {
    List<?> toList(Iterator<?> iterator);

    List<DocumentoDTO> getAllMDocumentos();

    List<DocumentoDTO> getFilterDocument(FilterDocumentDTO filter);

    DocumentoDTO salvar(DocumentoDTO doc);

    DocumentoDTO update(DocumentoDTO doc);

    void excluir(Integer id);

}
