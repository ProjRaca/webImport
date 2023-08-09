package raca.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import raca.api.domain.entity.Documento;
import raca.api.repository.DocumentRepository;
import raca.api.repository.DocumentoSpecifications;
import raca.api.rest.dto.DocumentoDTO;
import raca.api.service.DocumentService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
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
                    documentoDTO.setTipodocumento(document.getTipodocumento());
                    documentoDTO.setIddocpai(document.getIddocpai());
                    documentoDTO.setNome(document.getNome());
                    documentoDTO.setRestrito(document.isRestrito());
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
    public boolean excluir(Integer id) {
        try {
            documentRepository.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


    private Documento getDocument(DocumentoDTO doc){
        Documento documento = new Documento();
        documento.setFilial(doc.getFilial());
        documento.setEmissor(doc.getEmissor());
        documento.setDatavalidade(doc.getDatavalidade());
        documento.setDatadocumentesc(doc.getDatadocumentesc());
        documento.setDocumento(doc.getDocumento());
        documento.setTipodocumento(doc.getTipodocumento());
        documento.setIddocpai(doc.getIddocpai());
        documento.setNome(doc.getNome());
        documento.setRestrito(doc.isRestrito());
        return documento;
    }
    public static String convertDate(String inputDate) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDateTime localDateTime = LocalDateTime.parse(inputDate, inputFormatter);
        String formattedDate = localDateTime.format(outputFormatter);

        return formattedDate;
    }

    private DocumentoDTO getDocumentDTO(Documento doc){
        DocumentoDTO documento = new DocumentoDTO();
        documento.setId(doc.getId());
        documento.setFilial(doc.getFilial());
        documento.setEmissor(doc.getEmissor());
        documento.setDatavalidade(doc.getDatavalidade());
        documento.setDatadocumentesc(doc.getDatadocumentesc());
        documento.setDocumento(doc.getDocumento());
        documento.setTipodocumento(doc.getTipodocumento());
        if(doc.getIddocpai() != null){
            Optional<Documento> byId = documentRepository.findById(doc.getIddocpai());
            documento.setIddocpai(doc.getIddocpai());
            documento.setNomepai(byId.get().getNome());
        }
        documento.setNome(doc.getNome());
        documento.setRestrito(doc.isRestrito());
        return documento;
    }

    public List<DocumentoDTO> getFilterDocument(Integer id, String filial, String emissor, String datadocumentesc, String datavalidade,
                                                String tipodocumento, Integer iddocpai, boolean restrito, String nome, String datafim) {

        LocalDate datadocumento = null;
        if(datadocumentesc != null){
            datadocumento =   LocalDate.parse(datadocumentesc, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        LocalDate datavalidadeFilt = null;
        if(datavalidade != null){
            datavalidadeFilt =  LocalDate.parse(datavalidade, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        LocalDate dataFimFilt = null;
        if(datavalidade != null){
            dataFimFilt = LocalDate.parse(datafim, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

        Specification<Documento> spec = DocumentoSpecifications.withFilters(id, filial,
                emissor,
                datadocumento,
                datavalidadeFilt,
                tipodocumento,
                iddocpai,
                restrito,
                nome,
                dataFimFilt);
        List<Documento> documentos = documentRepository.findAll(spec);

        return documentos.stream().map(documento -> {
            return getDocumentDTO(documento);
        }).collect(Collectors.toList());
    }

    @Override
    public DocumentoDTO getId(Integer id) {
        Optional<Documento> one = documentRepository.findById(id);
        return getDocumentDTO(one.get());
    }


}
