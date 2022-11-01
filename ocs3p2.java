import java.util.Vector;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.lang.Math;

//Min execution 3 ms
public class ocs3p2 {
    public static class Point{
        int x;
        int y;
        public Point(){
            this.x = 0;
            this.y = 0;
        }
        public Point(Point point){
            this.x = point.x;
            this.y = point.y;
        }
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static class Line{
        Point start;
        Point end;
        char direction;
        public Line(){
            this.start = new Point();
            this.end = new Point();
            this.direction = 'S';
        }
        public Line(Line line){
            this.start = new Point(line.start);
            this.end = new Point(line.end);
            this.direction = 'S';
        }
    }
    public static Vector<Line> getListOfLines(String line1){
        Vector<Line> vector = new Vector<Line>();
        ArrayList<String> line_array = new ArrayList<>(Arrays.asList(line1.split(",")));
        Point previous = new Point();
        for(int i = 0; i < line_array.size(); i++){
            String value = line_array.get(i);
            if (value.length() < 2){
                System.out.println("ERROR, line has wrong length");
                return null;
            }
            int distance = 0;
            try{
                distance = Integer.parseInt(value.substring(1));
            }catch (Exception e){
                System.out.println("ERROR CANT CONVER distance");
            }
            Line line = new Line();
            line.start = new Point(previous);
            line.end = new Point(previous);

            switch(value.charAt(0)){
                case 'U':
                    line.end.y += distance;
                    line.direction = 'V';
                    break;
                case 'D':
                    line.end.y -= distance;
                    line.direction = 'V';
                    break;
                case 'R':
                    line.end.x += distance;
                    line.direction = 'H';
                    break;
                case 'L':
                    line.end.x -= distance;
                    line.direction = 'H';
                    break;
                default:
                    System.out.println("ERROR Unknonw direction " + value.charAt(0));
                    return null;
            }
            //System.out.println("{" + line.start.x + ", "+ line.start.y + "} {"+ line.end.x + ", "+ line.end.y+ "}");
            vector.add(line);
            previous.x = line.end.x;
            previous.y = line.end.y;
        }
        return vector;
    }
    public static void printLine(Vector<Line> lines){
        System.out.println("{x, y}, {x, y}");
        for(int i = 0; i < lines.size(); i++){
            System.out.println("{" + lines.get(i).start.x + ", "+ lines.get(i).start.y + "} {"+ lines.get(i).end.x + ", "+ lines.get(i).end.y+ "}");
        }
        System.out.println("-------------------");
    }
    public static Point intersection_of_lines(Line line1, Line line2){
        if(line1.direction == line2.direction){
            return new Point();
        }
        if(line1.direction == 'V'){ // line is vertical
            if((line2.start.x<= line1.start.x  && line1.start.x <=line2.end.x) || (line2.start.x>=line1.start.x && line1.start.x >=line2.end.x)){ // if x value of line1 is in range ofx value of line 2
                if((line1.start.y<=line2.start.y && line2.start.y <=line1.end.y) || (line1.start.y >=line2.start.y && line2.start.y>=line1.end.y)) {//and if value of y on line2 is in range of y values for line1
                    return new Point(line1.start.x, line2.start.y);
                }
            }
            return new Point();
        }else{ // line1 is horizontal
            if((line1.start.x<= line2.start.x  && line2.start.x <=line1.end.x) || (line1.start.x>=line2.start.x && line2.start.x >=line1.end.x)){ // if x value of line1 is in range ofx value of line 2
                if((line2.start.y<=line1.start.y && line1.start.y <=line2.end.y) || (line2.start.y >=line1.start.y && line1.start.y>=line2.end.y)) {//and if value of y on line2 is in range of y values for line1
                    return new Point(line2.start.x, line1.start.y);
                }
            }
            return new Point();
        }
    }
    public static void main(String[] args) {
        long minExecTime = 1000000, execTime;
        for (int i = 0; i < 100; i++) {
            long startTmst = System.currentTimeMillis();

            String filename = "input/input3test.txt";
            Vector<String> inputs = new Vector<String>();
            try{
                Scanner scanner = new Scanner(new File(filename));
                scanner.useDelimiter("\n");
                while(scanner.hasNext()){
                    inputs.add(scanner.nextLine());
                }
            }catch(Exception e){
                System.out.println("Error: " + e);
            }
            if(inputs.size() != 2){
                System.out.println("Error reading input" + inputs.size() );
                return;
            }
            String input1 = inputs.get(0);
            // System.out.println("input1 ="+ input1);
            String input2 = inputs.get(1);

            Vector<Line> lines1 = getListOfLines(input1);
            Vector<Line> lines2 = getListOfLines(input2);
            Vector<Point> intersections = new Vector<Point>();
            PriorityQueue<Integer> minDistance = new PriorityQueue<Integer>(); // defaults to min heap
            int k_sum = 0;
            int j_sum = 0;
            for( int k = 0; k < lines1.size(); k++){

                for(int j = 0; j < lines2.size(); j++){

                    Point temp = intersection_of_lines(lines1.get(k), lines2.get(j));
                    if (temp.x + temp.y != 0){
                        int additional_x = 0;
                        int additional_y = 0;
                        if(lines1.get(k).direction == 'V'){
                            additional_y = Math.abs(lines1.get(k).start.y - temp.y);
                            additional_x = Math.abs(lines2.get(j).start.x - temp.x);
                        }else{
                            additional_y = Math.abs(lines2.get(j).start.y - temp.y);
                            additional_x = Math.abs(lines1.get(k).start.x - temp.x);
                        }
                        minDistance.add(k_sum + j_sum +  + additional_x + additional_y);
//                        System.out.println("intersection at: "+ temp.x + ", " + temp.y);
//                        System.out.println("intersection at length to get there "+ k_sum + ", " + j_sum + " Toal:" +( k_sum +j_sum));
                    }
                    if(lines2.get(j).direction == 'V'){
                        j_sum += Math.abs(lines2.get(j).start.y - lines2.get(j).end.y);
                    }else{
                        j_sum += Math.abs(lines2.get(j).start.x - lines2.get(j).end.x);
                    }
                }
                if(lines1.get(k).direction == 'V'){
                    k_sum += Math.abs(lines1.get(k).start.y - lines1.get(k).end.y);
                }else{
                    k_sum += Math.abs(lines1.get(k).start.x - lines1.get(k).end.x);
                }
                j_sum = 0;
            }

            System.out.println("final answer: " + minDistance.peek());
            execTime = System.currentTimeMillis() - startTmst;
            System.out.println("Total execution time for a solution: " + execTime + " ms");
            if (execTime < minExecTime)
                minExecTime = execTime;
        }
        System.out.println("Min execution time: " + minExecTime + " ms");
    }
}
