/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import decorators.AzeitonaDecorator;
import decorators.PresuntoDecorator;
import interfaces.PizzaComponent;

/**
 *
 * @author sandroandrade
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PizzaComponent d1 = new PresuntoDecorator(
                new AzeitonaDecorator(
                        new AzeitonaDecorator(
                            new PizzaSimples()
                        )
                )
        );
        d1.preparar();
    }
    
}
