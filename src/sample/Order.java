package sample.addtionalClasses;

import javafx.scene.control.Alert;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Order {
    private String fio;
    private String telephone;
    private String email;
    private String order;
    private String measures;
    private String state;
    private String cost;

    public Order(String fio, String telephone, String email, String order, String measures, String state, String cost) {
        this.fio = fio;
        this.telephone = telephone;
        this.email = email;
        this.order = order;
        this.measures = measures;
        this.state = state;
        this.cost = cost;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getMeasures() {
        return measures;
    }

    public void setMeasures(String measures) {
        this.measures = measures;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    //метод для записи новых заказов в файл Orders.txt
     public void  addNewOrder()  {
        File file=new File("Orders.txt");
        FileWriter writer=null;
        try {
            writer =new FileWriter(file,true);//запи
        } catch (IOException e) {
            System.out.println("Файл Orders.txt не найден при вызове метода addNewOrder ");
            e.printStackTrace();
        }//запись в файл
        try {
            writer.write(fio+"|"+telephone+"|"+email+"|"+
                    order+"|"+measures+"|"+state+"|"+cost+"\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Сохранено");
         alert.setHeaderText("Данные сохранены");
         alert.setContentText("Данные заказа успешно сохранены");

         alert.showAndWait();
    }


    //метод для чтения файла заказов и записи сток в массив
    private void ordersFileread(String path){
        File file = new File(path);
        FileReader reader = null;
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            System.out.println("Файл "+path+" не найден при вызове метода orderTableFillIn " + e);
            e.printStackTrace();
        }
        BufferedReader bread = new BufferedReader(reader);
        String line = null;
        try {
            line = bread.readLine();
        } catch (IOException e) {
            System.out.println("Файл material.txt не найден при вызове метода matTableFillIn " + e);
            e.printStackTrace();
            ArrayList lineArray=new ArrayList();
            int i=0;
            while (line != null) {
                line.trim();
                lineArray.add(line);

            }






}
