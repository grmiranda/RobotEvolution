/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author grmir
 */
public class TelaInicialController implements Initializable {

    @FXML
    private Button continuar;
    @FXML
    private Button importarArquivo;
    @FXML
    private TextField porta;
    @FXML
    private TextField ip;
    @FXML
    private TableView<Computer> table;
    @FXML
    private Button adicionar;
    @FXML
    private Button limpar;
    @FXML
    private TableColumn<Computer, String> colunaId;
    @FXML
    private TableColumn<Computer, String> colunaPorta;
    /**
     * Initializes the controller class.
     */
    private List<Computer> tableItens = new ArrayList<Computer>();
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colunaId.setCellValueFactory(new PropertyValueFactory<Computer, String>("ip"));
        colunaPorta.setCellValueFactory(new PropertyValueFactory<Computer, String>("porta"));
        ObservableList<Computer> itens = FXCollections.observableArrayList(tableItens);
        this.table.setItems(itens);
    }   

    @FXML
    private void continuar(ActionEvent event) {
        
    }

    @FXML
    private void importarArquivo(ActionEvent event) {
        // Variavies locais
        Stage seletorStage = new Stage(); // Scene secundária para o seletor de arquivos
        File arquivo;
        
        // Selecionar o arquivo para importação
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
        arquivo = fileChooser.showOpenDialog(seletorStage);
        
        //scanner para o arquivo
        try {
            Scanner scanner = new Scanner(arquivo);
            while (scanner.hasNext()) {
               String line = scanner.nextLine();
               String[] dado = line.split("-");
               tableItens.add(new Computer(dado[0], dado[1]));
            }
            scanner.close();
            ObservableList<Computer> itens = FXCollections.observableArrayList(tableItens);
            this.table.setItems(itens);
        } catch (FileNotFoundException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro na Importação");
            alert.setHeaderText("Erro na Importação");
            alert.setContentText("Ocorreu um erro durante a importação do arquivo");
            alert.showAndWait();
        }
        
    }

    @FXML
    private void adicionar(ActionEvent event) {
        if(!this.ip.getText().equals("") && !this.porta.getText().equals("")){
        tableItens.add(new Computer(this.ip.getText(), this.porta.getText()));
        ObservableList<Computer> itens = FXCollections.observableArrayList(tableItens);
        this.table.setItems(itens);
        }else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Verifique os campos");
            alert.setHeaderText("Verifique os campos");
            alert.setContentText("Preencha corretamente os campos");
            alert.showAndWait();
        }
        
    }

    @FXML
    private void limpar(ActionEvent event) {
        tableItens.clear(); //limpa a lista de maquinas 
        ObservableList<Computer> itens = FXCollections.observableArrayList(tableItens);
        this.table.setItems(itens);
    }
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    public static class Computer {
 
        private final SimpleStringProperty ip;
        private final SimpleStringProperty porta;
 
        private Computer(String ip, String porta) {
            this.ip = new SimpleStringProperty(ip);
            this.porta = new SimpleStringProperty(porta);
        }
 
        public String getIp() {
            return ip.get();
        }
 
        public void setIp(String ip) {
            this.ip.set(ip);
        }
 
        public String getPorta() {
            return porta.get();
        }
 
        public void setPorta(String porta) {
            this.porta.set(porta);
        }
    }
}
