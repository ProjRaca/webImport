package raca.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raca.api.domain.entity.postgres.Documento;
import raca.api.domain.entity.postgres.Historico;
import raca.api.domain.entity.postgres.Responsavel;
import raca.api.repository.postgres.DocumentRepository;
import raca.api.repository.postgres.HistoricoRepository;
import raca.api.repository.specifications.DocumentoSpecifications;
import raca.api.rest.dto.DocumentoDTO;
import raca.api.rest.dto.TipoDocumentoDTO;
import raca.api.service.DocumentService;
import raca.api.service.ResponsavelService;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DocumentoServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final ResponsavelService responsavelService;
    private final HistoricoRepository historicoRepository;


    @Override
    public List<?> toList(Iterator<?> iterator) {
        return null;
    }

    @Override
    public List<DocumentoDTO> getAllMDocumentos() {
        try {
            boolean isAdmin = isAdmin();
            if (isAdmin) {
                List<Documento> all = documentRepository.findAll().stream().limit(70).collect(Collectors.toList());
                return getDocumentoDTOS(all);
            } else {
                List<Documento> all = documentRepository.listDocNotRestrito().stream().limit(70).collect(Collectors.toList());
                return getDocumentoDTOS(all);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<DocumentoDTO> getAllMDocumentosFull() {
        try {
            boolean isAdmin = isAdmin();
            if (isAdmin) {
                List<Documento> all = documentRepository.findAll().stream().collect(Collectors.toList());
                return getDocumentoDTOS(all);
            } else {
                List<Documento> all = documentRepository.listDocNotRestrito().stream().collect(Collectors.toList());
                return getDocumentoDTOS(all);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<DocumentoDTO> getDocumentoDTOS(List<Documento> documentsByFilter) {
        return documentsByFilter.stream()
                .map(document -> {
                    DocumentoDTO documentoDTO = new DocumentoDTO();
                    documentoDTO.setId(document.getId());
                    documentoDTO.setFilial(document.getFilial());
                    if (document.getFilial() != null && !document.getFilial() .isEmpty()) {
                        Optional<Responsavel> responsavel = responsavelService.getRsponsavelId(Integer.valueOf(document.getFilial()));
                        responsavel.ifPresent(x -> {
                            documentoDTO.setNomefilial(x.getNome());
                        });
                    }
                    if (document.getIdresponsavel() != null) {
                        Optional<Responsavel> responsavel = responsavelService.getRsponsavelId(document.getIdresponsavel());
                        responsavel.ifPresent(x -> {
                            documentoDTO.setResponsavel(x.getNome());
                        });
                    }
                    if(document.getTipodocumento() != null){
                        Historico one = historicoRepository.getOne(Integer.valueOf(document.getTipodocumento().trim()));
                        if(one != null){
                            documentoDTO.setTipodocumento(one.getNome());
                        }
                    }
                    documentoDTO.setEmissor(document.getEmissor());
                    documentoDTO.setDatadocumentesc(document.getDatadocumentesc());
                    documentoDTO.setDatavalidade(document.getDatavalidade());
                    documentoDTO.setDocumento(document.getDocumento());
                    documentoDTO.setTipodocumento(document.getTipodocumento());
                    documentoDTO.setIddocpai(document.getIddocpai());
                    documentoDTO.setNome(document.getNome());
                    documentoDTO.setRestrito(document.isRestrito());
                    documentoDTO.setEmpresa(document.getEmpresa());
                    documentoDTO.setNumerodocumento(document.getNumerodocumento());
                    documentoDTO.setIdresponsavel(document.getIdresponsavel());
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
        Documento save = documentRepository.save(getDocument(doc));
        return getDocumentDTO(save);
    }
    @Transactional
    @Override
    public boolean excluir(Integer id) {
        try {

            boolean isAdmin = isAdmin();

            if (isAdmin) {
                documentRepository.deleteById(id);
                return true;
            } else {
                // Usuário não tem permissão de ADMIN
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }



    private Documento getDocument(DocumentoDTO doc){
        Documento documento = new Documento();
        documento.setFilial(doc.getFilial());
        documento.setEmissor(doc.getEmissor());
        if(doc.getDatavalidade() != null)
            documento.setDatavalidade(doc.getDatavalidade());
        if(doc.getDatadocumentesc() != null)
            documento.setDatadocumentesc(doc.getDatadocumentesc());
        documento.setDocumento(doc.getDocumento());
        if(doc.getTipodocumento() != null){
            Historico one = historicoRepository.getOne(Integer.valueOf(doc.getTipodocumento().trim()));
            if(one != null){
                documento.setTipodocumento(one.getNome());
            }
        }
        documento.setIddocpai(doc.getIddocpai());
        documento.setNome(doc.getNome());
        documento.setRestrito(doc.isRestrito());
        documento.setId(doc.getId());
        documento.setEmpresa(doc.getEmpresa());
        documento.setNumerodocumento(doc.getNumerodocumento());
        documento.setIdresponsavel(doc.getIdresponsavel());
        return documento;
    }


    private DocumentoDTO getDocumentDTO(Documento doc){
        DocumentoDTO documento = new DocumentoDTO();
        documento.setId(doc.getId());
        if(doc.getFilial() != null && !doc.getFilial().isEmpty()){
            responsavelService.getRsponsavelId(Integer.valueOf(doc.getFilial())).ifPresent( x->{
                documento.setNomefilial(x.getNome());
            });
        }
        if (doc.getIdresponsavel() != null) {
            Optional<Responsavel> responsavel = responsavelService.getRsponsavelId(doc.getIdresponsavel());
            responsavel.ifPresent(x -> {
                documento.setResponsavel(x.getNome());
            });
        }
        documento.setFilial(doc.getFilial());
        documento.setEmissor(doc.getEmissor());
        documento.setDatavalidade(doc.getDatavalidade());
        documento.setDatadocumentesc(doc.getDatadocumentesc());
        documento.setDocumento(doc.getDocumento());
        if(doc.getTipodocumento() != null){
            Historico one = historicoRepository.getOne(Integer.valueOf(doc.getTipodocumento().trim()));
            if(one != null){
                documento.setTipodocumento(one.getNome());
            }
        }
        if(doc.getIddocpai() != null){
            Optional<Documento> byId = documentRepository.findById(doc.getIddocpai());
            if(byId.isPresent()){
                documento.setIddocpai(doc.getIddocpai());
                documento.setNomepai(byId.get().getNome());
            }
        }
        documento.setNome(doc.getNome());
        documento.setRestrito(doc.isRestrito());
        documento.setEmpresa(doc.getEmpresa());
        documento.setNumerodocumento(doc.getNumerodocumento());
        documento.setIdresponsavel(doc.getIdresponsavel());
        return documento;
    }

    public List<DocumentoDTO> getFilterDocument(Integer id, String filial, String emissor, String datadocumentesc, String datavalidade,
                                                String tipodocumento, Integer iddocpai, boolean restrito, String nome, String datafim,
                                                String datafimvalidade, Integer numerodocumento, String responsavel,Integer empresa) {

        Specification<Documento> spec = DocumentoSpecifications.withFilters(id, filial,
                emissor,
                datadocumentesc,
                datavalidade,
                tipodocumento,
                iddocpai,
                restrito,
                nome,
                datafim,
                datafimvalidade,
                numerodocumento,
                responsavel,
                empresa);
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

    private boolean isAdmin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().contains("ADMIN"));
    }

    @Override
    public List<DocumentoDTO> getAllMDocumentosPai() {
         List<Documento> all = documentRepository.getAllMDocumentosPai();
        return getDocumentoDTOS(all);
    }

    @Override
    public List<TipoDocumentoDTO> getTipoDocumento() {
        return historicoRepository.findAll().stream()
                .map(x -> new TipoDocumentoDTO(x.getId(), x.getNome()))
                .collect(Collectors.toList());

    }

    public List<DocumentoDTO> encontrarDocPaiPorNome(String nome){
        List<Documento> documentos = documentRepository.encontrarDocPaiPorNome(nome.toUpperCase());
        return getDocumentoDTOS(documentos);
    }


}
