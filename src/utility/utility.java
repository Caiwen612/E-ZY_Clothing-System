package utility;

public class utility {
    public static void clearScreen() {
        //    try{
        //        new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        //    }
        //    catch(Exception e){
        //        System.out.println(e);
        //    }
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
