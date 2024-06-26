package attribute;

public class AssignmentTable {
    private Drive drive;
    private Route route;
    private int numberOfTurns;

    public AssignmentTable(){}

    public AssignmentTable(Drive drive, Route route, int numberOfTurns) {
        this.drive = drive;
        this.route = route;
        this.numberOfTurns = numberOfTurns;
    }

    public Drive getDrive() {
        return drive;
    }

    public void setDrive(Drive drive) {
        this.drive = drive;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public void setNumberOfTurns(int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }



    @Override
    public String toString() {
        return "Assignment :{" + "Mã tài xế: " + getDrive().getId() + ", Tên tài xế: " + getDrive().getName() + ", Mã tuyến: "+getRoute().getId() + ", Số lượt: " + getNumberOfTurns() +  '}';
    }
}
