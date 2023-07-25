package raca.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import raca.api.domain.entity.postgres.Contas;
import raca.api.domain.entity.postgres.Movimentacao;
import raca.api.repository.oracle.MovimentacaoDao;
import raca.api.repository.postgres.MovimentacaoRepository;
import raca.api.rest.dto.MovimentacaoDTO;
import raca.api.service.ContasService;
import raca.api.service.MovimentacaoService;
import raca.api.util.StatusImportacao;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MovimentacaoServiceImpl implements MovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;
    private final MovimentacaoDao movimentacaoDao;

    //private final MovimentacaoRepositoryOracle movimentacaoRepositoryOracle;

    private final ContasService contasRepository;
    private final ContasService contasService;
    @Override
    public List<Movimentacao> getAllMovimentacao() {
        return movimentacaoRepository.findAll();
    }
    int cont=0;
    @Override
    public MovimentacaoDTO processMovement(MovimentacaoDTO movimentacao) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        List<Movimentacao> movimentacaos = movimentacao.getListMovimentacao().stream().map(moviment -> {
                cont++;
                moviment.setCodigofilial(movimentacao.getCodigofilial());
                moviment.setNota(movimentacao.getNota());
                moviment.setCompetencia(movimentacao.getCompetencia());
                moviment.setHistorico(movimentacao.getHistorico());
                moviment.setHistoricodescricao(movimentacao.getHistoricoDescricao());
                moviment.setVencimento(movimentacao.getVencimento());
                moviment.setStatus("P");
                movimentacaoRepository.save(moviment);
                System.out.println("cont registro atual = " + cont)  ;

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

    public MovimentacaoDTO exprtandoMovement(MovimentacaoDTO movimentacao) throws Exception {
        cont = 0;
        List<Movimentacao> movimentacaos = getMovimentacaoList(movimentacao);

        movimentacaoDao.TransferirMovimentacaoOracle(movimentacaos);
        movimentacao.setListMovimentacao(movimentacaos);
        return movimentacao;
    }

    private List<Movimentacao> getMovimentacaoList(MovimentacaoDTO movimentacao) {
        List<Movimentacao> movimentacaosList = new ArrayList<>();
        movimentacaosList.stream().forEach( moviment -> {

            Contas byCpfFuncionarioAndHistorico = getContaFuncionarioPorHistorico(
                    moviment.getCpffuncionario(), movimentacao.getHistorico());
            if(byCpfFuncionarioAndHistorico != null){
                if(moviment.getStatus().equals(StatusImportacao.P.getTipo())
                        && validaMovimentacaoBD(moviment)){
                    moviment.setCodigofilial(movimentacao.getCodigofilial());
                    moviment.setCompetencia(movimentacao.getCompetencia());
                    moviment.setHistorico(movimentacao.getHistorico());
                    moviment.setVencimento(movimentacao.getVencimento());
                    moviment.setStatus("E");
                    moviment.setContacorrente(byCpfFuncionarioAndHistorico.getIdconta());
                    moviment.setIdfuncionario(byCpfFuncionarioAndHistorico.getMatriculafuncionario());
                    moviment.setTipoparceiro(byCpfFuncionarioAndHistorico.getTipoparceiro());
                    movimentacaoRepository.save(moviment);
                    cont ++;
                    System.out.println("cont registro atual = " + cont)  ;
                }
            }
            movimentacaosList.add(moviment);
        });
        return movimentacaosList;
    }

    private boolean validaMovimentacaoBD(Movimentacao mov){
        return !buscarMovimentacoes(mov.getCpffuncionario(), mov.getHistorico(), mov.getCompetencia(), mov.getCnpjempresa());

    }
    public boolean buscarMovimentacoes(String cpfFuncionario, String historico, Date competencia, String cnpjEmpresa) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String competenciaString = new SimpleDateFormat("yyyy-MM-dd").format(competencia);
        LocalDate competenciaDate = LocalDate.parse(competenciaString, formatter);
        String compet = formatter.format(competenciaDate);
        List<String> list = null;//movimentacaoRepositoryOracle.findByCpfFuncionarioAndHistoricoAndCompetenciaAndCnpjEmpresaAndStatus(cpfFuncionario, historico, compet, cnpjEmpresa);
        return (list != null && !list.isEmpty());
    }


    private Contas getContaFuncionarioPorHistorico(String cpf, String descricao){
        List<Contas> list = contasService.getContaFuncionario(cpf, descricao);
        //List<Contas> list = contasService.getContaFuncionario(cpf, descricao);
        List<Contas> collect = list.stream().filter(x -> x.getDescricao().equals(descricao)).collect(Collectors.toList());
        if (collect.isEmpty()){
            return null;
        }
        return collect.get(0);


    }


}
