/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import interfaces.PizzaComponent;

/**
 *
 * @author sandroandrade
 */
public class PizzaSimples implements PizzaComponent {
    @Override
    public void preparar() {
        System.out.println("Preparando massa + molho + queijo");
    }
}
