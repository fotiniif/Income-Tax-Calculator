package incometaxcalculator.data.management;

import java.io.IOException;

import incometaxcalculator.data.io.FileReader;
import incometaxcalculator.data.io.TXTFileReader;
import incometaxcalculator.data.io.XMLFileReader;
import incometaxcalculator.exceptions.WrongFileEndingException;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

public class FileReaderFactory {
  
  public FileReader generateFileReaders(String fileName) throws NumberFormatException, IOException, WrongFileFormatException, WrongFileEndingException,
    WrongTaxpayerStatusException, WrongReceiptKindException, WrongReceiptDateException {
    FileReader reader;
    
    String ending[] = fileName.split("\\.");
    for(int i=0; i<ending.length; i++) {
      System.out.println(ending[i]);
    }
    if (ending[1].equals("txt")) {
      reader = new TXTFileReader();
    } else if (ending[1].equals("xml")) {
      reader = new XMLFileReader();
    } else {
      throw new WrongFileEndingException();
    }
    return reader;
  }
}