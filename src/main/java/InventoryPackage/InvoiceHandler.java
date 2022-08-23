package InventoryPackage;

import java.io.*;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InvoiceHandler {
    private static List<Invoice> InvoiceList = new ArrayList<>();

    public static List<Invoice> GetInvoices() {
        return InvoiceList;
    }

    public static boolean AddInvoice(Invoice invoice) {
        if (GetInvoices().stream().anyMatch(i -> i.equals(invoice))) return false;
        InvoiceList.add(invoice);
        WriteInvoicesToFile();
        return true;
    }

    private static boolean WriteInvoicesToFile(){
        //Check dir exist
        String folderDir = Path.of(System.getProperty("user.dir"), "ServerDatabase").toString();
        File f = new File(folderDir);
        if(!f.exists()){
            //If not create
            f.mkdir();
            System.out.println("Created directory: " + folderDir);
        }
        String fileDir = Path.of(folderDir, "Invoices.ser").toString();
        try{
            FileOutputStream file = new FileOutputStream(fileDir);
            ObjectOutputStream oos = new ObjectOutputStream(file);
            oos.writeObject(InvoiceList);

            oos.close();
            file.close();

            return true;
        }
        catch (IOException e) {
            return false;
        }
    }

    private static boolean ReadInvoicesToList() {
        File target = Path.of(System.getProperty("user.dir"), "ServerDatabase", "Invoices.ser").toFile();
        if(target.exists()){
            try {
                FileInputStream file = new FileInputStream(target);
                ObjectInputStream obj = new ObjectInputStream(file);

                InvoiceList = (List<Invoice>) obj.readObject();
                return true;
            }
            catch (IOException | ClassNotFoundException e) {
                return false;
            }
        }
        else{
            System.out.println("Invoices.ser does not exist");
            return false;
        }
    }
}
