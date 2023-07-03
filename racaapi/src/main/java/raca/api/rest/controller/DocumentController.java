package raca.api.rest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import raca.api.domain.entity.Documento;
import raca.api.domain.entity.Movimentacao;
import raca.api.rest.dto.FilterDocumentDTO;
import raca.api.service.DocumentService;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/document")
@Api("Api Documentos")
public class DocumentController {

   // private final DocumentService documentService;


    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Lista todas as movimentações do BD")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Listagem exibida com sucesso"),
            @ApiResponse(code = 400, message = "Erro de validação")
    })
    public List<Documento> getFilterDocument(@RequestBody FilterDocumentDTO dto) {
        //List<Documento> list = documentService.getFilterDocument(dto);
        return null;
    }



}
