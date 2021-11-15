/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ae.etisalatdigital.iot.ops.utility.sync.beans;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author shamr
 */
@Named(value = "instTreeView")
@ViewScoped
public class InstallationSemanticsTreeView implements Serializable{
    private String name;
    private String description;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public InstallationSemanticsTreeView(String name, String description, String type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public InstallationSemanticsTreeView() {
    }

    @Override
    public String toString() {
        return name + " - " + description + " - " + type;
    }
}
