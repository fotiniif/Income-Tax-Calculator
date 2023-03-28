package incometaxcalculator.data.management;

public class SingleTaxpayer extends Taxpayer {

  public SingleTaxpayer(String fullname, int taxRegistrationNumber, float income) {
    super(fullname, taxRegistrationNumber, income);
    singleIncomes[0] = 24680;
    singleIncomes[1] = 81080;
    singleIncomes[2] = 90000;
    singleIncomes[3] = 152540;
    singleTax[0] = 0;
    singleTax[1] = 1320.38;
    singleTax[2] = 5296.58;
    singleTax[3] = 5996.80;
    singleTax[4] = 10906.19;
    singleTaxPercentage[0] = 5.35;
    singleTaxPercentage[1] = 7.05;
    singleTaxPercentage[2] = 7.85;
    singleTaxPercentage[3] = 7.85;
    singleTaxPercentage[4] = 9.85;
  }
}