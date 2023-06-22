package raca.api.service;

import raca.api.domain.entity.Contas;

import java.util.List;

public interface ContasService {

    List<String> getHistorico();

    Contas findByCpfFuncionarioAndHistorico(String cpffuncionario, String historico);

    List<Contas> findByCpfFuncionario(String id);

}
