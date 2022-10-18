import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class BestGymEver {

    private final String nameOfGym = "Best Gym Ever";

    public String readSsn(String inString) {
        inString = inString.substring(0, inString.indexOf(','));
        return inString;
    }

    public String readName(String inString) {
        inString = inString.substring(inString.indexOf(',') + 2);
        return inString;
    }

    public boolean activeYearCard(String dateToCheck) {
        LocalDate date = LocalDate.now();
        LocalDate dateFromString = LocalDate.parse(dateToCheck);
        Period period = Period.between(dateFromString, date);
        int yearBetween = period.getYears();
        if (yearBetween == 0) {
            return true;
        } else {
            return false;
        }
    }

    public String inputDialog () {
        String input = JOptionPane.showInputDialog(null, "Type in your full name or social security number:",
                nameOfGym, JOptionPane.QUESTION_MESSAGE);

        if (input == null) {
            JOptionPane.showMessageDialog(null, "Program aborted.", nameOfGym,
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
        if (input.equals("")) {
            JOptionPane.showMessageDialog(null, "Incorrect entry.", nameOfGym,
                    JOptionPane.ERROR_MESSAGE);
            inputDialog();
        }
        return input;
    }

    public void mainProgram() {

            while (true) {
                try (Scanner inStream = new Scanner(new File("src/Customers.txt"));
                     PrintWriter outStream = new PrintWriter(new BufferedWriter
                             (new FileWriter("src/CustomerHistory.txt", true)))) {

                        String input = inputDialog();

                    Boolean foundCustomer = false;
                    while (!foundCustomer) {
                        String firstLine = inStream.nextLine();
                        String secondLine = inStream.nextLine();

                        if (input.trim().equalsIgnoreCase(readName(firstLine)) || input.trim().equals(readSsn(firstLine))) {
                            JOptionPane.showMessageDialog(null, "Welcome " + readName(firstLine),
                                    nameOfGym, JOptionPane.INFORMATION_MESSAGE);
                            if (activeYearCard(secondLine)) {
                                JOptionPane.showMessageDialog(null, "Active annual pass.",
                                        nameOfGym, JOptionPane.INFORMATION_MESSAGE);
                                outStream.print(firstLine + ", "
                                        + LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd, HH:mm")) + "\n");
                                foundCustomer = true;
                            } else {
                                JOptionPane.showMessageDialog(null, "Your annual pass has expired.",
                                        nameOfGym, JOptionPane.WARNING_MESSAGE);
                                foundCustomer = true;
                            }
                        } else if (!inStream.hasNextLine()) {
                            JOptionPane.showMessageDialog(null, "Customer not found.", nameOfGym,
                                    JOptionPane.WARNING_MESSAGE);
                            foundCustomer = true;
                        }
                    }

                } catch (FileNotFoundException e) {
                    System.out.println("Cannot find file.");
                    System.exit(0);
                } catch (IOException e) {
                    System.out.println("Problem with reading from file.");
                    System.exit(0);
                } catch (Exception e) {
                    System.out.println("Something went wrong.");
                    System.exit(0);
                }
            }
        }

    public static void main(String[] args) {
        BestGymEver test = new BestGymEver();
        test.mainProgram();
    }
}


