package raca.api.rest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raca.api.domain.entity.Responsavel;
import raca.api.rest.dto.ResponsavelDTO;
import raca.api.service.ResponsavelService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:4200")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/responsavel")
@Api("Api Responsável")
public class ResponsavelController {

    private final ResponsavelService responsavelService;

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
        Optional<Responsavel> rsponsavelId = responsavelService.getRsponsavelId(id);
        if(rsponsavelId.isPresent())
            return rsponsavelId.get();
        else
            return new Responsavel();

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

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Retorna um responsável pelo Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Responsável alterado com sucesso"),
            @ApiResponse(code = 400, message = "Erro de validação")
    })
    public Responsavel update(@RequestBody ResponsavelDTO responsavelDTO) {
        return responsavelService.editarResponsavel(responsavelDTO);
    }

    @GetMapping("/nome/{nome}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation("Retorna uma lista de responsáveis pelo nome")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Responsável consultado com sucesso"),
            @ApiResponse(code = 400, message = "Erro de validação")
    })
    public ResponseEntity<List<Responsavel>> encontrarPorNome(@PathVariable String nome) {
        List<Responsavel> listResponsavel = responsavelService.encontrarPorNome(nome);
        if(!listResponsavel.isEmpty())
            return ResponseEntity.ok(listResponsavel);
        else
            return ResponseEntity.ok(new ArrayList<>());
    }


}
