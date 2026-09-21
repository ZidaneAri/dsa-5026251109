package lw01.unguided;

public class LaptopRental extends Rental{
    
    public LaptopRental(String id, int days) {
        super(id, days);
    }

    @Override 
    public int calculateCharge() {
        int days = getDays();
        int totalCharge = 0;

        totalCharge += days * 40000;

        return totalCharge;
    }

    @Override 
    public String label() {
        return "Laptop";
    }
}
