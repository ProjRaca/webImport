package raca.api.service;

import raca.api.rest.dto.DocumentoDTO;
import raca.api.rest.dto.TipoDocumentoDTO;
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
                                         String nome,
                                         String datafim,
                                         String datafimvalidade,
                                         Integer numerodocumento,
                                         String responsave,
                                         Integer empresa);

    DocumentoDTO salvar(DocumentoDTO doc);

    DocumentoDTO update(DocumentoDTO doc);

    boolean excluir(Integer id);

    DocumentoDTO getId(Integer id);

    List<DocumentoDTO> getAllMDocumentosPai();

    List<TipoDocumentoDTO> getTipoDocumento();

    List<DocumentoDTO> encontrarDocPaiPorNome(String nome);

    List<DocumentoDTO> getAllMDocumentosFull();

}
