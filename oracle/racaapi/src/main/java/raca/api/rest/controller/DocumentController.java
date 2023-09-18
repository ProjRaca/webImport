package raca.api.rest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raca.api.rest.dto.DocumentoDTO;
import raca.api.rest.dto.TipoDocumentoDTO;
import raca.api.rest.filter.FilterDocumentDTO;
import raca.api.service.DocumentService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/document")
@Api("Api Documentos")
public class DocumentController {

    private final DocumentService documentService;

    @GetMapping("/filter")
    @ApiOperation("Lista todos os documentos filtrados")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Listagem exibida com sucesso"),
            @ApiResponse(code = 400, message = "Erro de validação")
    })
    public List<DocumentoDTO> getFilterDocument(@RequestParam(value = "id", required = false) Integer id,
                                                @RequestParam(value = "filial", required = false) String filial,
                                                @RequestParam(value = "emissor", required = false) String emissor,
                                                @RequestParam(value = "datadocumentesc", required = false) String datadocumentesc,
                                                @RequestParam(value = "datavalidade", required = false) String datavalidade,
                                                @RequestParam(value = "tipodocumento", required = false) String tipodocumento,
                                                @RequestParam(value = "iddocpai", required = false) Integer iddocpai,
                                                @RequestParam(value = "restrito", required = false) Boolean restrito,
                                                @RequestParam(value = "nome", required = false) String nome,
                                                @RequestParam(value = "datafim", required = false) String datafim,
                                                @RequestParam(value = "datafimvalidade", required = false) String datafimvalidade,
                                                @RequestParam(value = "numerodocumento", required = false) Integer numerodocumento,
                                                @RequestParam(value = "responsavel", required = false) String responsavel,
                                                @RequestParam(value = "empresa", required = false) Integer empresa) {

        return documentService.getFilterDocument(id, filial, emissor, datadocumentesc, datavalidade,
                tipodocumento, iddocpai, restrito, nome, datafim, datafimvalidade, numerodocumento, responsavel, empresa);
    }

    @GetMapping
    @ApiOperation("Lista todos os documentos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Listagem exibida com sucesso"),
            @ApiResponse(code = 400, message = "Erro de validação")
    })
    public List<DocumentoDTO> getAllDocuments() {
        return documentService.getAllMDocumentos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DocumentoDTO> salvar(@RequestBody DocumentoDTO dto) {
        DocumentoDTO savedDocumento = documentService.salvar(dto);
        if (savedDocumento != null) {
            return ResponseEntity.ok(savedDocumento);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @PutMapping
    public ResponseEntity<DocumentoDTO> update(@RequestBody DocumentoDTO dto) {
        DocumentoDTO updatedDocumento = documentService.update(dto);
        if (updatedDocumento != null) {
            return ResponseEntity.ok(updatedDocumento);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> excluir(@PathVariable Integer id) {
        boolean excluido = documentService.excluir(id);

        if (excluido) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentoDTO> getId(@PathVariable Integer id) {
        DocumentoDTO dto = documentService.getId(id);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pai")
    @ApiOperation("Lista todos os documentos pai")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Listagem exibida com sucesso"),
            @ApiResponse(code = 400, message = "Erro de validação")
    })
    public List<DocumentoDTO> getAllDocumentsPai() {
        return documentService.getAllMDocumentosPai();
    }

    @GetMapping("/tipodocumento")
    @ApiOperation("Lista todos os tipos de documentos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Listagem exibida com sucesso"),
            @ApiResponse(code = 400, message = "Erro de validação")
    })
    public List<TipoDocumentoDTO> getTipoDocumento() {
        return documentService.getTipoDocumento();
    }

    @GetMapping("/pai/{nome}")
    @ApiOperation("Lista todos os documentos pai por nome")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Listagem exibida com sucesso"),
            @ApiResponse(code = 400, message = "Erro de validação")
    })
    public List<DocumentoDTO> encontrarDocPaiPorNome(@PathVariable String nome) {
        return documentService.encontrarDocPaiPorNome(nome);

    }

    @GetMapping("/all")
    @ApiOperation("Lista todos os documentos full")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Listagem exibida com sucesso"),
            @ApiResponse(code = 400, message = "Erro de validação")
    })
    public List<DocumentoDTO> getAllDocumentsFull() {
        return documentService.getAllMDocumentosFull();
    }
}
