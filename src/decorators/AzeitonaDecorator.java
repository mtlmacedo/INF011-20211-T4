/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decorators;

import interfaces.PizzaComponent;
import interfaces.PizzaDecorator;

/**
 *
 * @author sandroandrade
 */
public class AzeitonaDecorator extends PizzaDecorator {
    public AzeitonaDecorator(PizzaComponent decorated) {
       this.decorated = decorated;
    }
    public String preparar() {
        
        return decorated.preparar() + "\n" + " + azeitona";
    }    
}
