package sample;

import javafx.scene.control.Alert;

import java.io.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Order {
    private int  orderNum;
    private String fio;
    private String telephone;
    private String order;
    private String measures;
    private String state;
    private String cost;
    private String loginOfWorker;

    public Order(int orderNum,String fio, String telephone, String order, String measures, String state, String cost,String loginOfWorker) {
        this.orderNum=orderNum;
        this.fio = fio;
        this.telephone = telephone;
        this.order = order;
        this.measures = measures;
        this.state = state;
        this.cost = cost;
        this.loginOfWorker=loginOfWorker;
    }

    public Order(int orderNum, String fio, String telephone, String order, String measures, String state, String cost) {
        this.orderNum = orderNum;
        this.fio = fio;
        this.telephone = telephone;
        this.order = order;
        this.measures = measures;
        this.state = state;
        this.cost = cost;
    }


    public Order(String measures, String telephone) {
        this.telephone = telephone;
        this.measures = measures;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public Order() {
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

            writer.write(orderNum+"|"+fio+"|"+telephone+"|"+
                    order+"|"+measures+"|"+state+"|"+cost+"|"+loginOfWorker+"\r\n");
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

   static public int orderNum() throws IOException {
        String line ;
        String[] arrSplit = {null,null};

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("Orders.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while ((line = reader.readLine()) != null) {
                arrSplit = line.split("\\|", 2);
            }
        System.out.println(Arrays.toString(arrSplit));
            int number=1;
       try {
           if(arrSplit[0]!= null) {
                number = Integer.parseInt(arrSplit[0])+1;

           }
       }
        catch (NumberFormatException e)
       {
         number=1;
       }return number;
    }


    //метод для чтения файла заказов и записи сток в массив
    static ArrayList<String> ordersFileread(String path) throws IOException {
        File file = new File(path);
        FileReader reader = null;
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            System.out.println("Файл " + path + " не найден при вызове метода orderTableFillIn " + e);
            e.printStackTrace();
        }
        BufferedReader bread = new BufferedReader(reader);
        String line = null;
        ArrayList<String> lineArray = new ArrayList();
        while ((line = bread.readLine()) != null) {
            line.trim();
            lineArray.add(line);
        }reader.close();
        bread.close();
        return lineArray;
    }


    static String loginOfWorker(){
        BufferedReader reader= null;
        String loginOfWorker=null;
        try {
            reader = new BufferedReader(new FileReader("temp.txt"));
             loginOfWorker=reader.readLine();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loginOfWorker;
    }






}
