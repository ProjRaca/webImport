package raca.api.rest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import raca.api.domain.entity.Documento;
import raca.api.domain.entity.Responsavel;
import raca.api.domain.entity.Usuario;
import raca.api.rest.dto.FilterDocumentDTO;
import raca.api.rest.dto.ResponsavelDTO;
import raca.api.service.ResponsavelService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/responsavel")
@Api("Api Responsável")
public class ResponsavelController {

    private ResponsavelService responsavelService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Lista de todos os responsáveis")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Listagem exibida com sucesso"),
            @ApiResponse(code = 400, message = "Erro de validação")
    })
    public List<Responsavel> getFilterDocument() {
        return responsavelService.getAllResponsavel();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation("Retorna um responsável pelo Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Responsável consultado com sucesso"),
            @ApiResponse(code = 400, message = "Erro de validação")
    })
    public Responsavel getRsponsavelId(@PathVariable Integer id) {
        return responsavelService.getRsponsavelId(id).get();
    }

    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Retorna um responsável pelo Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Responsável salvo com sucesso"),
            @ApiResponse(code = 400, message = "Erro de validação")
    })
    public void salvar(@RequestBody ResponsavelDTO responsavelDTO) {
        responsavelService.salvarResponsavel(responsavelDTO);
    }



}
