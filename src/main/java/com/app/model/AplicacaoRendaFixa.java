package com.app.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AplicacaoRendaFixa implements Serializable {

    private Long id;
    private String nomeAplicacao;
    private String instituicaoFinanceira;
    private BigDecimal valorInvestido;
    private BigDecimal percentualRetorno;
    private BigDecimal valorRetorno;
    private Date dataAplicacao;
    private Date dataVencimento;
    private String tipoAplicacao;
    private String observacoes;
    private boolean ativo;

    public AplicacaoRendaFixa() {
        this.ativo = true;
        this.dataAplicacao = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeAplicacao() {
        return nomeAplicacao;
    }

    public void setNomeAplicacao(String nomeAplicacao) {
        this.nomeAplicacao = nomeAplicacao;
    }

    public String getInstituicaoFinanceira() {
        return instituicaoFinanceira;
    }

    public void setInstituicaoFinanceira(String instituicaoFinanceira) {
        this.instituicaoFinanceira = instituicaoFinanceira;
    }

    public BigDecimal getValorInvestido() {
        return valorInvestido;
    }

    public void setValorInvestido(BigDecimal valorInvestido) {
        this.valorInvestido = valorInvestido;
    }

    public BigDecimal getPercentualRetorno() {
        return percentualRetorno;
    }

    public void setPercentualRetorno(BigDecimal percentualRetorno) {
        this.percentualRetorno = percentualRetorno;
    }

    public BigDecimal getValorRetorno() {
        return valorRetorno;
    }

    public void setValorRetorno(BigDecimal valorRetorno) {
        this.valorRetorno = valorRetorno;
    }

    public Date getDataAplicacao() {
        return dataAplicacao;
    }

    public void setDataAplicacao(Date dataAplicacao) {
        this.dataAplicacao = dataAplicacao;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getTipoAplicacao() {
        return tipoAplicacao;
    }

    public void setTipoAplicacao(String tipoAplicacao) {
        this.tipoAplicacao = tipoAplicacao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
