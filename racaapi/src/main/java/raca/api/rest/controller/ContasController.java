package raca.api.rest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import raca.api.service.ContasService;

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

}
