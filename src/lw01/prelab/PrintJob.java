package lw01.prelab;

public abstract class PrintJob implements Chargeable {
    private String id;
    private int pages;

    protected PrintJob(String id, int pages) {
        if (pages <= 0) {
            throw new IllegalArgumentException("Halaman tidak boleh nol atau negatif.");
        }
        this.id = id;
        this.pages = pages;
    }

    public String getId() {
        return id;
    }

    public int getPages() {
        return pages;
    }

    @Override
    public abstract int calculateCharge();

    public int calculateCharge(int copies) {
        if (copies <= 0) {
            throw new IllegalArgumentException("Jumlah salinan tidak boleh nol atau negatif.");
        }
        return copies * calculateCharge(); 
    }
    
    public abstract String label();

    public String summary() {
        return id + " | " + label() + " | " + calculateCharge();
    }
}