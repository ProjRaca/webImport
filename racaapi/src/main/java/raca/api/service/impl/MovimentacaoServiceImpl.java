package raca.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import raca.api.domain.entity.Contas;
import raca.api.domain.entity.Movimentacao;
import raca.api.repository.MovimentacaoRepository;
import raca.api.rest.dto.MovimentacaoDTO;
import raca.api.service.ContasService;
import raca.api.service.MovimentacaoService;
import raca.api.util.StatusImportacao;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MovimentacaoServiceImpl implements MovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;
    private final ContasService contasRepository;
    private final ContasService contasService;
    @Override
    public List<Movimentacao> getAllMovimentacao() {
        return movimentacaoRepository.findAll();
    }

    @Override
    public MovimentacaoDTO processMovement(MovimentacaoDTO movimentacao) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        List<Movimentacao> movimentacaos = movimentacao.getListMovimentacao().stream().map(moviment -> {
                Contas byCpfFuncionarioAndHistorico = contasService.findByCpfFuncionarioAndHistorico(
                        moviment.getCpffuncionario(), movimentacao.getHistorico());
                moviment.setCodigofilial(movimentacao.getCodigofilial());
                moviment.setNota(movimentacao.getNota());
                moviment.setCompetencia(movimentacao.getCompetencia());
                moviment.setHistorico(movimentacao.getHistorico());
                moviment.setHistoricodescricao(movimentacao.getHistoricoDescricao());
                moviment.setVencimento(movimentacao.getVencimento());
                moviment.setStatus("P");
                movimentacaoRepository.save(moviment);

            return moviment; // Retornar o objeto moviment após as modificações
        }).collect(Collectors.toList());
        movimentacao.setListMovimentacao(movimentacaos);
        return movimentacao;
    }
    public MovimentacaoDTO criar(MultipartFile file){

        MovimentacaoDTO dados = new MovimentacaoDTO();
        List<Movimentacao> listMov = new ArrayList<>();
        try {
            FileInputStream caminho = convert(file);
            HSSFWorkbook arquivo = new HSSFWorkbook(caminho);
            Sheet planilha = arquivo.getSheetAt(0);
            List<Row> linhas = (List<Row>) toList(planilha.iterator());
            linhas.forEach(row ->{

                List<Cell> celula = (List<Cell>) toList(row.cellIterator());
                MovimentacaoDTO mov = new MovimentacaoDTO();
                listMov.add(getMovimentacao(celula));

            });
            dados.setListMovimentacao(listMov);
            return dados;
        } catch (IOException e) {

        }
        return null;

    }

    public List<?> toList(Iterator<?> iterator){
        return IteratorUtils.toList(iterator);
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
        movimentacao.setStatus(StatusImportacao.A.getTipo());
        movimentacao.setCnpjempresa(celula.get(9).getStringCellValue());
        return movimentacao;
    }

    public MovimentacaoDTO exprtandoMovement(MovimentacaoDTO movimentacao) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        List<Movimentacao> movimentacaos = movimentacao.getListMovimentacao().stream().map(moviment -> {
            if(moviment.getStatus().equals(StatusImportacao.P.getTipo())
                && validaMovimentacaoBD(moviment)){
               Optional<Contas> byCpfFuncionarioAndHistorico = Optional.of(contasService.findByCpfFuncionarioAndHistorico(
                        moviment.getCpffuncionario(), movimentacao.getHistorico()));
                moviment.setCodigofilial(movimentacao.getCodigofilial());
                moviment.setCompetencia(movimentacao.getCompetencia());
                moviment.setHistorico(movimentacao.getHistorico());
                moviment.setVencimento(movimentacao.getVencimento());
                moviment.setStatus("E");
                if(byCpfFuncionarioAndHistorico.isPresent()){
                    moviment.setContacorrente(byCpfFuncionarioAndHistorico.get().getIdconta());
                    moviment.setIdfuncionario(byCpfFuncionarioAndHistorico.get().getMatriculafuncionario());
                    moviment.setTipoparceiro(byCpfFuncionarioAndHistorico.get().getTipoparceiro());

                }
                movimentacaoRepository.save(moviment);
            }
            return moviment; // Retornar o objeto movimentacaoList após as modificações
        }).collect(Collectors.toList());
        movimentacao.setListMovimentacao(movimentacaos);
        return movimentacao;
    }

    private boolean validaMovimentacaoBD(Movimentacao mov){
        List<Movimentacao> movimentacaos = buscarMovimentacoes(mov.getCpffuncionario(), mov.getHistorico(), mov.getCompetencia(), mov.getCnpjempresa());
        return (movimentacaos.isEmpty());
    }

    public List<Movimentacao> buscarMovimentacoes(String cpfFuncionario, String historico, LocalDate competencia, String cnpjEmpresa) {
        return movimentacaoRepository.findByCpfFuncionarioAndHistoricoAndCompetenciaAndCnpjEmpresaAndStatus(cpfFuncionario, historico, competencia, cnpjEmpresa);
    }

    @Override
    public List<Movimentacao> getExMovimentacao() {
        return movimentacaoRepository.getExMovimentacao();
    }

}
