package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;

public class TXTInfoWriter extends FileWriter {
  
  private String[] generateFileData = {"Name: ", "AFM: ","Status: ","Income: ","Receipts:"};
  private String[] generateFileData2 = {"", "", "", "", ""};
  private String[] taxpayerReceipts = {"Receipt ID: ", "Date: ", "Kind: ", "Amount: ", "Company: ", "Country; ","City: ", "Street: ","Number: "};  
  private String[] taxpayerReceipts2 = {"", "", "", "", "", "", "", "", ""};
  
  public void generateFile(String path, int taxRegistrationNumber) throws IOException {

    PrintWriter outputStream = new PrintWriter(
        new java.io.FileWriter(taxRegistrationNumber + "_INFO.txt"));
    writeFile(generateFileData, taxRegistrationNumber, generateFileData2, outputStream);
    generateTaxpayerReceipts(taxRegistrationNumber, outputStream);
    outputStream.close();
  }

  private void generateTaxpayerReceipts(int taxRegistrationNumber, PrintWriter outputStream) {
    writeTaxpayerReceipts(taxRegistrationNumber, taxpayerReceipts, taxpayerReceipts2, outputStream);
  }

}