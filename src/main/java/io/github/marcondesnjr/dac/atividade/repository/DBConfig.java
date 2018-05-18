/*
 * 
 */
package io.github.marcondesnjr.dac.atividade.repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
@ApplicationScoped
public class DBConfig {
    
    private Properties prop;
    
    @PostConstruct
    private void init(){
        prop = new Properties();
        try(InputStream is = this.getClass().getResourceAsStream("/db-config.properties")) {
            prop.load(is);
        } catch (IOException ex) {
            Logger.getLogger(DBConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getServerName(){
        return this.prop.getProperty("serverName");
    }
    
    public String getDataBaseName(){
        return this.prop.getProperty("dataBaseName");
    }
    
    public int getPort(){
        return Integer.parseInt(this.prop.getProperty("port"));
    }
    
    public String getUser(){
        return this.prop.getProperty("user");
    }
    
    public String getPassword(){
        return this.prop.getProperty("password");
    }
    
    public String getURI(){
        return this.prop.getProperty("uri");
    }
    
}
