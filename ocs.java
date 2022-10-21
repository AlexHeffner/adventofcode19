// Your First Program
import java.util.Vector;
import java.util.Scanner;
import java.io.File;

class ocs {
  public static int getFule(int mass){
    return( (mass /3) -2);
  }
  public static int getFuleOfFule(int totalMassWithFule){
    if(totalMassWithFule <= 0){
      return 0;
    }else{
      int new_mass = ((totalMassWithFule /3) -2);
      if(new_mass <= 0){
        return 0;
      }
      return new_mass + getFuleOfFule(new_mass);
    } 
  }
  public static Vector<Integer> getListOfMass(String filename){
    Vector<Integer> vector = new Vector<Integer>();
    try{
      Scanner scanner = new Scanner(new File(filename));
      while(scanner.hasNextInt()){
        vector.add(scanner.nextInt());
      }
    }catch(Exception e){
      System.out.println("Error: " + e);
    }
    return vector;
  }
  public static void main(String[] args) {
      System.out.println("Hello, World!"); 
      // int [] masses = {12,14,1969,100756};
      // for(int i =0; i < 4;i++){
      //   System.out.println("for mass" + masses[i] + " the fule is " + getFuleOfFule(masses[i])); 
      // }
      String filename = "input/input1.txt";
      Vector<Integer> list = getListOfMass(filename);
      if(list.size() < 1){
        System.out.println("Error: reading list");
        return;
      }
      int sum = 0;
      for(int x = 0; x < list.size() ;x++){
        sum += getFuleOfFule(list.get(x));
      }
      System.out.println("Sum of all fule is: " + sum );
  }
}