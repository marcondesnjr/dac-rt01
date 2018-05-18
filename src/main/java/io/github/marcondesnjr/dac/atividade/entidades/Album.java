/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.marcondesnjr.dac.atividade.entidades;

import java.time.LocalDate;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class Album {
    
    private String nome;
    private Estilo estilo; //Rock, Pop, Sertanejo
    private Banda banda;
    private LocalDate anoDeLancamento;

    public Album(String nome, Estilo estilo, Banda banda, LocalDate anoDeLancamento) {
        this.nome = nome;
        this.estilo = estilo;
        this.banda = banda;
        this.anoDeLancamento = anoDeLancamento;
    }

    public Album() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estilo getEstilo() {
        return estilo;
    }

    public void setEstilo(Estilo estilo) {
        this.estilo = estilo;
    }

    public Banda getBanda() {
        return banda;
    }

    public void setBanda(Banda banda) {
        this.banda = banda;
    }

    public LocalDate getAnoDeLancamento() {
        return anoDeLancamento;
    }

    public void setAnoDeLancamento(LocalDate anoDeLancamento) {
        this.anoDeLancamento = anoDeLancamento;
    }

    
    
    
}
