import java.util.ArrayList;
import java.util.List;

public class TraineeDAO {
    static List<Trainee> traineeList = new ArrayList<Trainee>();

    public void addTrainee(Trainee trainee) {
        traineeList.add(trainee);
    }

    public List<Trainee> getTraineeDetails() {
        return traineeList;
    }

   public void updateTrainee(int traineeIndex, Trainee trainee) {
        traineeList.set(traineeIndex, trainee);
    }

    public void deleteTrainee(int traineeIndex) {
        traineeList.remove(traineeIndex);
    }
}
