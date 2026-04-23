package com.pluralsight;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class StoreApp {

        // the key is the product id, the value is a product object
    public static HashMap<Integer, Product> inventory = new HashMap<Integer, Product>();

    public static void main(String[] args) {
        loadInventory();

        Scanner sc = new Scanner(System.in);
        System.out.print("What item # are you interested in? ");
        int searchId = sc.nextInt();

        Product matchedProduct = inventory.get(searchId);

        if (matchedProduct == null) {
            System.out.println("We don't carry that product");
            return;
        }else{
            System.out.printf("We carry %s (ID: %d) and the price is $%.2f.%n",
                    matchedProduct.getName(),
                    matchedProduct.getId(),
                    matchedProduct.getPrice());
        }
        sc.close();
    }
    public static void loadInventory(){
        String fileName = "inventory.csv";

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = bufferedReader.readLine()) != null) {


                String[] parts = line.split("\\|");

                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                double price = Double.parseDouble(parts[2].trim());


                // using the product ID as the map key
                inventory.put(id, new Product(id, name, price));
                inventory.put(3435, new Product(3434,"Large Trigger Clamps", 36.97 ));

            }
            bufferedReader.close();
            System.out.println("Inventory: " + inventory.size() + " products.\n");

            } catch (FileNotFoundException e){
            System.out.println("Error: " + fileName + " not found.");
            } catch (IOException e){
            System.out.println("Error reading file: " + e.getMessage());
            }catch(NumberFormatException e){
            System.out.println("Error parsing a line: " + e.getMessage());
        }
    }
}


