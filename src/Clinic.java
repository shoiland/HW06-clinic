import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Scanner;

public class Clinic {
    private File patientFile;
    private int day;

    //Constructors
    public Clinic(File file){
        this(file.getName());

    }
    public Clinic(String fileName) {
        this.patientFile = new File(fileName);
    }

    //Methods
    //Todo: Need to fix this to include logic from below
    public String nextDay(File f) throws FileNotFoundException {
        Scanner fileScan = new Scanner(f);
        String output = null;
        while (fileScan.hasNextLine()) {
            String data = fileScan.next();
            output += data;
        }
        return output;
    }

    public String nextDay(String fileName) throws FileNotFoundException {
        //Reading appointments
        String[] appointmentInfo = null;
        File fileInput = new File("src/" + fileName);
        Scanner fileScan = new Scanner(fileInput);
        Scanner userInput = new Scanner(System.in);
        String allAppointments = "";
        //Increment Day
        this.day += 1;
        while (fileScan.hasNextLine()) {
            String data = fileScan.next();
            appointmentInfo = data.split(",");
            String name = appointmentInfo[0];
            String petType = appointmentInfo[1];
            double count = Double.parseDouble(appointmentInfo[2]);
            String entryTime = appointmentInfo[3];
            //Getting user input
            //Do not catch the exception in your code! The caller of the method should handle the exception.
            if (!Objects.equals(petType, "Cat") && !Objects.equals(petType, "Dog")){
                throw new InvalidPetException();
            }
            //Getting Health from User
            System.out.println("Consultation for " + name + " the " + petType + " at " + entryTime + ".\nWhat is the health of " + name + "?");
            double health = 0.0;
            boolean healthValid = false;
            while (!healthValid){
                try {
                    health = userInput.nextDouble();
                    healthValid = true;
                } catch (Exception e){
                    System.out.println("Please enter a number");
                    userInput.nextLine();
                }
            }

            //Getting Pain from User
            System.out.println("On a scale of 1 to 10, how much pain is " + name + " in right now?");
            int painLevel = 0;
            boolean painLevelValid = false;
            while (!painLevelValid){
                try {
                    painLevel = userInput.nextInt();
                    painLevelValid = true;
                } catch (Exception e){
                    System.out.println("Please enter a number");
                    userInput.nextLine();
                }
            }

            //Treat Time
            int treatMin;
            String exitTime;

            if (petType.equals("Cat")) {
                Cat cat = new Cat(name, health, painLevel, (int) count);
                cat.speak();
                treatMin = cat.treat();
                exitTime = addTime(entryTime, treatMin);
                allAppointments += name + "," + petType + "," + count + "," + "Day " + this.day + "," + entryTime + "," + exitTime + "," + health + "," + painLevel + "\n";
            } else {
                Dog dog = new Dog(name, health, painLevel, count);
                dog.speak();
                treatMin = dog.treat();
                exitTime = addTime(entryTime, treatMin);
                allAppointments += name + "," + petType + "," + count + "," + "Day " + this.day + "," + entryTime + "," + exitTime + "," + health + "," + painLevel + "\n";
            }
        }
        return allAppointments;
    }

    public boolean addToFile(String patientInfo){
        File fileIn = new File("src/Patients.csv");
        PrintWriter filePrint = null;
        String currentPatientInfo = "";
        boolean success;
        try {
            Scanner fileScan = new Scanner(fileIn);
            //Read and write what is currently in the file
            while (fileScan.hasNextLine()){
                String currentLineString = fileScan.nextLine();
                String[] currentLine = currentLineString.split(",");
                String patientName = currentLine[0];
                String newPatientName = patientInfo.split(",")[0];
                if (patientName.equals(newPatientName)){
                    String appointmentDay = currentLine[3];
                    String appointmentTimeIn = currentLine[4];
                    String appointmentEndTime = currentLine[5];
                    String health = currentLine[6];
                    String pain = currentLine[7];
                    currentPatientInfo += currentLineString + "," + appointmentDay + "," + appointmentTimeIn + "," + appointmentEndTime + "," + health + "," + pain + "," + "\n";
                }
                currentPatientInfo += currentLineString + "\n";
            }
            filePrint = new PrintWriter(fileIn);
            //Printing the old info with the new info
            filePrint.println(currentPatientInfo + patientInfo);
            success = true;
            filePrint.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
            success = false;
        }
        return success;

    }

    private String addTime(String timeIn, int TreatmentTime){
        int hoursTimeIn = Integer.parseInt(timeIn.substring(0, 2)); //18
        int minTimeIn = Integer.parseInt(timeIn.substring(2, 4)); //35
        int totalTreatMin = TreatmentTime + minTimeIn; //80 min
        int totalTreatHours = totalTreatMin / 60; //1 hr
        int outHours = hoursTimeIn + totalTreatHours;
        int outMin = totalTreatMin % 60; //20 min
        String output = String.valueOf(outHours);
        output += String.valueOf(outMin);
        return output;
    }
}
