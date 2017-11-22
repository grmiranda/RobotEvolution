package model;

import java.util.ArrayList;

/**
 * 
 * @author gabriel reis miranda
 */
public class Evolve {
    
    //SINGLETON instance
    private static Evolve instance = new Evolve();
    
    //Ambient variables (infra)
    private ArrayList<Connection> connections;
    
    //Evolve configuration variables
    private int maxRobots; // max robots number in each generation 
    private int maxGeneration; // max generation number to be simulated
    private int repDataNumber; // number of data nodes in representation
    private String sceneController; // scene object name who the control script is attached 
    private int initRange; // int number to determine the range values to random init population data (0 -> range) 
    
    
    private Evolve(){}
    public static Evolve getInstance(){
        if(instance == null){
            instance = new Evolve();
        }
        return instance;
    }
    
    public void setConnections (String [] dados){
        this.connections = new ArrayList<>();
        // add all recived data to connection array
        for (int i = 0; i < dados.length; i++){
            String[] aux = dados[i].split(";");
            this.connections.add(new Connection(aux[0], Integer.parseInt(aux[1])));
        }
    }
    
    public void setEvolveConfig (int robots, int generations, int repDataNumber, int range, String sceneController){
        this.maxRobots = robots;
        this.maxGeneration = generations;
        this.repDataNumber = repDataNumber;
        this.sceneController = sceneController;
        this.initRange = range;
    }

    
}
