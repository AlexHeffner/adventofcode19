// Your First Program
import java.util.Vector;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class ocs3 {
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
  }
  public static class Line{
    Point start;
    Point end;
    public Line(){
      this.start = new Point();
      this.end = new Point();
    }
    public Line(Line line){
      this.start = new Point(line.start);
      this.end = new Point(line.end);
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
          break;
        case 'D':
          line.end.y -= distance;
          break;
        case 'R':
          line.end.x += distance;
          break;
        case 'L':
          line.end.x -= distance;
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
  public static void main(String[] args) {
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
    
    // printLine(lines1);
    // printLine(lines2);
  }
}
