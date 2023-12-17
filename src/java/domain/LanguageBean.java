/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author ADMIN
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

@ManagedBean
@SessionScoped
public class LanguageBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Locale> supportedLocales;

    public LanguageBean() {
        // Initialise la liste des langues prises en charge
        supportedLocales = new ArrayList<>();
        supportedLocales.add(new Locale("en", "EN"));
        supportedLocales.add(new Locale("es", "ES"));
        supportedLocales.add(new Locale("fr", "FR"));
        supportedLocales.add(new Locale("ar", "AR"));
    }


    // Getter pour la liste des langues prises en charge
    public List<Locale> getSupportedLocales() {
        return supportedLocales;
    }
    
    public void changeLanguage(ValueChangeEvent event) {
        
        
        String newLocaleValue = event.getNewValue().toString();        
        System.out.println("Selected Language: " + newLocaleValue);

        if (newLocaleValue != null) {
            FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(newLocaleValue));
        } else {
            System.out.println("WARNING: selectedLanguage is null!");
        }
    }

}
