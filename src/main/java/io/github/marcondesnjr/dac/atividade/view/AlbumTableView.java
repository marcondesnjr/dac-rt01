/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.marcondesnjr.dac.atividade.view;

import io.github.marcondesnjr.dac.atividade.entidades.Album;
import io.github.marcondesnjr.dac.atividade.repository.AlbumRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.inject.Inject;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
@Named(value = "albumTableView")
@RequestScoped
public class AlbumTableView {
    
    private List<Album> albuns;
    @Inject
    private AlbumRepository albumRepository;

    /**
     * Creates a new instance of AlbumTableView
     */
    public AlbumTableView() {
    }
    
    @PostConstruct
    private void init(){
        refreshAlbuns();
    }

    public void refreshAlbuns() {
        albuns = albumRepository.readAll();
    }
    
    public void deleteAlbum(String nome){
        albumRepository.remove(nome);
        refreshAlbuns();
    }

    public List<Album> getAlbuns() {
        return albuns;
    }

    public void setAlbuns(List<Album> albuns) {
        this.albuns = albuns;
    }
    
    
    
    
}
