/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.marcondesnjr.dac.atividade.repository;

import io.github.marcondesnjr.dac.atividade.entidades.Album;
import io.github.marcondesnjr.dac.atividade.entidades.Banda;
import io.github.marcondesnjr.dac.atividade.entidades.Estilo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class AlbumRepository {

    private QueryRunner queryRunner;
    @Inject
    private DataSource dataSource;
    @Inject
    private BandaRepository bandaRepository;

    @PostConstruct
    private void init() {
        queryRunner = new QueryRunner(dataSource);
    }

    public void create(Album album) {
        String sql = "INSERT INTO ALBUM(nome, banda, estilo, anoDeLancamento) VALUES (?,?,?,?)";
        try {
            queryRunner.execute(sql, album.getNome(), album.getBanda().getNome(), album.getEstilo().name(), album.getAnoDeLancamento());
        } catch (SQLException ex) {
            Logger.getLogger(AlbumRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Album> readAll() {
        String sql = "SELECT * FROM ALBUM";
        try {
            return queryRunner.query(sql, getListResultHandler());
        } catch (SQLException ex) {
            Logger.getLogger(AlbumRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void remove(String nome) {
        String sql = "DELETE FROM ALBUM WHERE nome = ?";
        try {
            queryRunner.execute(sql, nome);
        } catch (SQLException ex) {
            Logger.getLogger(BandaRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ResultSetHandler<List<Album>> getListResultHandler() {
        ResultSetHandler<List<Album>> rsh = new ResultSetHandler<List<Album>>() {
            @Override
            public List<Album> handle(ResultSet rs) throws SQLException {
                ArrayList<Album> albuns = new ArrayList<>();
                while (rs.next()) {
                    String nome = rs.getString("nome");
                    Banda banda = bandaRepository.read(rs.getString("banda"));
                    Estilo estilo = Estilo.valueOf(rs.getString("estilo"));
                    LocalDate ano = rs.getDate("anoDeLancamento").toLocalDate();
                    Album album = new Album(nome, estilo, banda, ano);
                    albuns.add(album);
                }
                return albuns;
            }
        };
        return rsh;
    }

}
