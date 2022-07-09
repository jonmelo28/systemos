/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mag.classe;

import java.util.Date;

/**
 *
 * @author Jonatha
 */
public class Pagamento {
    private int codOs;
    private int codCliente;
    private String cliente;
    private String dataOs;
    private String dataPagamento;
    private String formaPagamento;
    private String valor;

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
    

    public int getCodOs() {
        return codOs;
    }

    public void setCodOs(int codOs) {
        this.codOs = codOs;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDataOs() {
        return dataOs;
    }

    public void setDataOs(String dataOs) {
        this.dataOs = dataOs;
    }

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
    
    
}
