import java.util.List;

public class Trainer {
    private String name;
    private Integer age;
    private String phoneNumber;
    private String dateOfBirth;
    private Integer id;
    private List<Trainee> traineeList;

    public void setTrainee(List<Trainee> traineeList) {
        this.traineeList = traineeList;
    }

    public List<Trainee> getTrainee() {
        return traineeList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    } 

    public Integer getId() {
        return id;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public String  getPhoneNumber() {
        return phoneNumber;
    }

   public void setPhoneNumber(String  phoneNumber) {
        this.phoneNumber = phoneNumber;
   }

   public String getDateOfBirth() {
        return dateOfBirth;
   }

   public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
   }

   public String  toString() {
        return ("Trainer ID :" + id + "\n" + "Trainer name :" + name + "\n"
               + "Trainer Age :" + age + "\n"
               + "Trainer Phone Number :" + phoneNumber + "\n"
               + "Trainer Date of Birth :" + dateOfBirth);
   }
}
 