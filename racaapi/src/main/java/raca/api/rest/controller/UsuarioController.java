package raca.api.rest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
import java.util.List;

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
    public Usuario salvar(@RequestBody @Valid Usuario usuario ){
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        return usuarioService.salvar(usuario);
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
            return new TokenDTO(usuario.getLogin(), token);
        } catch (UsernameNotFoundException | SenhaInvalidaException e ){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
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

}
