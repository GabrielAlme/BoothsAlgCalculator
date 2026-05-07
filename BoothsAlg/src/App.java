import java.util.Scanner;


public class App {

        public static String toBinary(double num){
        String output = "";
        if(num < 0){
            output += "1";
            for (int i = 6; i >= 0 ; i--) {
            if(Math.pow(2,i) < Math.abs(num)){
                output += "0";
                num = num + Math.pow(2,i);
            }else {
                output += "1";
            }
            }
        }else{
            output += "0";
            for (int i = 6; i >= 0 ; i--) {
            if(Math.pow(2,i) <= num){
                output += "1";
                num = num - Math.pow(2,i);
            }else {
                output += "0";
            }
            }
        }
        return output;
    }

    public static int toDecimal(String binary) {
    int result = 0;
    if (binary.charAt(0) == '1') {
        // negative number, sign bit worth -128
        result -= 128;
    }
    for (int i = 1; i < 8; i++) {
        if (binary.charAt(i) == '1') {
            result += (int) Math.pow(2, 7 - i);
        }
    }
    return result;
}
    public static void main(String[] args) throws Exception {
        double M = 0;
        double Q = 0;
        double A = 0;
        char Q1 = '0';
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Input the Multiplicand");
            M = sc.nextInt();
            if(M > 127 || M < -128){
                System.out.println("please enter a valid num");
                continue;
            }
            System.out.println("Input the Multiplier");
            Q = sc.nextInt();
            if(Q > 127 || Q < -128){
                System.out.println("please enter a valid num");
                continue;
            }
            break;
        }
        double goal = M * Q;
        System.out.println("Iteration: 0 |Step: Initialize |Multiplicand: " + toBinary(M) + "|Product: " + toBinary(A) + " " + toBinary(Q) + " " + Q1);
        String Abin = toBinary(A);
        String Qbin = toBinary(Q);
        for(int i = 0; i < 8; i++){
            char q0 = Qbin.charAt(Qbin.length() - 1);
            if(q0 == '1' && Q1 == '0'){
                A = A - M;
                System.out.println("Iteration: " +  (i + 1) + " |Step: Subtract |Multiplicand: " + toBinary(M) + "|Product: " + toBinary(A) + " " + toBinary(Q) + " " + Q1);

                Abin = toBinary(A);
                Qbin = toBinary(Q);

                Q1 = Qbin.charAt(Qbin.length() - 1);
                Qbin = Abin.charAt(Abin.length() - 1) + Qbin.substring(0, Qbin.length() - 1);
                Abin = Abin.charAt(0) + Abin.substring(0, Abin.length() - 1);
                A = toDecimal(Abin);
                Q = toDecimal(Qbin);
                System.out.println("Step: Right-Shift |Multiplicand: " + toBinary(M) + "|Product: " + toBinary(A) + " " + toBinary(Q) + " " + Q1);
                continue;
            } else if (q0 == '0' && Q1 == '1'){
                A = A + M;
                System.out.println("Iteration: " +  (i + 1) + " |Step: Add |Multiplicand: " + toBinary(M) + "|Product: " + toBinary(A) + " " + toBinary(Q) + " " + Q1);
                
                Abin = toBinary(A);
                Qbin = toBinary(Q);

                Q1 = Qbin.charAt(Qbin.length() - 1);
                Qbin = Abin.charAt(Abin.length() - 1) + Qbin.substring(0, Qbin.length() - 1);
                Abin = Abin.charAt(0) + Abin.substring(0, Abin.length() - 1);
                A = toDecimal(Abin);
                Q = toDecimal(Qbin);
                System.out.println("Step: Right-Shift |Multiplicand: " + toBinary(M) + "|Product: " + toBinary(A) + " " + toBinary(Q) + " " + Q1);
                continue;
            }

            System.out.println("Iteration: " +  (i + 1) + " |Step: No Operation |Multiplicand: " + toBinary(M) + "|Product: " + toBinary(A) + " " + toBinary(Q) + " " + Q1);
            Q1 = Qbin.charAt(Qbin.length() - 1);
            Qbin = Abin.charAt(Abin.length() - 1) + Qbin.substring(0, Qbin.length() - 1);
            Abin = Abin.charAt(0) + Abin.substring(0, Abin.length() - 1);
            A = toDecimal(Abin);
            Q = toDecimal(Qbin);
            System.out.println("Step: Right-Shift |Multiplicand: " + toBinary(M) + "|Product: " + toBinary(A) + " " + toBinary(Q) + " " + Q1);
        }

        System.out.println("the final product is: " + toBinary(A) + " " + toBinary(Q) + " which is " + goal);

        
        
        
        
        
        
    }
}
