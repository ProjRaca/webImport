package raca.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import raca.api.domain.entity.postgres.Movimentacao;
import raca.api.repository.postgres.MovimentacaoRepository;
import raca.api.rest.dto.MovimentacaoDTO;
import raca.api.service.CarregaDadosService;

import java.io.*;
import java.util.Iterator;
import java.util.List;
@RequiredArgsConstructor
@Service
public class CarregaDadosServiceImpl implements CarregaDadosService {

    private final MovimentacaoRepository movimentacaoRepository;



    public List<?> toList(Iterator<?> iterator){
        return IteratorUtils.toList(iterator);
    }
    
    public List<Movimentacao> getAllMovimentacao(){
        return movimentacaoRepository.findAll();
    }

    @Override
    public List<Movimentacao> processMovement(MovimentacaoDTO movimentacao) {
        return null;
    }

    @Override
    public List<Movimentacao> criar(MultipartFile local) {
        return null;
    }


    private FileInputStream convert(MultipartFile multipartFile) throws IOException {
        File file = convertToFile(multipartFile);
        return new FileInputStream(file);
    }

    private File convertToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        try (OutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        }
        return file;
    }

      
}

