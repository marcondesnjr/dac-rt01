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
public class BandaRepository {
    
    private QueryRunner queryRunner;
    @Inject
    private DataSource dataSource;
    @Inject
    private IntegranteRepository integranteRepository;
    
    @PostConstruct
    private void init(){
        queryRunner = new QueryRunner(dataSource);
    }
    
    public void create(Banda b){       
        String sql = "INSERT INTO BANDA(nome,localDeOrigem) VALUES (?,?)";
        integranteRepository.create(b);
        try {
            queryRunner.execute(sql, b.getNome(), b.getLocalDeOrigem());
        } catch (SQLException ex) {
            Logger.getLogger(BandaRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Banda> readAll(){
        String sql = "SELECT * FROM BANDA";
        ResultSetHandler<List<Banda>> rsh = new ResultSetHandler() {
            @Override
            public List<Banda> handle(ResultSet rs) throws SQLException {
                List<Banda> bandas = new ArrayList<>();
                while(rs.next()){
                    String nome = rs.getString("nome");
                    String localDeOrigem = rs.getString("localDeOrigem");
                    List<String> integrantes = integranteRepository.read(nome);
                    Banda b = new Banda();
                    b.setNome(nome);
                    b.setLocalDeOrigem(localDeOrigem);
                    b.setIntegrates(integrantes);
                    bandas.add(b);
                }
                return bandas;
            }
        };
        try {
            return queryRunner.query(sql, rsh);
        } catch (SQLException ex) {
            Logger.getLogger(BandaRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Banda read(String nome){
        String sql = "SELECT * FROM BANDA WHERE nome = ?";
        ResultSetHandler<Banda> rsh = new ResultSetHandler() {
            @Override
            public Banda handle(ResultSet rs) throws SQLException {
                if(rs.next()){
                    String nome = rs.getString("nome");
                    String localDeOrigem = rs.getString("localDeOrigem");
                    List<String> integrantes = integranteRepository.read(nome);
                    Banda b = new Banda();
                    b.setNome(nome);
                    b.setLocalDeOrigem(localDeOrigem);
                    b.setIntegrates(integrantes);
                    return b;
                }
                return null;
            }
        };
        try {
            return queryRunner.query(sql, rsh, nome);
        } catch (SQLException ex) {
            Logger.getLogger(BandaRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    
    
}
