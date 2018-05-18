/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.marcondesnjr.dac.atividade.managedbean;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import org.primefaces.convert.DateTimeConverter;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
@Named
public class LocalDateConverter implements Converter {

    private DateTimeConverter converter;

    public LocalDateConverter() {
        converter = new DateTimeConverter();
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Locale brasil = new Locale("pt", "BR");
        return LocalDate.parse(value, DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(brasil));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        LocalDate ld = (LocalDate) value;
        return ld.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

}
