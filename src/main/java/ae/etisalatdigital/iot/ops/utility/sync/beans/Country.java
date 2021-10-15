/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.beans;

import java.util.Locale;

/**
 *
 * @author appadmin
 */
public class Country {
    int index;
    Locale locale;
    boolean isAvailable;
    String name;

    public Country() {
    }

    public Country(int index, Locale locale) {
        this.index = index;
        this.locale = locale;
    }

    public Country(int index, Locale locale, boolean isAvailable) {
        this.index = index;
        this.locale = locale;
        this.isAvailable = isAvailable;
    }
    
    
    
    public int getIndex() {
        return index;
    }

    public Locale getLocale() {
        return locale;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public String getName() {
        return name;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public void setName(String name) {
        this.name = name;
    }
    
   
    
}
