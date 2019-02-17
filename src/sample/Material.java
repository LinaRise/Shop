package sample.addtionalClasses;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Material {
private String material;
private String color;
private double amount;
private String extra;

    public Material(String material, String color, double amount, String extra) {
        this.material = material;
        this.color = color;
        this.amount = amount;
        this.extra = extra;
    }

    public Material() {

    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @FXML//перезапись измененного файла материалов
    public static void rewriteMaterialCatalog(ObservableList<Material> materialCatalog) throws IOException {
        File file = new File("material.txt");
        FileWriter writer = new FileWriter(file, false);
        for (Material material : materialCatalog) {
            System.out.println(material.getMaterial() + "/" + material.getColor() + "/" + material.getAmount() + "/" + material.getExtra());
            writer.write(material.getMaterial() + "/" + material.getColor() + "/" + material.getAmount() + "/" + material.getExtra());
            writer.flush();
            writer.write("\r\n");

        }writer.close();
    }


//внесение новыз данных в файл
    public static void addMaterial(Material material) throws IOException {
        File file = new File("material.txt");
        FileWriter writer = new FileWriter(file, true);
        String text = material.getMaterial() + "/" + material.getColor() + "/" + material.getAmount() + "/" + material.getExtra();
        writer.write(text);
        writer.flush();
        writer.close();
    }

}
