package raca.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import raca.api.domain.entity.Documento;
import raca.api.domain.entity.Movimentacao;
import raca.api.repository.DocumentRepository;
import raca.api.rest.dto.DocumentoDTO;
import raca.api.rest.filter.FilterDocumentDTO;
import raca.api.service.DocumentService;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DocumentoServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;

    @Override
    public List<?> toList(Iterator<?> iterator) {
        return null;
    }

    @Override
    public List<DocumentoDTO> getAllMDocumentos() {
        List<Documento> all = documentRepository.findAll();
        return getDocumentoDTOS(all);
    }

    @Override
    public List<DocumentoDTO> getFilterDocument(FilterDocumentDTO filter) {
        List<Documento> documentsByFilter =
                documentRepository.findDocumentsByFilter(filter.getFilial(), filter.getEmissor(),
                        filter.getDatadocumentesc(), filter.getDatavalidade());
        return getDocumentoDTOS(documentsByFilter);
    }

    private static List<DocumentoDTO> getDocumentoDTOS(List<Documento> documentsByFilter) {
        return documentsByFilter.stream()
                .map(document -> {
                    DocumentoDTO documentoDTO = new DocumentoDTO();
                    documentoDTO.setId(document.getId());
                    documentoDTO.setFilial(document.getFilial());
                    documentoDTO.setEmissor(document.getEmissor());
                    documentoDTO.setDatadocumentesc(document.getDatadocumentesc());
                    documentoDTO.setDatavalidade(document.getDatavalidade());
                    documentoDTO.setDocumento(document.getDocumento());
                    return documentoDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public DocumentoDTO salvar(DocumentoDTO doc) {
        Documento save = documentRepository.save(getDocument(doc));
        return getDocumentDTO(save);
    }

    @Override
    public DocumentoDTO update(DocumentoDTO doc) {
        return salvar(doc);
    }

    @Override
    public void excluir(Integer id) {
          documentRepository.deleteById(id);
    }

    private Documento getDocument(DocumentoDTO doc){
        Documento documento = new Documento();
        documento.setFilial(doc.getFilial());
        documento.setEmissor(doc.getEmissor());
        documento.setDatavalidade(doc.getDatavalidade());
        documento.setDatadocumentesc(doc.getDatadocumentesc());
        documento.setDocumento(doc.getDocumento());
        return documento;
    }

    private DocumentoDTO getDocumentDTO(Documento doc){
        DocumentoDTO documento = new DocumentoDTO();
        documento.setFilial(doc.getFilial());
        documento.setEmissor(doc.getEmissor());
        documento.setDatavalidade(doc.getDatavalidade());
        documento.setDatadocumentesc(doc.getDatadocumentesc());
        documento.setDocumento(doc.getDocumento());
        return documento;
    }

}
