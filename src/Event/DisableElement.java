/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event;

import java.awt.Component;
import java.awt.Container;
import javax.swing.JPanel;

/**
 *
 * @author Jayant Kumar Yadav
 */
public class DisableElement {
    
    JPanel p;
    
    public DisableElement(JPanel p){
        this.p = p;
    }
    
    public void enableComponents(Container container, boolean enable) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            component.setEnabled(enable);
            if (component instanceof Container) {
                enableComponents((Container)component, enable);
            }
        }
    }
}
