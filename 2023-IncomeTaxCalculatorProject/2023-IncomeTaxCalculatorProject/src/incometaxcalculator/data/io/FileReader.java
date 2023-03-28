package incometaxcalculator.data.io;

import java.io.BufferedReader;
import java.io.IOException;

import incometaxcalculator.data.management.TaxpayerManager;
import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

public abstract class FileReader {

  protected abstract int checkForReceipt(String values[])
      throws NumberFormatException;

  protected abstract String getValueOfField(String fieldsLine) throws WrongFileFormatException;
  
  /**
   * 
   * @throws NumberFormatException 
   * @throws IOException xcvcxcvcxvxc
   * @throws WrongTaxpayerStatusException
   * @throws WrongFileFormatException
   * @throws WrongReceiptKindException
   * @throws WrongReceiptDateException
   */
  public void readFile(String fileName)
      throws NumberFormatException, IOException, WrongTaxpayerStatusException,
      WrongFileFormatException, WrongReceiptKindException, WrongReceiptDateException {

    BufferedReader inputStream = new BufferedReader(new java.io.FileReader(fileName));
    System.out.println(fileName);
    String fullname = getValueOfField(inputStream.readLine());
    int taxRegistrationNumber = Integer.parseInt(getValueOfField(inputStream.readLine()));
    String status = getValueOfField(inputStream.readLine());
    float income = Float.parseFloat(getValueOfField(inputStream.readLine()));
    createTaxpayer(fullname, taxRegistrationNumber, income, status);
    while (readReceipt(inputStream, taxRegistrationNumber));
  }

  protected boolean readReceipt(BufferedReader inputStream, int taxRegistrationNumber)
      throws WrongFileFormatException, IOException, WrongReceiptKindException,
      WrongReceiptDateException {

    int receiptId = checkReceiptId(inputStream);
    if(receiptId == -1) {
      return false;
    }
    String[] receiptFields = readValueOfField(inputStream);
    createReceipt(receiptId, receiptFields[0], Float.parseFloat(receiptFields[2]), receiptFields[1], receiptFields[3], receiptFields[4], receiptFields[5], receiptFields[6], Integer.parseInt(receiptFields[7]), taxRegistrationNumber);
    return true;
  }
  
  protected int checkReceiptId(BufferedReader inputStream)throws IOException {
    int receiptId = -1;
    String line;
    while (!isEmpty(line = inputStream.readLine())) {
      System.out.println(line);
      String values[] = line.split(" ", 3);
      if(values.length == 3) {
        if(values[1].equals("ID:") || values[0].equals("<ReceiptID>")) {
          receiptId = checkForReceipt(values);
          break;
        }
      }
    }
    if (receiptId < 0) {
      return -1;
    }
    return receiptId;
  }
  
  protected String[] readValueOfField(BufferedReader inputStream)throws IOException, WrongFileFormatException {
    String[] receiptFields = new String[8];
    for(int i=0; i<receiptFields.length; i++) {
      String line = inputStream.readLine();
      
      if (isEmpty(line)) {
        throw new WrongFileFormatException();
      }
      try {
        receiptFields[i] = getValueOfField(line);
      }
      catch (NullPointerException e) {
        throw new WrongFileFormatException();
      }
    }
    return receiptFields;
  }

  protected void createTaxpayer(String fullname, int taxRegistrationNumber, float income,
      String status) throws WrongTaxpayerStatusException {

    TaxpayerManager manager = new TaxpayerManager();
    manager.createTaxpayer(fullname, taxRegistrationNumber, status, income);
  }

  protected void createReceipt(int receiptId, String issueDate, float amount, String kind,
      String companyName, String country, String city, String street, int number,
      int taxRegistrationNumber) throws WrongReceiptKindException, WrongReceiptDateException {

    TaxpayerManager manager = new TaxpayerManager();
    manager.createReceipt(receiptId, issueDate, amount, kind, companyName, country, city, street,
        number, taxRegistrationNumber);
  }

  protected boolean isEmpty(String line) {
    if (line == null) {
      return true;
    } else {
      return false;
    }
  }

}