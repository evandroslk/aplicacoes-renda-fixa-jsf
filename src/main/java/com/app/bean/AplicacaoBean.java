package com.app.bean;

import com.app.model.AplicacaoRendaFixa;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class AplicacaoBean implements Serializable {

    private AplicacaoRendaFixa aplicacao;
    private List<AplicacaoRendaFixa> aplicacoes;
    private AplicacaoRendaFixa aplicacaoSelecionada;

    @PostConstruct
    public void init() {
        aplicacao = new AplicacaoRendaFixa();
        aplicacoes = new ArrayList<>();
        carregarAplicacoes();
    }

    private void carregarAplicacoes() {
    }

    public void salvar() {
        if (aplicacao.getNomeAplicacao() == null || aplicacao.getNomeAplicacao().trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Nome da aplicação é obrigatório"));
            return;
        }

        if (aplicacao.getPercentualRetorno() != null && aplicacao.getValorInvestido() != null) {
            BigDecimal retorno = aplicacao.getValorInvestido()
                .multiply(aplicacao.getPercentualRetorno())
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
            aplicacao.setValorRetorno(retorno);
        }

        if (aplicacao.getId() == null) {
            aplicacao.setId(System.currentTimeMillis());
            aplicacoes.add(aplicacao);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Aplicação cadastrada com sucesso"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Aplicação atualizada com sucesso"));
        }

        aplicacao = new AplicacaoRendaFixa();
    }

    public void editar(AplicacaoRendaFixa app) {
        this.aplicacao = app;
    }

    public void excluir(AplicacaoRendaFixa app) {
        aplicacoes.remove(app);
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Aplicação excluída"));
    }

    public void novaAplicacao() {
        aplicacao = new AplicacaoRendaFixa();
    }

    public BigDecimal getTotalInvestido() {
        return aplicacoes.stream()
            .map(AplicacaoRendaFixa::getValorInvestido)
            .filter(v -> v != null)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalRetorno() {
        return aplicacoes.stream()
            .map(AplicacaoRendaFixa::getValorRetorno)
            .filter(v -> v != null)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public AplicacaoRendaFixa getAplicacao() {
        return aplicacao;
    }

    public void setAplicacao(AplicacaoRendaFixa aplicacao) {
        this.aplicacao = aplicacao;
    }

    public List<AplicacaoRendaFixa> getAplicacoes() {
        return aplicacoes;
    }

    public void setAplicacoes(List<AplicacaoRendaFixa> aplicacoes) {
        this.aplicacoes = aplicacoes;
    }

    public AplicacaoRendaFixa getAplicacaoSelecionada() {
        return aplicacaoSelecionada;
    }

    public void setAplicacaoSelecionada(AplicacaoRendaFixa aplicacaoSelecionada) {
        this.aplicacaoSelecionada = aplicacaoSelecionada;
    }
}
