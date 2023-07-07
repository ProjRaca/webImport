package raca.api.rest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import raca.api.domain.entity.Movimentacao;
import raca.api.domain.entity.Usuario;
import raca.api.rest.dto.MovimentacaoDTO;
import raca.api.service.MovimentacaoService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/movimentacao")
@Api("Api Movimentação")
public class MovimentacaoController {

    private final MovimentacaoService movimentacaoService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.ACCEPTED)
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
    public MovimentacaoDTO processMovement(@RequestBody @Valid MovimentacaoDTO movimentacaoDto) {
        return movimentacaoService.processMovement(movimentacaoDto);
    }

    @PostMapping("/upload-xls")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Enviar um novo documento")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Documento salvo com sucesso"),
            @ApiResponse(code = 400, message = "Erro de validação")
    })
    public ResponseEntity<MovimentacaoDTO> uploadPDF(@RequestParam("xls_file") MultipartFile file) {
        MovimentacaoDTO dto = movimentacaoService.criar(file);
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.ok(dto);
    }


}
