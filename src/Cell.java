import java.awt.*;
import java.util.Random;

class Cell{
    // fields
    int x;
    int y;
    static int size = 35;

    // this fields are needed for the creation of 
    // the random colors that will populate the grid :)
    int R; 
    int G;
    int B;
    Color randomColor = new Color(R, G, B);

    //this string dictated the type of terrain the cell has
    String type;
    
    //constructors
    public Cell(int x, int y){
        this.x = x;
        this.y = y;

        choseCellType();
    }

    void grass(){
        //random color green 
        this.R = (int)(Math.random( )*16);
        this.G = (int)(50 + Math.random( )*206);
        this.B = (int)(Math.random( )*16);
        this.randomColor = new Color(R, G, B);
        this.type = "grass";
    }
    
    void road() {
        //dark grey color
        this.R = 169;
        this.G = 169;
        this.B = 169;
        this.randomColor = new Color(R, G, B);
        this.type = "road";
    }

    void mountain() {
        //light grey color
        this.R = 211;
        this.G = 211;
        this.B = 211;
        this.randomColor = new Color(R, G, B);
        this.type = "mountain";
    }

    void water(){
        //I also put random color for the water
        //different blues suit the grid better ;)
        this.R = (int)(Math.random( )*8);
        this.G = (int)(Math.random( )*16);
        this.B = (int)(50 + Math.random( )*206);
        this.randomColor = new Color(R, G, B);
        this.type = "water";
    }

    void choseCellType(){
        Random rand = new Random();

        //Obtain a number between [0 - 119].
        int n = rand.nextInt(120);
        n += 1;


        //decides what type of cell to inicialize, 
        //grass is 3 times more likely to appear.
        if (n <= 10){road();}
        else if (n <= 20){mountain();}
        else if (n <= 30){water();}
        else {grass();}
    }

    //methods
    void paint(Graphics g, Point mousePos){
        
        //selects the font to use in the text display
        Font font = new Font("Verdana", Font.BOLD, 24);
        g.setFont(font);
        
        if(contains(mousePos)){
            g.setColor(Color.BLACK);
            g.drawString("->Type of Terrain:", 800, 250); 
            g.drawString(getType(), 900, 300); 
            g.drawString("->Cost of Terrain:", 800, 350); 
            g.drawString(getCost(), 920, 400);  

            g.setColor(Color.YELLOW);
        } 
        else {
            g.setColor(randomColor);
        }
        g.fillRect(x,y,size,size);
        g.setColor(Color.BLACK);
        g.drawRect(x,y,size,size);
    }
    

    String getType(){return type;}
    
    String getCost(){
       
        //road movement is always 1
        //mountain movement is always 10
        //water movement is always 100
        //grass movement is calculated as 
        //(green component - 100)/50
        
        int cost;

        if (type == "road") {cost = 1;}
        else if(type == "mountain") {cost = 10;}
        else if(type == "water") {cost = 100;}
        else {
            cost = (G - 100)/50;
            //in some cases the cost of grass would be a negative number
            //i took it as the cost was zero (the cost cannot be negative).
            if (cost <= 0){
                cost = 0; 
            }
        }

        //some shady bussines I did hehe
        String CostAsString = Integer.toString(cost);
        return CostAsString;
    }

    boolean contains(Point p){
        if (p != null){
            return (x < p.x && x+size > p.x && y < p.y && y+size > p.y);
        } else {
            return false;
        }
    }
}