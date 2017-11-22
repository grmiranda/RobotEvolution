/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Evolve;

/**
 *
 * @author gabriel reis miranda
 */
public class MainController {
    
    private Evolve evolve;
    
    public MainController(){
        this.evolve = Evolve.getInstance();
    }
    
    public void setConexoes(String[] dados) {
        this.evolve.setConnections(dados);
    }
    
    public void setEvolveConfig(int robots, int generations, int repDataNumber, int range, String sceneController){
        this.evolve.setEvolveConfig(robots, generations, repDataNumber, range, sceneController);
    }
    
}