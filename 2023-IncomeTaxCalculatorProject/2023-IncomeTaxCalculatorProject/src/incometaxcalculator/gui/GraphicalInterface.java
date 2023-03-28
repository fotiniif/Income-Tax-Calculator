package incometaxcalculator.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import incometaxcalculator.data.management.TaxpayerManager;
import incometaxcalculator.exceptions.WrongFileEndingException;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

public class GraphicalInterface extends JFrame {

  private JPanel contentPane;
  private TaxpayerManager taxpayerManager = new TaxpayerManager();
  private JTextField txtTaxRegistrationNumber;

  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          GraphicalInterface frame = new GraphicalInterface();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  public GraphicalInterface() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 500);
    contentPane = new JPanel();
    contentPane.setBackground(new Color(204, 204, 204));
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
        | UnsupportedLookAndFeelException e2) {
      e2.printStackTrace();
    }

    JTextPane textPane = new JTextPane();
    textPane.setEditable(false);
    textPane.setBackground(new Color(153, 204, 204));
    textPane.setBounds(0, 21, 433, 441);

    DefaultListModel<String> taxRegisterNumberModel = new DefaultListModel<String>();

    JList<String> taxRegisterNumberList = new JList<String>(taxRegisterNumberModel);
    taxRegisterNumberList.setBackground(new Color(84, 85, 84));
    taxRegisterNumberList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    taxRegisterNumberList.setSelectedIndex(0);
    taxRegisterNumberList.setVisibleRowCount(3);

    JScrollPane taxRegisterNumberListScrollPane = new JScrollPane(taxRegisterNumberList);
    taxRegisterNumberListScrollPane.setSize(300, 300);
    taxRegisterNumberListScrollPane.setLocation(70, 100);
    contentPane.add(taxRegisterNumberListScrollPane);

    
    JButton btnLoadTaxpayer = new JButton("Load Taxpayer");
    btnLoadTaxpayer.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JFileChooser fileLoaderPanel = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileLoaderPanel.setDialogTitle("Select a data file");
        fileLoaderPanel.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("XML files", "xml");
        FileNameExtensionFilter filter2 = new FileNameExtensionFilter("TXT files", "txt");
        fileLoaderPanel.addChoosableFileFilter(filter);
        fileLoaderPanel.addChoosableFileFilter(filter2);
        
        String pathString = "";
        
        fileLoaderPanel.setAcceptAllFileFilterUsed(true);

        int returnValue = fileLoaderPanel.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
          pathString = fileLoaderPanel.getSelectedFile().getPath();
          System.out.println("### " + pathString);
          
        }
        
        if (returnValue == 0) {
          
          String[] tempName = pathString.split("\\\\");
          String[] tempName2 = tempName[tempName.length - 1].split("_");
          String taxRegistrationNumber = tempName2[0];
          while (taxRegistrationNumber.length() != 9 && returnValue == 0) {
            
            JOptionPane.showMessageDialog(null,
                "The tax  registration number must have 9 digit.\n" + " Try again.");
            returnValue = fileLoaderPanel.showOpenDialog(null);
            pathString = fileLoaderPanel.getSelectedFile().getPath();
            System.out.println("### " + pathString);
            tempName = pathString.split("\\\\");
            tempName2 = tempName[tempName.length - 1].split("_");
            taxRegistrationNumber = tempName2[0];
          }
          if (returnValue == 0) {
            int trn = 0;
            String taxRegistrationNumberFile = pathString;
            try {
              trn = Integer.parseInt(taxRegistrationNumber);
              if (taxpayerManager.containsTaxpayer(trn)) {
                JOptionPane.showMessageDialog(null, "This taxpayer is already loaded.");
              } else {
                taxpayerManager.loadTaxpayer(taxRegistrationNumberFile);
                taxRegisterNumberModel.addElement(taxRegistrationNumber);
              }
            } catch (NumberFormatException e1) {
              JOptionPane.showMessageDialog(null,
                  "The tax registration number must have only digits.");
            } catch (IOException e1) {
              JOptionPane.showMessageDialog(null, "The file doesn't exists.");
            } catch (WrongFileFormatException e1) {
              JOptionPane.showMessageDialog(null, "Please check your file format and try again.");
            } catch (WrongFileEndingException e1) {
              JOptionPane.showMessageDialog(null, "Please check your file ending and try again.");
            } catch (WrongTaxpayerStatusException e1) {
              JOptionPane.showMessageDialog(null, "Please check taxpayer's status and try again.");
            } catch (WrongReceiptKindException e1) {
              JOptionPane.showMessageDialog(null, "Please check receipts kind and try again.");
            } catch (WrongReceiptDateException e1) {
              JOptionPane.showMessageDialog(null,
                  "Please make sure your date is " + "DD/MM/YYYY and try again.");
            }
          }

        }
      }
    });
    btnLoadTaxpayer.setBounds(0, 0, 146, 23);
    contentPane.add(btnLoadTaxpayer);

    JButton btnSelectTaxpayer = new JButton("Select Taxpayer");
    taxRegisterNumberList.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent evt) {
          JList list = (JList)evt.getSource();
          if (evt.getClickCount() == 2) {
              int index = list.locationToIndex(evt.getPoint());
              int taxRegistrationNumber = Integer.parseInt(taxRegisterNumberList.getSelectedValue());
              System.out.println(taxRegistrationNumber);
              TaxpayerData taxpayerData = new TaxpayerData(taxRegistrationNumber, taxpayerManager);
              taxpayerData.setVisible(true);
          }
      }
    });
    btnSelectTaxpayer.setBounds(147, 0, 139, 23);
    contentPane.add(btnSelectTaxpayer);

    JButton btnDeleteTaxpayer = new JButton("Delete Taxpayer");
    btnDeleteTaxpayer.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        if (taxpayerManager.containsTaxpayer()) {
          String trn = JOptionPane.showInputDialog(null,
              "Give the tax registration number that you want to delete: ");
          int taxRegistrationNumber;
          try {
            taxRegistrationNumber = Integer.parseInt(trn);
            if (taxpayerManager.containsTaxpayer(taxRegistrationNumber)) {
              taxpayerManager.removeTaxpayer(taxRegistrationNumber);
              taxRegisterNumberModel.removeElement(trn);
            }
          } catch (NumberFormatException e) {

          }
        } else {
          JOptionPane.showMessageDialog(null,
              "There isn't any taxpayer loaded. Please load one first.");
        }
      }
    });
    btnDeleteTaxpayer.setBounds(287, 0, 146, 23);
    contentPane.add(btnDeleteTaxpayer);

    txtTaxRegistrationNumber = new JTextField();
    txtTaxRegistrationNumber.setEditable(false);
    txtTaxRegistrationNumber.setBackground(new Color(153, 204, 204));
    txtTaxRegistrationNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
    txtTaxRegistrationNumber.setText("Tax Registration Number:");
    txtTaxRegistrationNumber.setBounds(70, 80, 300, 20);
    contentPane.add(txtTaxRegistrationNumber);
    txtTaxRegistrationNumber.setColumns(10);

  }
}