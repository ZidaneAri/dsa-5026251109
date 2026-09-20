package lw01.prelab;

public class ColourPrint extends PrintJob {
    
    public ColourPrint(String id, int pages) {
        super(id, pages);
    }

    @Override
    public int calculateCharge() {
        int pages = getPages();
        int totalCharge = 2000;
        
        if (pages <= 10) {
            totalCharge += pages * 1500;
        } else {
            totalCharge += (10 * 1500) + ((pages - 10) * 1000);
        }
        
        return totalCharge;
    }

    @Override
    public String label() {
        return "Colour";
    }
}
