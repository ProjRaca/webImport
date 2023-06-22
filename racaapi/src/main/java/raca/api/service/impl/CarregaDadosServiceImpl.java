package raca.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import raca.api.domain.entity.Documento;
import raca.api.domain.entity.Movimentacao;
import raca.api.repository.MovimentacaoRepository;
import raca.api.service.DocumentService;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@RequiredArgsConstructor
@Service
public class CarregaDadosServiceImpl implements DocumentService {

    private final MovimentacaoRepository movimentacaoRepository;
    
    public List<Movimentacao> criar(MultipartFile file){

        List<Movimentacao> dados = new ArrayList<>();
        try {
            FileInputStream caminho = convert(file);
            HSSFWorkbook arquivo = new HSSFWorkbook(caminho);
            Sheet planilha = arquivo.getSheetAt(0);
            List<Row> linhas = (List<Row>) toList(planilha.iterator());
            linhas.forEach(row ->{

                //percorrendo as celulas e pegando o conteúdo
                List<Cell> celula = (List<Cell>) toList(row.cellIterator());
                Movimentacao mov = getMovimentacao(celula);
                dados.add(mov);
                movimentacaoRepository.save(mov);

            });
            return dados;
        } catch (IOException e) {

        }
        return null;

    }

    private Movimentacao getMovimentacao(List<Cell> celula){
        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setIdfuncionario((int) celula.get(0).getNumericCellValue());
        movimentacao.setCpffuncionario(celula.get(1).getStringCellValue());
        movimentacao.setNomefuncionario(celula.get(2).getStringCellValue());
        movimentacao.setAgencia(celula.get(3).getStringCellValue());
        movimentacao.setAgenciadv(celula.get(4).getStringCellValue());
        movimentacao.setContacorrente(celula.get(5).getStringCellValue());
        movimentacao.setContacorrentedv(celula.get(6).getStringCellValue());
        String valor = String.valueOf(celula.get(7).getNumericCellValue());
        movimentacao.setValor(new BigDecimal(valor));
        int status = (int) celula.get(8).getNumericCellValue();
        movimentacao.setStatus(String.valueOf(status));
        movimentacao.setCnpjempresa(celula.get(9).getStringCellValue());

        return movimentacao;
    }
    
    public List<?> toList(Iterator<?> iterator){
        return IteratorUtils.toList(iterator);
    }
    
    public List<Movimentacao> getAllMovimentacao(){
        return movimentacaoRepository.findAll();
    }

    @Override
    public List<Documento> getFilterDocument() {
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

