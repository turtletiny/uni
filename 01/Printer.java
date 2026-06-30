import java.util.ArrayList;
import java.util.Random;
public class Printer {

    public static void main(String[] args) {
        Printer printer = new Printer("Cool Printer", 32, true, true);
        printer.addJobToQueue(new PrintJob("doc1", 2, "horizontal", true));
        printer.addJobToQueue(new PrintJob("doc2", 5, "horizontal", false));
        printer.addJobToQueue(new PrintJob("doc3", 1, "landscape", true));
        printer.addJobToQueue(new PrintJob("doc4", 9, "landscape", true));
        printer.addJobToQueue(new PrintJob("doc5", 999, "landscape", true));
        printer.printMultipleJobs();

    }

    private String name;
    private int curSheets;
    private boolean canPrintColour, canPrintDoubleSided;
    private ArrayList<PrintJob> jobQueue = new ArrayList<>();

    public Printer(String name, int curSheets, boolean canPrintColour, boolean canPrintDoubleSided) {
        this.name = name;
        this.curSheets = curSheets;
        this.canPrintColour = canPrintColour;
        this.canPrintDoubleSided = canPrintDoubleSided;
    }

    public String getName() {
        return this.name;
    }

    public int getSheets() {
        return this.curSheets;
    }

    public boolean getCanPrintColour() {
        return this.canPrintColour;
    }

    public boolean getCanPrintDoubleSided() {
        return this.canPrintDoubleSided;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSheets(int sheets) {
        this.curSheets = sheets;
    }

    public void setCanPrintColour(boolean bool) {
        this.canPrintColour = bool;
    }

    public void setCanPrintDoubleSided(boolean bool) {
        this.canPrintDoubleSided = bool;
    }

    public void addJobToQueue(PrintJob printjob){
        this.jobQueue.add(printjob);
        System.out.println("Added: " + printjob.fileName + " Queue number: " + this.jobQueue.size());
    }

    public boolean printJob(PrintJob printjob){
        if (printjob.numOfCopies > this.curSheets){
            System.out.println("Not enough paper!");
            return false;
        } else if (printjob.isColour && !this.canPrintColour){
            System.out.println("Cannot print colour");
            return false;
        } 
        System.out.println("Printing...");
        System.out.println(printjob);
        return true;
    }

    public void printMultipleJobs(){
        Random random = new Random();
        int num = random.nextInt(this.jobQueue.size());
        for (int i = 0; i < num; i++){
            System.out.println("Printing: " + this.jobQueue.get(0).fileName);
            this.printJob(this.jobQueue.get(0));
            this.jobQueue.remove(0);   
        }
        if (this.jobQueue.size() > 0){
            System.out.println("Jobs remaining: " + (this.jobQueue.size()));
        }
    }



    public void restock(int sheets) {
        this.curSheets += sheets;
    }

    public String toString() {
        return "Name: " + this.name + "\nSheets: " + this.curSheets + "\nColour: " + this.canPrintColour
                + "\nDouble Sided: " + this.canPrintDoubleSided;
    }
}

class PrintJob {

    String fileName;
    int numOfCopies;
    String orientation;
    boolean isColour;

    public PrintJob(String fileName, int numOfCopies, String orientation, boolean isColour) {
        this.fileName = fileName;
        this.numOfCopies = numOfCopies;
        this.orientation = orientation;
        this.isColour = isColour;
    }

    public String toString(){
        return "Filename: " + this.fileName + "\nCopies: " + this.numOfCopies 
        + "\nOrientation: " + this.orientation;
    }
}
