import java.util.ArrayList;

public class Test {
    public static void main(String[] args){
        kika();
        // System.out.println("ied".replaceFirst('d','D'));
        // PasswordCracker cracker = new PasswordCracker();
        // String[] testWords = new String[] {"isantose","SaintoEes","12isantos","123i54","!dfef","123456a"};
        // for(int index = 0; index < testWords.length;index++ ){
        //     System.out.println(cracker.addYear(testWords[index]));
        // }
       
    }

    static public void kika() {
        ArrayList<String> passwords = new ArrayList<String>();
        passwords.add("santoise");
        passwords.add("siantoes");
        passwords.add("santosie");
        PasswordCracker cracker = new PasswordCracker();

 //       System.out.println(cracker.replaceLast("anto",'s','$'));


        // for(int index = 0; index < passwords.size(); index++){
        //     String stringToAdd = cracker.replaceAWithAt(passwords.get(index));
        //     String stringToAddTwo = cracker.replaceEWithThree(passwords.get(index));
        //     String stringToAddThree = cracker.replaceIWithOne(passwords.get(index));

        //     if(!passwords.contains(stringToAdd)){
        //         passwords.add(stringToAdd);
        //     }

        //     if(!passwords.contains(stringToAddTwo)){
        //         passwords.add(stringToAddTwo);
        //     }


        //     // if(!passwords.contains(stringToAddThree)){
        //     //     passwords.add(stringToAddThree);
        //     // }

        // }
        // for(int index = 0; index < passwords.size(); index++){
        //    System.out.println(passwords.get(index));
        // }
        // System.out.println(passwords.size());
    }

    public void basicTest() {
        PasswordCracker testCracker=new PasswordCracker(); 
        DatabaseStandard database1=new DatabaseStandard(); 
        ArrayList<String> commonPass=new ArrayList<String>(); 
        commonPass.add("123456");
        commonPass.add("password"); 
        commonPass.add("12345678"); 
        commonPass.add("brady"); 
        testCracker.createDatabase(commonPass,database1); 
        database1.printStatistics();
        String code=new String("F35D55B3ACF667911A679B44918F5D88B2400823"); 
        String discoverPassword=testCracker.crackPassword(code,database1); 
        System.out.println("Decrypt: "+code+ " Password: "+discoverPassword+";");

    }
}