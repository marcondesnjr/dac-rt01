/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.marcondesnjr.dac.atividade.managedbean;

import io.github.marcondesnjr.dac.atividade.entidades.Banda;
import io.github.marcondesnjr.dac.atividade.repository.BandaRepository;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
@Named
public class BandaConverter implements Converter{
    
    @Inject
    private BandaRepository bandaRepository;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return bandaRepository.read(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value.toString();
    }

    public BandaRepository getBandaRepository() {
        return bandaRepository;
    }

    public void setBandaRepository(BandaRepository bandaRepository) {
        this.bandaRepository = bandaRepository;
    }
    
    
    
}
