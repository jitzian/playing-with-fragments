package mac.training.android.com.org.materialdesignbasic.examples;

import java.util.HashMap;

/**
 * Created by raian on 12/15/16.
 */

public class SampleExercises {

    public static void main(String[] args) {
        HashMap hm = new HashMap();

        hm.put(1, "uno");
        hm.put(2, "dos");
        hm.put(null, "null");
        hm.put(3, null);
        hm.put(null, null);
        hm.put(null, "otro");

        System.out.println("Size: " + hm.size());

        for(int i = 0; i < hm.size(); i ++){
            System.out.println(hm.get(i));
        }

        System.out.println(hm.get(1));
        System.out.println(hm.get(2));
        System.out.println(hm.get(3));
        System.out.println(hm.get(null));

        int myInt = 5;

        Integer myInteger = 5;

    }

}
