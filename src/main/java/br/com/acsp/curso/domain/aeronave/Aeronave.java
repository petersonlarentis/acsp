/**
 *
 */
package br.com.acsp.curso.domain.aeronave;

import br.com.acsp.curso.common.AbstractDocument;
import br.com.acsp.curso.domain.aula.Aula;
import br.com.acsp.curso.domain.horario.Horario;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author eduardobregaida
 */
@Document
public class Aeronave extends AbstractDocument {

    @NotBlank
    private String certificadoMatricula;

    @NotBlank
    private String marca;

    @NotBlank
    private String modelo;

    private String dentel;
    private String fiam;
    private String apoliceSeguro;
    private boolean fichaPesoBalanceamento;
    private boolean manualVoo;
    private boolean checkList;
    private boolean nsca3_5;
    private boolean nsca3_7;
    private boolean diarioBordo;
    private boolean ativo;
    private String motivoInatividade;

    private List<Aula> aulas;

    private List<Horario> horarios;

    public String getCertificadoMatricula() {
        return certificadoMatricula;
    }

    public void setCertificadoMatricula(String certificadoMatricula) {
        this.certificadoMatricula = certificadoMatricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDentel() {
        return dentel;
    }

    public void setDentel(String dentel) {
        this.dentel = dentel;
    }

    public String getFiam() {
        return fiam;
    }

    public void setFiam(String fiam) {
        this.fiam = fiam;
    }

    public String getApoliceSeguro() {
        return apoliceSeguro;
    }

    public void setApoliceSeguro(String apoliceSeguro) {
        this.apoliceSeguro = apoliceSeguro;
    }

    public boolean isFichaPesoBalanceamento() {
        return fichaPesoBalanceamento;
    }

    public void setFichaPesoBalanceamento(boolean fichaPesoBalanceamento) {
        this.fichaPesoBalanceamento = fichaPesoBalanceamento;
    }

    public boolean isManualVoo() {
        return manualVoo;
    }

    public void setManualVoo(boolean manualVoo) {
        this.manualVoo = manualVoo;
    }

    public boolean isCheckList() {
        return checkList;
    }

    public void setCheckList(boolean checkList) {
        this.checkList = checkList;
    }

    public boolean isNsca3_5() {
        return nsca3_5;
    }

    public void setNsca3_5(boolean nsca3_5) {
        this.nsca3_5 = nsca3_5;
    }

    public boolean isNsca3_7() {
        return nsca3_7;
    }

    public void setNsca3_7(boolean nsca3_7) {
        this.nsca3_7 = nsca3_7;
    }

    public boolean isDiarioBordo() {
        return diarioBordo;
    }

    public void setDiarioBordo(boolean diarioBordo) {
        this.diarioBordo = diarioBordo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getMotivoInatividade() {
        return motivoInatividade;
    }

    public void setMotivoInatividade(String motivoInatividade) {
        this.motivoInatividade = motivoInatividade;
    }

    public List<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(List<Aula> aulas) {
        this.aulas = aulas;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    @Override
    public String toString() {
        return certificadoMatricula + ' ' + modelo;
    }
}
