package incometaxcalculator.data.management;

import java.util.HashMap;

import incometaxcalculator.exceptions.WrongReceiptKindException;

public abstract class Taxpayer {

  protected final String fullname;
  protected final int taxRegistrationNumber;
  protected final float income;
  private float amountPerReceiptsKind[] = new float[5];
  private int totalReceiptsGathered = 0;
  private HashMap<Integer, Receipt> receiptHashMap = new HashMap<Integer, Receipt>(0);
  
  protected int headOfHouseholdIncomes[] = new int[4];
  protected double headOfHouseholdTax[] = new double[5];
  protected double headOfHouseholdTaxPercentage[] = new double[5];
  protected int marriedFillingJointlyIncomes[] = new int[4];
  protected double marriedFillingJointlyTax[] = new double[5];
  protected double marriedFillingJointlyTaxPercentage[] = new double[5];
  protected int marriedFillingSeperatelyIncomes[] = new int[4];
  protected double marriedFillingSeperatelyTax[] = new double[5];
  protected double marriedFillingSeperatelyTaxPercentage[] = new double[5];
  protected int singleIncomes[] = new int[4];
  protected double singleTax[] = new double[5];
  protected double singleTaxPercentage[] = new double[5];
  protected String names[] = {"Entertainment", "Basic", "Travel", "Health", "Other"};
  protected double taxPercentages[][] = {headOfHouseholdTaxPercentage, marriedFillingJointlyTaxPercentage, marriedFillingSeperatelyTaxPercentage, singleTaxPercentage};
  protected double taxes[][] = {headOfHouseholdTax, marriedFillingJointlyTax, marriedFillingSeperatelyTax, singleTax};
  protected int incomes[][] = {headOfHouseholdIncomes, marriedFillingJointlyIncomes, marriedFillingSeperatelyIncomes, singleIncomes};

  public double calculateBasicTax() {
    int pointer = 0;
    for (int i=0; i<taxPercentages.length; i++) {
      if (taxPercentages[i][0] != 0) {
        pointer = i;
        break;
      }
    }
    if (income < incomes[pointer][0]) {
      return taxPercentages[pointer][0] * income;
    } else if (income < incomes[pointer][1]) {
      return taxes[pointer][1] + taxPercentages[pointer][1] * (income - incomes[pointer][0]);
    } else if (income < incomes[pointer][2]) {
      return taxes[pointer][2] + taxPercentages[pointer][2] * (income - incomes[pointer][1]);
    } else if (income < incomes[pointer][3]) {
      return taxes[pointer][3] + taxPercentages[pointer][3] * (income - incomes[pointer][2]);
    } else {
      return taxes[pointer][4] + taxPercentages[pointer][4] * (income - incomes[pointer][3]);
    }
  }

  protected Taxpayer(String fullname, int taxRegistrationNumber, float income) {
    this.fullname = fullname;
    this.taxRegistrationNumber = taxRegistrationNumber;
    this.income = income;
  }

  public void addReceipt(Receipt receipt) throws WrongReceiptKindException {
    for(int i=0; i<amountPerReceiptsKind.length; i++) {
      if(receipt.getKind().equals(names[i])) {
        amountPerReceiptsKind[i] += receipt.getAmount();
        break;
      }
      else if(i==amountPerReceiptsKind.length-1) {
        throw new WrongReceiptKindException();
      }
    }
    receiptHashMap.put(receipt.getId(), receipt);
    totalReceiptsGathered++;
  }

  public void removeReceipt(int receiptId) throws WrongReceiptKindException {
    Receipt receipt = receiptHashMap.get(receiptId);
    for(int i=0; i<amountPerReceiptsKind.length; i++) {
      if(receipt.getKind().equals(names[i])) {
        amountPerReceiptsKind[i] -= receipt.getAmount();
        break;
      }
      else if(i==amountPerReceiptsKind.length-1) {
        throw new WrongReceiptKindException();
      }
    }
    totalReceiptsGathered--;
    receiptHashMap.remove(receiptId);
  }

  public String getFullname() {
    return fullname;
  }

  public int getTaxRegistrationNumber() {
    return taxRegistrationNumber;
  }

  public float getIncome() {
    return income;
  }

  public HashMap<Integer, Receipt> getReceiptHashMap() {
    return receiptHashMap;
  }

  public double getVariationTaxOnReceipts() {
    float totalAmountOfReceipts = getTotalAmountOfReceipts();
    double amountOfReceipts[] = {0.2 * income, 0.4 * income, 0.6 * income};
    double variation[] = {0.08, 0.04, -0.15};
    for(int i=0; i<amountOfReceipts.length; i++) {
      if(totalAmountOfReceipts < amountOfReceipts[i]) {
        return calculateBasicTax() * variation[i];
      }
      else if(i == amountOfReceipts.length-1) {
        return calculateBasicTax() * -0.3;
      }
    }
    return 0;
  }

  private float getTotalAmountOfReceipts() {
    int sum = 0;
    for (int i = 0; i < 5; i++) {
      sum += amountPerReceiptsKind[i];
    }
    return sum;
  }

  public int getTotalReceiptsGathered() {
    return totalReceiptsGathered;
  }

  public float getAmountOfReceiptKind(short kind) {
    return amountPerReceiptsKind[kind];
  }

  public double getTotalTax() {
    return calculateBasicTax() + getVariationTaxOnReceipts();
  }

  public double getBasicTax() {
    return calculateBasicTax();
  }
}