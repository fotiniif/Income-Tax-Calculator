package 
import org.junit.jupiter.api.Assertions;
import incometaxcalculator.data.management.TaxpayerManager;

class Test {

  @Test
  void testLoadTaxpayerReadTxt() {
    String trn = "123456789.txt";
    TaxpayerManager manager = new TaxpayerManager();
    manager.loadTaxpayer(trn);
    assertTrue(manager.containsTaxpayer(123456789));
  }

  @Test
  void testLoadTaxpayerReadXml() {
    String trn = "123456789.xml";
    TaxpayerManager manager = new TaxpayerManager();
    manager.loadTaxpayer(trn);
    assertTrue(manager.containsTaxpayer(123456789));
  }
  
  @Test
  void testLoadTaxpayerExists() {
    String trn = "123456789.txt";
    TaxpayerManager manager = new TaxpayerManager();
    manager.loadTaxpayer(trn);
    assertTrue(manager.containsTaxpayer(123456789)); 
    trn = "130456093.txt";
    manager = new TaxpayerManager();
    manager.loadTaxpayer(trn);
    assertTrue(manager.containsTaxpayer(130456093));
    assertTrue(manager.containsTaxpayer(123456789));
  }
  
 
}
