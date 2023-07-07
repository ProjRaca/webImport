package raca.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import raca.api.domain.entity.Documento;
import raca.api.domain.entity.Movimentacao;
import raca.api.repository.DocumentRepository;
import raca.api.rest.filter.FilterDocumentDTO;
import raca.api.service.DocumentService;

import java.util.Iterator;
import java.util.List;
@RequiredArgsConstructor
@Service
public class DocumentoServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;

    @Override
    public List<?> toList(Iterator<?> iterator) {
        return null;
    }

    @Override
    public List<Movimentacao> getAllMovimentacao() {
        return null;
    }

    @Override
    public List<Documento> getFilterDocument(FilterDocumentDTO filter) {
        //return documentRepository.findDocumentsByFilter(filter);
        return null;

    }
}
