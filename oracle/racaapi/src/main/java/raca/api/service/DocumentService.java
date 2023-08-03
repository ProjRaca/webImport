package raca.api.service;

import raca.api.rest.dto.DocumentoDTO;
import raca.api.rest.filter.FilterDocumentDTO;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

public interface DocumentService {
    List<?> toList(Iterator<?> iterator);

    List<DocumentoDTO> getAllMDocumentos();

    List<DocumentoDTO> getFilterDocument(Integer id,
                                         String filial,
                                         String emissor,
                                         String datadocumentesc,
                                         String datavalidade,
                                         String tipodocumento,
                                         Integer iddocpai,
                                         boolean restrito,
                                         String nome);

    DocumentoDTO salvar(DocumentoDTO doc);

    DocumentoDTO update(DocumentoDTO doc);

    void excluir(Integer id);

}
