package Server;

import InventoryPackage.Invoice;
import InventoryPackage.InvoiceHandler;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InvoiceServer extends UnicastRemoteObject implements InvoiceInterface{

    public InvoiceServer() throws RemoteException {
        super();
    }

    @Override
    public List<Invoice> GetInvoices() throws RemoteException {
        return InvoiceHandler.GetInvoices();
    }

    @Override
    public boolean AddInvoice(Invoice newInvoice) throws RemoteException {
        return InvoiceHandler.AddInvoice(newInvoice);
    }

    @Override
    public Invoice GenerateInvoice(HashMap<String, ArrayList<String>> soldItemList, double total, LocalDateTime createDate) throws RemoteException {
        return new Invoice(soldItemList, total, createDate);
    }

}
