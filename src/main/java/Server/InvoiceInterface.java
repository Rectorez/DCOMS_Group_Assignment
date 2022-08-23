package Server;

import InventoryPackage.Invoice;
import InventoryPackage.InvoiceHandler;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface InvoiceInterface extends Remote {

    //Getter
    List<Invoice> GetInvoices() throws RemoteException;

    //Manipulation
    boolean AddInvoice(Invoice newInvoice) throws RemoteException;

    //ObjectGeneration
    Invoice GenerateInvoice(HashMap<String, ArrayList<String>> soldItemList, double total, LocalDateTime createDate) throws  RemoteException;
}
