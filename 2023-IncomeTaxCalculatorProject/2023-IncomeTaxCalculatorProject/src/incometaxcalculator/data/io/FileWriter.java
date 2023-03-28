package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;

import incometaxcalculator.data.management.Address;
import incometaxcalculator.data.management.Receipt;
import incometaxcalculator.data.management.TaxpayerManager;

public abstract class FileWriter {

  private static final short ENTERTAINMENT = 0;
  private static final short BASIC = 1;
  private static final short TRAVEL = 2;
  private static final short HEALTH = 3;
  private static final short OTHER = 4;
  
  private static final short[] receiptKinds = {ENTERTAINMENT, BASIC, TRAVEL, HEALTH, OTHER};

  
  public abstract void generateFile(String path, int taxRegistrationNumber) throws IOException;

  public void writeFile(String[] FileData, int taxRegistrationNumber, String[] FileData2, PrintWriter outputStream) throws IOException {
    TaxpayerManager manager = new TaxpayerManager();
    String[] taxpayerInfo = {manager.getTaxpayerName(taxRegistrationNumber), Integer.toString(taxRegistrationNumber), manager.getTaxpayerStatus(taxRegistrationNumber),manager.getTaxpayerIncome(taxRegistrationNumber)};
    
    for(int i=0; i<FileData.length; i++){
      if(i == FileData.length-1) {
        outputStream.println();
        outputStream.println(FileData[i]);
      }
      else {
        outputStream.println(FileData[i] + taxpayerInfo[i] + FileData2[i]);
      }
    }
    outputStream.println();
  }
  
  public void writeTaxpayerReceipts(int taxRegistrationNumber, String[] taxpayerReceipts, String[] taxpayerReceipts2, PrintWriter outputStream) {
    TaxpayerManager manager = new TaxpayerManager();
    HashMap<Integer, Receipt> receiptsHashMap = manager.getReceiptHashMap(taxRegistrationNumber);
    Iterator<HashMap.Entry<Integer, Receipt>> iterator = receiptsHashMap.entrySet().iterator();
    
    while (iterator.hasNext()) {
      HashMap.Entry<Integer, Receipt> entry = iterator.next();
      Receipt receipt = entry.getValue();
      Address address = receipt.getCompany().getAddress();
      String[] taxpayerReceiptsInfo = {Integer.toString(receipt.getId()), receipt.getIssueDate(), receipt.getKind(), Float.toString(receipt.getAmount()), receipt.getCompany().getName(), address.getCountry(), address.getCity(), address.getStreet(), Integer.toString(address.getNumber()) }; 
      for (int i=0; i<taxpayerReceipts.length; i++) {
        outputStream.println(taxpayerReceipts[i] + taxpayerReceiptsInfo[i] + taxpayerReceipts2[i]);
      }
      outputStream.println();
    }
  }
  
  public void generateFileData(int taxRegistrationNumber, String[] fileData, String[] fileData2, PrintWriter outputStream) {
    TaxpayerManager manager = new TaxpayerManager();
    
    String[] taxpayerInfo = {manager.getTaxpayerName(taxRegistrationNumber), Integer.toString(taxRegistrationNumber), manager.getTaxpayerIncome(taxRegistrationNumber), Double.toString(manager.getTaxpayerBasicTax(taxRegistrationNumber)), 
        Double.toString(manager.getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber)), Double.toString(manager.getTaxpayerTotalTax(taxRegistrationNumber)), Double.toString(manager.getTaxpayerTotalReceiptsGathered(taxRegistrationNumber)), 
        Double.toString(manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, ENTERTAINMENT))};
    
    for (int i=0; i<4; i++) {
      outputStream.println(fileData[i] + taxpayerInfo[i] + fileData2[i]);
      System.out.println(fileData[i] + taxpayerInfo[i] + fileData2[i]);
    }
    if (Double.parseDouble(taxpayerInfo[4]) > 0) {
      outputStream.println(fileData[4] + taxpayerInfo[4] + fileData2[4]);
      System.out.println(fileData[4] + taxpayerInfo[4] + fileData2[4]);
    }else{
      outputStream.println(fileData[5] + taxpayerInfo[4] + fileData2[5]);
      System.out.println(fileData[5] + taxpayerInfo[4] + fileData2[5]);
    }
    for (int i=6; i<8; i++) {
      outputStream.println(fileData[i] + taxpayerInfo[i-1] + fileData2[i]);
      System.out.println(fileData[i] + taxpayerInfo[i-1] + fileData2[i]);
    }
    for (int i=8; i<fileData.length; i++) {
      outputStream.println(fileData[i] + manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, receiptKinds[i-8]) + fileData2[i]);
      System.out.println(fileData[i] + manager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, receiptKinds[i-8]) + fileData2[i]);
    }
  }
}