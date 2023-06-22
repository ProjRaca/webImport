package raca.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import raca.api.repository.ContasRepository;
import raca.api.service.ContasService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ContasServiceImpl implements ContasService {

    private final ContasRepository contasRepository;

    @Override
    public List<String> getHistorico() {
        return contasRepository.getHistorico();
    }
}
