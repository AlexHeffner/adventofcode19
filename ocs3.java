// Your First Program
import java.util.Vector;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class ocs3 {
  public static class Line{
    int xdirection;
    int ydirection;
    int distance;
  }
  public static Vector<Line> getListOfLines(String line1){
    Vector<Line> vector = new Vector<Line>();
    ArrayList<String> line_array = new ArrayList<>(Arrays.asList(line1.split(",")));
    for(int i = 0; i < line_array.size(); i++){
      String value = line_array.get(i);
      // System.out.println("Value" + value);
      if (value.length() < 2){
        System.out.println("ERROR, line has wrong length");
        return null;
      }
      Line line = new Line();
      line.xdirection = 0;
      line.ydirection = 0;
      line.distance = 0;
      switch(value.charAt(0)){
        case 'U':
          line.ydirection = 1;
          break;
        case 'D':
          line.ydirection = -1;
          break;
        case 'R':
          line.xdirection = 1;
          break;
        case 'L':
          line.xdirection = -1;
          break;
        default:
          System.out.println("ERROR Unknonw direction " + value.charAt(0));
          return null;
      }
      try{
        line.distance = Integer.parseInt(value.substring(1));
      }catch (Exception e){
        System.out.println("ERROR CANT CONVER distance");
      }
      vector.add(line);
    }
    return vector;
  }
  public static void printLine(Vector<Line> lines){
    System.out.println("{x , y, distance}");
    for(int i = 0; i < lines.size(); i++){
      System.out.println("{" + lines.get(i).xdirection + ", "+ lines.get(i).ydirection + ", "+ lines.get(i).distance + "}");
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
