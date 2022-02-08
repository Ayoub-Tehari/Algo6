import java.io.*;

//
// args : LesAchats_petit LesVins Res_Nested Res_Hashed
//

class EssaiJoin {
    public static void main(String [] args) {
        Joiner j;
        try {
            j = new Joiner(args[0],args[1],"minus_res");
            int length =j.countLines ();
            // date de début
			long startTime = System.nanoTime();
			
            j.NestedMinus();
            // date de fin
            long endTime = System.nanoTime();

            // Impression de la longueur du texte et du temps d'exécution
			System.out.println((length + "\t" + (endTime - startTime)/1.0E9));

            // j.ChangeOutput(args[3]);
            // j.HashJoin();
        } catch (Exception e) {
            System.out.println(e);
        }
   }
}
