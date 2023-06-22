package raca.api.rest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import raca.api.domain.entity.Movimentacao;
import raca.api.rest.dto.MovimentacaoDTO;
import raca.api.service.MovimentacaoService;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/movimentacao")
@Api("Api Movimentação")
public class MovimentacaoController {

    private final MovimentacaoService movimentacaoService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Lista todas as movimentações do BD")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Listagem exibida com sucesso"),
            @ApiResponse(code = 400, message = "Erro de validação")
    })
    public List<Movimentacao> getAllMovimentacao() {
        List<Movimentacao> list = movimentacaoService.getAllMovimentacao();
       return list;
    }

    @PostMapping("/atualizar")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Atualiza a Lista de movimentações no BD")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Listagem atualizada com sucesso"),
            @ApiResponse(code = 400, message = "Erro de validação")
    })
    public List<Movimentacao> processMovement(@RequestBody @Valid MovimentacaoDTO movimentacaoDto) {
        List<Movimentacao> list = movimentacaoService.processMovement(movimentacaoDto);
        return list;
    }



}
