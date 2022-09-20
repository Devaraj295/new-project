import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class EmployeeController {
    int trainerId = 1;
    int traineeId = 1;
    Scanner scanner = new Scanner(System.in);
    TraineeService traineeService = new TraineeService();
    TrainerService trainerService = new TrainerService();

    public static void main(String[] args) {
        int choise = 0;       
        do {
            EmployeeController controller = new EmployeeController();
            Scanner scanInput = new Scanner(System.in);              
            System.out.println("\n" + "Choose the option" + "\n" + "1.Add Trainee Details"
                              + "\n" + "2.Add Trainer Details" + "\n" + "3.Exit" + "\n");
            try {
                choise = scanInput.nextInt();
                
                switch(choise) {
                    case 1:
                        controller.traineeOperation();
                        break;

                    case 2:
                        controller.trainerOperation();
                        break;

                    case 3:
                        System.out.println("Exist");
                        break;

                    default:
                        System.out.println("Enter valid option");
                }
            } catch (Exception e) {
                System.out.println("Enter Number only");
            }
        } while (choise != 3);
        
    }

    public void traineeOperation() {
        int traineeUserOption = 0;
        do {
            try {
                System.out.println("\n" + "Choose the option" + "\n"
                                  + "1.Add Trainee Details" + "\n"
                                  + "2.Update Trainee Details" + "\n"
                                  + "3.Delete Trainee Details" + "\n"
                                  + "4.Read Trainee Detail" + "\n" 
                                  + "5.Exit Trainee Details" + "\n");
                scanner = new Scanner(System.in);
                traineeUserOption = scanner.nextInt();               
                switch (traineeUserOption) {
                    case 1:
                        addTraineeDetails();
                        break;

                    case 2:
                        updateTraineeDetails();
                        break;

                    case 3:
                        deleteTraineeDetail();
                        break;

                    case 4:
                        readTraineeDetails();
                        break;

                    case 5:
                        System.out.println("Existed Trainee Detail");
                        break;

                    default :
                        System.out.println("Enter the correct option");
                }
            } catch (Exception e) {
                    System.out.println("Enter Valid option only");
            }
        } while (traineeUserOption != 5);   
        
    }

    public void addTraineeDetails() {
        Trainee trainee = new Trainee();
        TraineeDAO traineeDAO = new TraineeDAO();
        System.out.println("Trainee ID :" + (traineeId));
        trainee.setId(traineeId++);
        trainee.setName(addTraineeName());
        trainee.setAge(addTraineeAge());
        trainee.setDateOfBirth(addTraineeDateOfBirth());
        trainee.setPhoneNumber(addTraineePhoneNumber());
        traineeService.addTraineeDetails(trainee);
    }

    public String addTraineeName() {
        String name;
        boolean isValidTraineeName = false;
        do {
            System.out.println("Enter the Trainee Name :");
            name = scanner.next();
            isValidTraineeName = ValidationUtil.isValidInput
                                 (ValidationUtil.namePattern, name);
            if (!(isValidTraineeName)) {
                System.out.println("Enter Character only");
                isValidTraineeName = false;
            }
        } while (!(isValidTraineeName));
        return name;
    }

    public int addTraineeAge() {
        boolean isValidTraineeAge = false;
        int age;
        do {
            System.out.print("Enter the Trainee Age :");
            age = Integer.parseInt(scanner.next());
            isValidTraineeAge = ValidationUtil.isValidInput
                                (ValidationUtil.phoneNumberPattern,
                                Integer.toString(age));
            if (!(isValidTraineeAge)) {
                System.out.println("Enter the valid Age");
                isValidTraineeAge = false;
            } else  {
                try { 
                    if ((age > 18) && (age < 60)) {
                        isValidTraineeAge = true;
                        return age;
                    } else {
                        throw new InvalidInputException("Invalid Age");
                    }
                } catch (InvalidInputException e) {
                    System.out.println("your are not Eligible for work " + e);
                    isValidTraineeAge = false;
                }
            }
        } while (!(isValidTraineeAge));
        return age;
    }

    public String addTraineeDateOfBirth() {
        boolean check;
        Trainee trainee = new Trainee();
        String dateOfBirth = " ";
        boolean isValidTraineeDateOfBirth = true;
        do {
            try {
                System.out.print("Enter the Trainee Date of Birth :");
                dateOfBirth = scanner.next();
                LocalDate date = LocalDate.parse(dateOfBirth);
                trainee.setDateOfBirth(dateOfBirth);
                check = false;
            } catch (Exception e) {
                System.out.println("Enter the valid Date of Birth");
                check = true;
            }
        } while (!(isValidTraineeDateOfBirth));
        return (dateOfBirth.toString());
    }

    public String addTraineePhoneNumber() {
        boolean isValidTraineePhoneNumber;
        String phoneNumber;
        do {
            System.out.print("Enter the Trainee phone number :");
            phoneNumber = scanner.next();
            isValidTraineePhoneNumber = ValidationUtil.isValidInput
                                        (ValidationUtil.phoneNumberPattern, 
                                        phoneNumber);
            if (!(isValidTraineePhoneNumber)) {
                System.out.println("Enter the valid Trainee PhoneNumber");
            }
        } while (!(isValidTraineePhoneNumber));
        return (phoneNumber + "\n");
    }

    public void updateTraineeDetails() {
        System.out.println("Enter Trainee ID for update:");
        int updateTrainee = scanner.nextInt();
        if (traineeService.checkTraineeEmpty()) {
            System.out.println("Trainee List is Empty");
        } else {
            traineeService.checkIndexById(updateTrainee);
            System.out.println("1.Update Trainee Name" + "\n" 
                              + "2.Update Trainee Age" + "\n"
                              + "3.Update Trainee Date of Birth" + "\n"
                              + "4.Update Trainee Phone Number" + "\n");
            int modifyTraineeChoise = scanner.nextInt();
            switch (modifyTraineeChoise) {
                case 1:
                    updateTraineeName();
                    break;

                case 2:
                    updateTraineeAge();
                    break;

                case 3:
                    updateTraineeDateOfBirth();
                    break;

                case 4:
                    updateTraineePhoneNumber();
                    break;
            }
        }
    }

    public void updateTraineeName() {
        Trainee trainee = new Trainee();
        String name;
        boolean isValidTraineeName;
        do {
            System.out.print("Enter Trainee Name :");
            name = scanner.next();
            isValidTraineeName = ValidationUtil.isValidInput
                                 (ValidationUtil.namePattern, name);
            if(!(isValidTraineeName)) {
                System.out.println("Enter characters only");
            } else {
                trainee.setName(name);
            }
        } while (!(isValidTraineeName));
        traineeService.addTraineeDetails(trainee);
    }

    public void updateTraineeAge() {
        Trainee trainee = new Trainee();
        int age;
        boolean isValidTraineeAge; 
        do {
            System.out.print("Enter Trainee Age :");
            age = scanner.nextInt();
            isValidTraineeAge = ValidationUtil.isValidInput
                                (ValidationUtil.namePattern, 
                                Integer.toString(age));
            if (!(isValidTraineeAge)) {
                System.out.println("Enter the valid age");
            } else {
                trainee.setAge(age);
            }
        } while (!(isValidTraineeAge));
        traineeService.addTraineeDetails(trainee);
    }

    public void updateTraineeDateOfBirth() {
        Trainee trainee = new Trainee();
        boolean check = false;
        String dateOfBirth;
        do {
            try {
                System.out.print("Enter Trainee Date of Birth : ");
                dateOfBirth = scanner.next();          
                LocalDate born = LocalDate.parse(dateOfBirth);
                trainee.setDateOfBirth(dateOfBirth);
                check = false;
            } catch (Exception e) {
                System.out.println("Enter the valide Date of Birth");
                check = true;
            }
        } while (check);
        traineeService.addTraineeDetails(trainee);
    }

    public void updateTraineePhoneNumber() {
        Trainee trainee = new Trainee();
        String phoneNumber;
        boolean isValidTraineePhoneNumber;
        do {
            System.out.print("Enter Trainee Phone Number :");
            phoneNumber = scanner.next();
            isValidTraineePhoneNumber = ValidationUtil.isValidInput
                                        (ValidationUtil.phoneNumberPattern,
                                        phoneNumber);
            if (!(isValidTraineePhoneNumber)) {              
                System.out.print("Enter the valide PhoneNumber");      
            } else {
                trainee.setPhoneNumber(phoneNumber);
            }
        } while (!(isValidTraineePhoneNumber));
        traineeService.addTraineeDetails(trainee);
    }

    public void readTraineeDetails() {
        List<Trainee> traineeDetail = traineeService.getAllDetails();
        if(traineeDetail.isEmpty()) {
            System.out.println("Trainee List is Empty");
        } else {
            for (Trainee traineeList : traineeDetail) {
                System.out.println(traineeList.toString());
            }
        }
    }

    public void deleteTraineeDetail() {
        if(traineeService.getAllDetails().isEmpty()) {
            System.out.println("Trainee List is Empty");
        } else {
            System.out.print("Enter the trainee ID :");
            int delete = scanner.nextInt();
            traineeService.deleteId(delete);
            System.out.println("Trainee ID" + delete + "Deleted Successfully");
        }
    }
   
    public void trainerOperation() {
        Trainer trainer = new Trainer();
        int trainerUserOption = 0;
        do {
            try {
                System.out.println("choose the option" + "\n"
                                  + "1.Add Trainer Details"
                                  + "\n" + "2.Update Trainer Details" + "\n"
                                  + "3.Delete Trainer Details" + "\n"
                                  + "4.Read Trainer Detail" + "\n" 
                                  + "5.Assign Trainees to Trainer" + "\n"
                                  + "6.Exit" + "\n");
                scanner = new Scanner(System.in);
                trainerUserOption = scanner.nextInt();
                switch (trainerUserOption) {
                    case 1:
                        addTrainerDetails();
                        break;

                    case 2:
                        updateTrainerDetails();
                        break;

                    case 3:
                        deleteTrainerDetail();
                        break;

                    case 4:
                        readTrainerDetails();
                        break;

                    case 5:
                        assignTrainee();
                        break;

                    case 6:
                        System.out.println("Exit from Trainer operation");
                        break;

                    default:
                        System.out.println("Enter valid option");
                }
            } catch (Exception e) {
                System.out.println("Enter numbers only" + "\n");
            }
        } while (trainerUserOption != 6);       
    }

    public void addTrainerDetails() {
        Trainer trainer = new Trainer();
        TrainerDAO trainerDAO = new TrainerDAO();
        System.out.println("Trainer ID : " + trainerId);
        trainer.setId(trainerId++);
        trainer.setName(addTrainerName());
        trainer.setAge(addTrainerAge());
        trainer.setDateOfBirth(addTrainerDateOfBirth());
        trainer.setPhoneNumber(addTrainerPhoneNumber());
        trainer.setTrainee(assignTrainee());
        trainerService.addTrainerDetail(trainer);
    }

    public String addTrainerName() {
        boolean isValidTrainerName;
        String name;
        do {
            System.out.print("Enter the Trainer Name :");
            name = scanner.next();
            isValidTrainerName = ValidationUtil.isValidInput
                                 (ValidationUtil.namePattern, name);
            if (!(isValidTrainerName)) {
                System.out.println("Enter Character only");
            } 
        } while (!(isValidTrainerName));
        return name;
    }

    public int addTrainerAge() {
        boolean isValidTrainerAge;
        int age;
        do {
            System.out.print("Enter the Trainer Age :");
            age = scanner.nextInt();
            isValidTrainerAge = ValidationUtil.isValidInput
                               (ValidationUtil.agePattern, 
                               Integer.toString(age));
            if (!(isValidTrainerAge)) {
                System.out.println("Enter the valid Trainer Age");
            }
        } while (!(isValidTrainerAge));
        return age;
    }

    public String addTrainerDateOfBirth() {
        boolean check;
        Trainer trainer = new Trainer();
        String dateOfBirth = "";
        boolean isValidDateOfBirth = true;
        do {
            try {
                System.out.print("Enter the Trainer Date of Birth :");
                dateOfBirth = scanner.next();
                LocalDate born = LocalDate.parse(dateOfBirth);
                trainer.setDateOfBirth(dateOfBirth);
                check = false;  
            } catch (Exception e) {
                System.out.println("Enter the Valid Date : ");
                check = true;
            }
        } while (check);
        return (dateOfBirth.toString());
    }

    public String addTrainerPhoneNumber() {
        boolean isValidTrainerPhoneNumber;
        String phoneNumber;
        Trainer trainer = new Trainer();
        do {
            System.out.print("Enter the Trainer phone number :");
            phoneNumber = scanner.next();
            isValidTrainerPhoneNumber = ValidationUtil.isValidInput
                                        (ValidationUtil.phoneNumberPattern, 
                                        phoneNumber);
            if (!(isValidTrainerPhoneNumber)) {   
                System.out.println("Enter the valid PhoneNumber");
            } 
        } while (!(isValidTrainerPhoneNumber));
        return phoneNumber;
    }
    
    public List<Trainee> assignTrainee() {
        int assignTraineeChoise;
        int traineeID;
        int traineeIndex = 0;
        TraineeDAO traineeDAO = new TraineeDAO();
        List<Trainee> traineeAssignList = new ArrayList<Trainee>();
        do {
            System.out.println("1.Assign Exisist Trainee" + "\n"
                              + "2.Create Trainee and Assign" + "\n"
                              + "3.Exit ");
            assignTraineeChoise = scanner.nextInt();
            switch (assignTraineeChoise) {
                case 1:
                   assignExisistTrainee();
                   break;
                case 2:
                    addTraineeDetails();
                    traineeIndex = traineeDAO.traineeList.size()-1;
                    traineeAssignList.add(traineeService.getId(traineeIndex));
                    break;

                default:
                    System.out.print("Enter valid option");
            } 
       } while (assignTraineeChoise != 3);
       return traineeAssignList;
    }

    public void assignExisistTrainee() {
        if (traineeService.checkTraineeEmpty()) {
            System.out.println("Trainee List is Empty");
        } else {           
            System.out.println("Available Trainee List ID's :");
            for (Trainee trainee : traineeService.getAllDetails()) {
                System.out.println(trainee.getId());
            }
            System.out.println("Choose the Trainee ID to Assign");
            traineeID = scanner.nextInt();
            traineeAssignList.add(traineeService.getId(traineeId));
        }
    }
                   
      
    public void updateTrainerDetails() {
        System.out.print("Enter the Trainer ID to Update");
        int modifyTrainerChoise = scanner.nextInt();
        if(trainerService.checkTrainerId(modifyTrainerChoise)) {
            System.out.println("Trainer List is Empty");
        } else {
            trainerService.checkIndex(modifyTrainerChoise);          
            System.out.println("1.Update Trainer Name" + "\n" 
                              + "2.Update Trainer Age" + "\n"
                              + "3.Update Trainer Date of Birth" + "\n"
                              + "4.update Trainer Phone Number" + "\n");

            switch (modifyTrainerChoise) {
                case 1:
                    updateTrainerName();
                    break;

                case 2:
                    updateTrainerAge();
                    break;

                case 3:
                    updateTrainerDateOfBirth();
                    break;

                case 4:
                    updateTrainerPhoneNumber();
                    break;

                default :
                    System.out.println("Enter valid option");
            }
        }
    }

    public void updateTrainerName() {
        Trainer trainer = new Trainer();
        TrainerDAO trainerDAO = new TrainerDAO();
        String name;
        boolean isValidTrainerName ;
        do {
            System.out.print("Enter Trainer Name :");
            name = scanner.next();
            isValidTrainerName = ValidationUtil.isValidInput
                                 (ValidationUtil.namePattern, name);
            if(!(isValidTrainerName)) {
                System.out.println("Enter characters Only");
            } else {
                trainer.setName(name);
            }
        } while (!(isValidTrainerName));
        trainerService.addTrainerDetail(trainer);
    }

    public void updateTrainerAge() {
        Trainer trainer = new Trainer();
        TrainerDAO trainerDAO = new TrainerDAO();
        int age;
        boolean isValidTrainerAge; 
        do {
            System.out.print("Enter Trainer Age :");
            age = scanner.nextInt();
            isValidTrainerAge = ValidationUtil.isValidInput
                                (ValidationUtil.agePattern, 
                                Integer.toString(age));
            if (!(isValidTrainerAge)) {
                System.out.println("Enter the valid age");
            } else {
                trainer.setAge(age);
            }
        } while (!(isValidTrainerAge));
        trainerService.addTrainerDetail(trainer);
    }

    public void updateTrainerDateOfBirth() {
        Trainer trainer = new Trainer();
        TrainerDAO trainerDAO = new TrainerDAO();
        boolean check = false;
        String dateOfBirth;
        do {
            try {
                System.out.print("Enter Trainer Date of Birth : ");
                dateOfBirth = scanner.next();
                LocalDate born = LocalDate.parse(dateOfBirth);
                trainer.setDateOfBirth(dateOfBirth);
                check = false;
            } catch (Exception e) {
               System.out.println("Enter valid Trainer Date of Birth");
                check = false;
            }
        } while (check);
        trainerService.addTrainerDetail(trainer);
    }

    public void updateTrainerPhoneNumber() {
        Trainer trainer = new Trainer();
        TrainerDAO trainerDAO = new TrainerDAO();
        String phoneNumber;
        boolean isValidTrainerPhoneNumber;
        do {
            System.out.print("Enter Trainer Phone Number :");
            phoneNumber = scanner.next();
            isValidTrainerPhoneNumber = ValidationUtil.isValidInput
                                        (ValidationUtil.phoneNumberPattern,
                                        phoneNumber);
            if (!(isValidTrainerPhoneNumber)) {
                System.out.println("Enter valid Trainer phone number");
            } else {
                trainer.setPhoneNumber(phoneNumber);
            }
        } while (!(isValidTrainerPhoneNumber));
        trainerService.addTrainerDetail(trainer);
    }

    public void readTrainerDetails() {
        TrainerDAO trainerDAO = new TrainerDAO();
        List<Trainer> trainerDetails = trainerService.getTrainerDetails();
        if(trainerDetails.isEmpty()) {
            System.out.println("Traineer List is Empty");
        } else {
            for (Trainer trainer : trainerDetails) {
                System.out.println(trainer);
                List<Trainee> traineeList = trainer.getTrainee();
                if (!(traineeList.isEmpty())) {
                    for(Trainee trainee : traineeList) {
                        System.out.println(trainee.toString());
                    }
                }
            }
        }
    }

    public void deleteTrainerDetail() {
        TrainerDAO trainerDAO = new TrainerDAO();
        if(trainerService.getTrainerDetails().isEmpty()) {
            System.out.println(" Trainer List is Empty");
        } else {
            System.out.println("Enter the trainer ID :");
            Integer deleteTrainer = scanner.nextInt();
            trainerService.deleteTrainer(deleteTrainer);
            System.out.println("Trainer ID " + deleteTrainer
                              + "Deleted Sucessfully");
        }
    }
}
        








    
              


                       