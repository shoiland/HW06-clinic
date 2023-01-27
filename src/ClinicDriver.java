
import java.io.FileNotFoundException;

/**
 * Driver class to demonstrate a Clinic treating various patients
 */
public class ClinicDriver {

    public static void main(String[] args) {
        Clinic clinic = new Clinic("src/Patients.csv");
        String dayOneReport = "";
        try {
            dayOneReport = clinic.nextDay("data/Appointments.csv");
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
        System.out.println(dayOneReport);
        String[] dayOneAppointments = dayOneReport.split("\\n");
        for (String appointment : dayOneAppointments) {
            if (!clinic.addToFile(appointment)) {
                System.out.println("Appointment could not be added to file!");
            }
        }
    }
}

//Issues

//Todo: Failed: addToFile doesn't modify repeat patient inform
//ation correctly expected:<...ay 1,1655,1700,0.4,4[,Day 2,0800,
//0900,0.6,3]> but was:<...ay 1,1655,1700,0.4,4[]>

//Todo: test_dog_equals_implementation Failed: null

//Todo: Failed: Your equals method does not call the Pet equal
//s method.

//Todo: Failed: System.in was given following values when call
//ing nextDay:
//    ImAString
//    .87
//    4
//    false
//    .9
//    12
//    .1342
//    RandomText
//    20
//    .2
//    1
//
//    And did not properly reprompt when given invalid input

//Todo: t was given the following values for System.in:
//    .5
//    5
//    .5
//    5
//    .5
//    5
//    .5
//    5
//
//    And the file contained the following contents:
//    Chloe,Cat,2,1430
//    Jim,Dog,3,1945
//    John,Cat,10,1637
//    Sally,Dog,3.7,1345


//Todo: Failed: Got java.util.NoSuchElementExceptioninvoking n
//extDay method on valid file.


