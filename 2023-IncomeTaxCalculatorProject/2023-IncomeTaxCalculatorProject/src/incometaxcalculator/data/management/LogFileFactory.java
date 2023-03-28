package incometaxcalculator.data.management;

import java.io.IOException;

import incometaxcalculator.data.io.TXTLogWriter;
import incometaxcalculator.data.io.XMLLogWriter;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.data.io.FileWriter;

public class LogFileFactory {

  public FileWriter generateLogFile(String fileFormat) throws IOException, WrongFileFormatException{
    FileWriter writer;
    if (fileFormat.equals("txt")) {
      writer = new TXTLogWriter();
    } else if (fileFormat.equals("xml")) {
      writer = new XMLLogWriter();
    } else {
      throw new WrongFileFormatException();
    }
    return writer;
  }
}