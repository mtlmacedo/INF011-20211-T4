/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author sandroandrade
 */
public abstract class PizzaDecorator implements PizzaComponent {
    public final void setDecorated(PizzaComponent decorated) {
        this.decorated = decorated;        
    }
    protected PizzaComponent decorated;
}
