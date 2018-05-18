/*
 * 
 */
package io.github.marcondesnjr.dac.atividade.repository;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class RepositoryConnection {

    @Inject
    private DBConfig dbConfig;


    @Produces
    @ApplicationScoped
    public DataSource getDataSource() {
        
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RepositoryConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        String URI = dbConfig.getURI();

        ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(URI, dbConfig.getUser(), dbConfig.getPassword());
        PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, null);

        ObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnectionFactory);
        poolableConnectionFactory.setPool(connectionPool);

        PoolingDataSource<PoolableConnection> dataSource = new PoolingDataSource<>(connectionPool);

        return dataSource;

    }

}
