package raca.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raca.api.domain.entity.postgres.Usuario;
import raca.api.exception.SenhaInvalidaException;
import raca.api.repository.postgres.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UserDetailsService {


    private final PasswordEncoder encoder;

    @Autowired
    private UsuarioRepository repository;

    @Transactional
    public Usuario salvar(Usuario usuario){
        if(!repository.findByLogin(usuario.getLogin()).isPresent()){
            usuario.setStatus("A");
            return repository.save(usuario);
        }
        return null;
    }

    public UserDetails autenticar( Usuario usuario ){
        UserDetails user = loadUserByUsername(usuario.getLogin());
        boolean senhasBatem = encoder.matches( usuario.getSenha(), user.getPassword() );

        if(senhasBatem){
            return user;
        }

        throw new SenhaInvalidaException();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados."));

       // Usuario usuario1 = repository.findByLoginAndSenha(username, usuario.getSenha())
            //    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados."));

        String[] roles = usuario.isAdmin() ?
                new String[]{"ADMIN", "USER"} : new String[]{"USER"};

        return User
                .builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }

    public List<Usuario> getAllUsuarios(){
        return repository.findAll();
    }

    public Optional<Usuario> getIdlUsuarios(Integer id){
        return repository.findById(id);
    }

    public void deleteIdlUsuarios(Integer id){
        repository.deleteById(id);
    }

    public List<Usuario> encontrarPorNome(String nome) {
        return repository.encontrarPorNome(nome);
    }

    public List<Usuario> getAllContas() {
        return repository.findAll();
    }

    @Transactional
    public Usuario update(Usuario usuario){
        usuario.setStatus("A");
        return repository.save(usuario);
    }

}
