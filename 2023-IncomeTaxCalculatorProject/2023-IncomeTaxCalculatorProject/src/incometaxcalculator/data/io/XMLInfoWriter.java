package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;

public class XMLInfoWriter extends FileWriter {
  
  private String[] FileData = {"<Name> ", "<AFM> ","<Status> ","<Income> ","<Receipts>"};
  private String[] FileData2 = {" </Name>", " </AFM>", " </Status>", " </Income>", ""};
  private String[] taxpayerReceipts = {"<ReceiptID> ", "<Date> ", "<Kind> ", "<Amount> ", "<Company> ", "<Country> ","<City> ", "<Street> ","<Number> "};  
  private String[] taxpayerReceipts2 = {" </ReceiptID> ", " </Date> ", " </Kind> ", " </Amount> ", " </Company> ", " </Country> "," </City> ", " </Street> "," </Number> "};  
  
  public void generateFile(String path, int taxRegistrationNumber) throws IOException {

    PrintWriter outputStream = new PrintWriter(
        new java.io.FileWriter(taxRegistrationNumber + "_INFO.xml"));
    writeFile(FileData, taxRegistrationNumber, FileData2, outputStream);
    generateTaxpayerReceipts(taxRegistrationNumber, outputStream);
    outputStream.close();
  }

  private void generateTaxpayerReceipts(int taxRegistrationNumber, PrintWriter outputStream) {   
    writeTaxpayerReceipts(taxRegistrationNumber, taxpayerReceipts, taxpayerReceipts2, outputStream);
  }
}