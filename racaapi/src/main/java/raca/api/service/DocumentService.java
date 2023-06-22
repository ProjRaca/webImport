package raca.api.service;

import org.springframework.web.multipart.MultipartFile;
import raca.api.domain.entity.Documento;
import raca.api.domain.entity.Movimentacao;

import java.util.Iterator;
import java.util.List;

public interface DocumentService {
    List<Movimentacao> criar(MultipartFile local);
    List<?> toList(Iterator<?> iterator);

    List<Movimentacao> getAllMovimentacao();

    List<Documento> getFilterDocument();
}
