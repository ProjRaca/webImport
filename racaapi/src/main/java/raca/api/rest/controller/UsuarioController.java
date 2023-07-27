package raca.api.rest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import raca.api.domain.entity.Usuario;
import raca.api.exception.SenhaInvalidaException;
import raca.api.rest.dto.CredenciaisDTO;
import raca.api.rest.dto.TokenDTO;
import raca.api.security.jwt.JwtService;
import raca.api.service.impl.UsuarioServiceImpl;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@Api("Api Usuários")
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
        public ResponseEntity<Usuario> salvar(@RequestBody @Valid Usuario usuario ){
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        Usuario salvar = usuarioService.salvar(usuario);
        if(salvar != null)
            return ResponseEntity.ok(salvar);
        else
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);

    }

    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais){
        try{
            Usuario usuario = Usuario.builder()
                    .login(credenciais.getLogin())
                    .senha(credenciais.getSenha())
                    .admin(false).build();
            UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);
            String token = jwtService.gerarToken(usuario);
            return new TokenDTO(usuario.getLogin(), token,0,"");
        } catch (UsernameNotFoundException | SenhaInvalidaException e ){
            return new TokenDTO("", "",404,"Login  ou senha innválidos");
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Retorna um usuário pelo Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Usuário consultado com sucesso"),
            @ApiResponse(code = 400, message = "Erro de validação")
    })
    public Usuario getUsuarioId(@PathVariable Integer id) {
        return usuarioService.getIdlUsuarios(id).get();
    }


    @GetMapping("/all")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Retorna todos os usuários")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Listagem realizada com sucesso"),
            @ApiResponse(code = 400, message = "Erro de validação")
    })
    public List<Usuario> getAll() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/nome/{nome}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation("Retorna uma lista de responsáveis pelo nome")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Responsável consultado com sucesso"),
            @ApiResponse(code = 400, message = "Erro de validação")
    })
    public ResponseEntity<List<Usuario>> encontrarPorNome(@PathVariable String nome) {
        List<Usuario> listResponsavel = usuarioService.encontrarPorNome(nome);
        if(!listResponsavel.isEmpty())
            return ResponseEntity.ok(listResponsavel);
        else
            return ResponseEntity.ok(new ArrayList<>());
    }

}
