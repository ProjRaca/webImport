package raca.api.rest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raca.api.domain.entity.postgres.Responsavel;
import raca.api.rest.dto.ResponsavelDTO;
import raca.api.service.ResponsavelService;

import javax.persistence.PostUpdate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
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


    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Lista de todos os responsáveis")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Listagem exibida com sucesso"),
            @ApiResponse(code = 400, message = "Erro de validação")
    })
    public List<ResponsavelDTO> getFilterDocument(@RequestParam(value = "id", required = false) Integer id,
                                                  @RequestParam(value = "cpfcnpj", required = false) String cpfcnpj,
                                                  @RequestParam(value = "nome", required = false) String nome,
                                                  @RequestParam(value = "email", required = false) String email,
                                                  @RequestParam(value = "telefone", required = false) String telefone,
                                                  @RequestParam(value = "filial", required = false) Boolean filial) {

        return responsavelService.getFilterResponsavel(id, cpfcnpj, nome, email, telefone,filial);
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Cria um novo responsável")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Responsável salvo com sucesso"),
            @ApiResponse(code = 400, message = "Erro de validação")
    })
    public void salvar(@RequestBody ResponsavelDTO responsavelDTO) {
        responsavelService.salvarResponsavel(responsavelDTO);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Exclui um responsável pelo ID")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Responsável excluído com sucesso"),
            @ApiResponse(code = 404, message = "Responsável não encontrado")
    })
    public ResponseEntity<Void> excluirResponsavel(@PathVariable Integer id) {
        Optional<Responsavel> responsavelOptional = responsavelService.getRsponsavelId(id);

        if (responsavelOptional.isPresent()) {
            responsavelService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/filiais")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation("Retorna uma lista de filiais")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Filiais cadastradas"),
            @ApiResponse(code = 400, message = "Erro de validação")
    })
    public ResponseEntity<List<Responsavel>> getFiliais() {
        List<Responsavel> listResponsavel = responsavelService.getFiliais();
        if(!listResponsavel.isEmpty())
            return ResponseEntity.ok(listResponsavel);
        else
            return ResponseEntity.ok(new ArrayList<>());
    }


}
