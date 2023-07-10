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
import raca.api.rest.filter.FilterDocumentDTO;
import raca.api.service.DocumentService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("http://localhost:4200")
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
    public List<DocumentoDTO> getFilterDocument(@RequestBody FilterDocumentDTO dto) {
        return documentService.getFilterDocument(dto);
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
    public ResponseEntity<DocumentoDTO> salvar(@RequestBody @Valid DocumentoDTO dto){
        DocumentoDTO savedDocumento = documentService.salvar(dto);
        if (savedDocumento != null) {
            return ResponseEntity.ok(savedDocumento);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @PutMapping
    public ResponseEntity<DocumentoDTO> update(@RequestBody @Valid DocumentoDTO dto){
        DocumentoDTO updatedDocumento = documentService.update(dto);
        if (updatedDocumento != null) {
            return ResponseEntity.ok(updatedDocumento);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id){
        documentService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
