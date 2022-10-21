// Your First Program
import java.util.Vector;
import java.util.Scanner;
import java.io.File;

public class ocs2 {
  public static void restore_gravity(Vector<Integer> program, int noun, int verb){
    program.set(1,noun);
    program.set(2,verb);
  }
  public static void add(Vector<Integer> program, int pos){
    program.set(program.get(pos+3), program.get(program.get(pos+1)) + program.get(program.get(pos+2)));
  }
  public static void multi(Vector<Integer> program, int pos){
    program.set(program.get(pos+3), program.get(program.get(pos+1)) * program.get(program.get(pos+2)));
  }
  public static Vector<Integer> getListOfMass(String filename){
    Vector<Integer> vector = new Vector<Integer>();
    try{
      Scanner scanner = new Scanner(new File(filename));
      scanner.useDelimiter("\\D");
      while(scanner.hasNextInt()){
        vector.add(scanner.nextInt());
      }
    }catch(Exception e){
      System.out.println("Error: " + e);
    }
    return vector;
  }
  @SuppressWarnings("unchecked")
  public static void main(String[] args) {
    String filename = "input/input2.txt";
    Vector<Integer> program = getListOfMass(filename);
    if(program.size() < 1){
      System.out.println("Error: reading program");
      return;
    }
    // for(int i = 0; i < program.size(); i++){
    //   System.out.println(program.get(i) + " ");
    // }
    int noun = -1;
    int verb = -1;

    boolean finished = false;
    for(int i = 0; i < 100 && !finished; i++){
      for(int j = 0; j < 100 && !finished; j++){
        int pos = 0;
        Vector<Integer> program2 = (Vector<Integer>)program.clone();
        restore_gravity(program2,i,j); // could be swaped
        while(pos <program2.size()){ /// run program
          int code = program2.get(pos);
          if(code == 99){
            break;
          }else if(code == 1){
            add(program2,pos);
          }else if(code == 2){
            multi(program2, pos);
          }else{
            System.out.println("Error: reading program unknown code " + pos + " value: " + program2.get(pos));
            return;
          }
          pos += 4;
        }
        if(program2.get(0)==19690720){
          noun = i;
          verb = j;
          finished = true;
        }
      }
    }
    System.out.println(100 * noun + verb);

    // for(int i = 0; i < program.size(); i++){
    //   System.out.print(program.get(i));
    //   if(i != program.size() -1){
    //     System.out.print(",");
    //   }else{
    //     System.out.print("\n");
    //   }
    // }
  }
}
