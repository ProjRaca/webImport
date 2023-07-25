package raca.api.rest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raca.api.domain.entity.postgres.Contas;
import raca.api.domain.entity.postgres.Usuario;
import raca.api.service.ContasService;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/contas")
@Api("Api Contas")
public class ContasController {

    private final ContasService contasService;

    @GetMapping("/historico")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Listagem do histórico")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Listagem exibida com sucesso"),
            @ApiResponse(code = 400, message = "Erro de validação")
    })
    public List<String> getHistorico() {
        return contasService.getHistorico();
    }

    @GetMapping("/{cpf}/{descricao}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation("Retorna uma lista de contas pelo cpf e descricao")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Consulta com sucesso"),
            @ApiResponse(code = 400, message = "Erro de validação")
    })
    public ResponseEntity<List<Contas>> encontrarPorCpfDescricao(@PathVariable String cpf, @PathVariable String descricao) {
        List<Contas> contaFuncionario = contasService.getContaFuncionario(cpf, descricao);
        if(!contaFuncionario.isEmpty())
            return ResponseEntity.ok(contaFuncionario);
        else
            return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation("Retorna uma lista de contas")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Consulta com sucesso"),
            @ApiResponse(code = 400, message = "Erro de validação")
    })
    public ResponseEntity<List<Contas>> getAll() {
        List<Contas> contaFuncionario = contasService.getAllContas();
        if(!contaFuncionario.isEmpty())
            return ResponseEntity.ok(contaFuncionario);
        else
            return ResponseEntity.ok(new ArrayList<>());
    }

}
