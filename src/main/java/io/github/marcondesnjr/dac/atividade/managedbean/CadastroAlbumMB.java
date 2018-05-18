/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.marcondesnjr.dac.atividade.managedbean;

import io.github.marcondesnjr.dac.atividade.entidades.Album;
import io.github.marcondesnjr.dac.atividade.entidades.Banda;
import io.github.marcondesnjr.dac.atividade.entidades.Estilo;
import io.github.marcondesnjr.dac.atividade.repository.AlbumRepository;
import io.github.marcondesnjr.dac.atividade.repository.BandaRepository;
import io.github.marcondesnjr.dac.atividade.view.AlbumTableView;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;
import javax.inject.Inject;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
@Named(value = "cadastroAlbumMB")
@RequestScoped
public class CadastroAlbumMB {
    
    private String nome;
    private Banda banda;
    private String estilo;
    private LocalDate ano;
    private Map<String,Banda> bandas;
    private Map<String,String> estilos;
    @Inject
    private BandaRepository bandaRepository;
    @Inject
    private AlbumRepository albumRepository;
    @ManagedProperty(value = "#{albumTableView}")
    private AlbumTableView atv;

    /**
     * Creates a new instance of CadastroAlbumMB
     */
    public CadastroAlbumMB() {
    }
    @PostConstruct
    private void init(){
        bandas = new HashMap<>();
        estilos = new HashMap<>();
        for(Banda b: bandaRepository.readAll()){
            bandas.put(b.getNome(), b);
        }
        for(Estilo e: Estilo.values()){
            estilos.put(e.name(), e.name());
        }
    }
    
    
    public void cadastrar(AlbumTableView update){
        Album al = new Album();
        al.setNome(nome);
        al.setBanda(banda);
        al.setEstilo(Estilo.valueOf(estilo));
        al.setAnoDeLancamento(ano);
        albumRepository.create(al);
        update.refreshAlbuns();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Banda getBanda() {
        return banda;
    }

    public void setBanda(Banda banda) {
        this.banda = banda;
    }


    public BandaRepository getBandaRepository() {
        return bandaRepository;
    }

    public void setBandaRepository(BandaRepository bandaRepository) {
        this.bandaRepository = bandaRepository;
    }

    public LocalDate getAno() {
        return ano;
    }

    public void setAno(LocalDate ano) {
        this.ano = ano;
    }

    

    public Map<String, Banda> getBandas() {
        return bandas;
    }

    public void setBandas(Map<String, Banda> bandas) {
        this.bandas = bandas;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public Map<String, String> getEstilos() {
        return estilos;
    }

    public void setEstilos(Map<String, String> estilos) {
        this.estilos = estilos;
    }

    

    
    
    
    
    
    
}
