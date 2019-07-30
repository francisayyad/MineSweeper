        // done by Francis Ayyad
        // This is a minesweeper game
        
    import javax.swing.JFrame;
    import javax.swing.JPanel;
    import javax.swing.WindowConstants;
    import java.awt.Dimension;
    import java.awt.Color;
    import java.awt.Graphics;
    import java.awt.Graphics2D;
    import java.awt.Point;
    import java.awt.event.MouseListener;
    import java.awt.event.MouseEvent;
    import java.awt.Font;
    import java.awt.FontMetrics;
    
    public class MineSweeper {
        private JFrame frame;
    
        public MineSweeper() {
            frame = new JFrame("Mine Sweeper");
            frame.setSize(600, 400);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setPreferredSize(frame.getSize());
            frame.add(new MineDraw(frame.getSize()));
            frame.pack();
            frame.setVisible(true);
        }
    // intro to game
        public static void main(String... argv) {
            new MineSweeper();
            System.out.println("Welcome to minesweeper, a game with an abjective to complete a grid while avoiding all 10 mines placed on the battelfield. ");
        }
        // all variables are placed here
        public static class MineDraw extends JPanel  implements MouseListener {
            int startX = 10;
            int startY = 10;
            int side = 40;
            int turn = 0;
            int yPlace = 0;
            int[][] grid = new int[8][8];
            boolean [][] clicked = new boolean [8][8];
            boolean startClick = false;
            int nextClick = 0;
            boolean reset = false;
            public MineDraw(Dimension dimension) {
                setSize(dimension);
                setPreferredSize(dimension);
                addMouseListener(this);
                randomAssign();
    
            }
    
            @Override
            public void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D)g;
                Dimension d = getSize();
                startX = 0;
                startY = 0;
    
                //1) draw grid of squares
                //2) draw numbers in the squares
              
              
                for (int row = 0; row < grid.length; row++) {
                    for (int col = 0; col < grid[0].length; col++) {
                        //grid[row][col]
    
                        g2.setColor(Color.black);
                        g2.drawRect(startX,startY,side,side);
                        if (clicked [row][col] == true){
                            g2.setFont (new Font("TimesRoman", Font.PLAIN, 20));
                            g2.drawString(""+grid[row][col] ,  startX + side/2 ,startY + side/2);
                        }
                        startX += side;
                    }
                    startX = 0;
                    startY += side;
                }
    
            }
    
            public void randomAssign(){
    
                int max = grid.length;
                int min = 0;
                int counter = 0;
                //conter is less than 10 because there are ten mines
                while(counter<10){
                    int rowRand = (int) (Math.random() * ((max - min) + 0)) + min;
                    int colRand = (int) (Math.random() * ((max - min) + 0)) + min;
    
                    if (grid[rowRand][colRand]!=-1){
                        counter++;
                        grid[rowRand][colRand]=-1;
                    }
                }
                assignNumbers();
    
            }
    
            public void assignNumbers(){
               
                 for (int row = 0; row < grid.length; row++) {
                    for (int col = 0; col < grid[0].length; col++) {
                        int count = 0;
                        int counts = 0;
                        
                        if (grid[row][col]==0){
                            //checking top left of box clciked 
                            if (row>0 && col>0 && grid[row-1][col-1]==-1){//makes sure in bound and box not already assigned
                                count++;
                                counts++;
                            } 
                            //checking above box clicked
                            if (row>0 && grid[row-1][col]==-1){
                                count++;
                                counts++;
                            }  
                            //checking top right of box clicked
                            if (row>0 && col<grid.length-1 && grid[row-1][col+1]==-1){
                                count++;
                                counts++;
                            } 
                            //checking to the right of box xlicked
                            if (col<grid.length-1 && grid[row][col+1]==-1){
                                count++;
                                counts++;
                            } 
                            //checking to the left of box clicked
                            if (col>0 && grid[row][col-1]==-1){
                                count++;
                                counts++;
                            } 
                            //checking bottom right of box clicked
                            if (row<grid.length-1 && col<grid.length-1 && grid[row+1][col+1]==-1){
                                count++;
                                counts++;
                            } 
                            //checking to the bottom of box clicked
                            if (row<grid.length-1 && grid[row+1][col]==-1){
                                count++;
                                counts++;
                            } 
                            //checking bottom left of box clicked 
                            if (row < grid.length-1 && col>0 && grid[row+1][col-1]==-1){
                                count++;
                                counts++;
                            } 
    
                            grid[row][col]=count;
                           
    
                        }
                  }
                    
            }
            
        }
    
            public void mousePressed(MouseEvent e) {
                int x = e.getX()/side;//column
                int y = e.getY()/side;//row
                if(x<grid.length && y <grid[0].length){
                    if(x<grid.length && y <grid[0].length){
                        if(startClick==false){
                            showArea(y, x);
                            clicked[y][x] = true;
                            startClick = true;
                        }else{
                            clicked[y][x] = true; 
                        }
                    }
                    if(x<grid.length && y <grid[0].length){
                        if(nextClick == -1){
                            System.out.println("You lose!");
                    }
                    repaint();
                }   
                
            }
        }

        public void showArea(int row,int col){
            if (row>0 && col>0 && grid[row-1][col-1]!=-1){ // top left
                clicked[row-1][col-1]=true;
            }
            if (row>0 && col>0 && grid[row-1][col]!=-1){ //above
                clicked[row-1][col]=true;
            }
            if (row>0 && col>grid.length-1 && grid[row-1][col+1]!=-1){//top right
                clicked[row-1][col+1]=true;
            }
            if (col<grid.length-1 && grid[row][col+1]!=-1){
                clicked[row][col+1]=true;
            } 
            //checking to the left of x
            if (col>0 && grid[row][col-1]!=-1){
                    clicked[row][col-1]=true;
                } 
                //checking bottom right of x
            if (row<grid.length-1 && col<grid.length-1 && grid[row+1][col+1]!=-1){
                clicked[row+1][col+1]=true;
            } 
            //checking to the bottom of x
            if (row<grid.length-1 && grid[row+1][col]!=-1){
                clicked[row+1][col]=true;
            } 
            //checking bottom left of x
            if (row < grid.length-1 && col>0 && grid[row+1][col-1]!=-1){
                clicked[row+1][col-1]=true;
            } 
        }   
        

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mouseClicked(MouseEvent e) {
        }
    }
}