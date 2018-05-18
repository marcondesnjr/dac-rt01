/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.marcondesnjr.dac.atividade.repository;

import io.github.marcondesnjr.dac.atividade.entidades.Banda;
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
public class IntegranteRepository {
    
    private QueryRunner queryRunner;
    @Inject
    private DataSource dataSource;
    
    @PostConstruct
    private void init(){
        queryRunner = new QueryRunner(dataSource);
    }
    
    public void create(Banda b){
        String sql= "INSERT INTO INTEGRANTE(nome,banda) VALUES(?,?,?)";
        for (String integrante : b.getIntegrates()) {
            try {
                queryRunner.execute(sql,integrante,b.getNome());
            } catch (SQLException ex) {
                Logger.getLogger(IntegranteRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public List<String> read(String bandaNome){
        String sql = "SELECT * FROM INTEGRANTE WHERE banda = ?";
        ResultSetHandler<List<String>> rsh = new ResultSetHandler<List<String>>() {
            @Override
            public List<String> handle(ResultSet rs) throws SQLException {
                List<String> list = new ArrayList<>();
                while(rs.next()){
                    String integrante = rs.getString("nome");
                    list.add(integrante);
                }
                return list;
            }
        };
        try {
            return queryRunner.query(sql, rsh,bandaNome);
        } catch (SQLException ex) {
            Logger.getLogger(IntegranteRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
