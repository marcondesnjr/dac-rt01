/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.marcondesnjr.dac.atividade.entidades;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class Banda {

    private String nome;
    private String localDeOrigem;
    private List<String> integrates;

    public Banda() {
    }

    public Banda(String nome, String localDeOrigem, List<String> integrates) {
        this.nome = nome;
        this.localDeOrigem = localDeOrigem;
        this.integrates = integrates;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalDeOrigem() {
        return localDeOrigem;
    }

    public void setLocalDeOrigem(String localDeOrigem) {
        this.localDeOrigem = localDeOrigem;
    }

    public List<String> getIntegrates() {
        return integrates;
    }

    public void setIntegrates(List<String> integrates) {
        this.integrates = integrates;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.nome);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Banda other = (Banda) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nome;
    }
    
    
    
}
