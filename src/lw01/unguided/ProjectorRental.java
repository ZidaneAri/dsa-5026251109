package lw01.unguided;

public class ProjectorRental extends Rental {
    
    public ProjectorRental(String id, int days) {
        super(id, days);
    }

    @Override 
    public int calculateCharge() {
        int days = getDays();
        int totalCharge = 0;

        if (days <= 3) {
            totalCharge += days * 60000;
        } else {
            totalCharge += (3 * 60000) + ((days - 3) * 45000);
        }
        return totalCharge;
    }

    @Override 
    public String label() {
        return "Projector";
    }
    
}
