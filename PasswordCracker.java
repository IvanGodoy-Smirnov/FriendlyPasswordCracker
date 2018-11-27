import java.util.ArrayList;
import java.io.UnsupportedEncodingException;

public class PasswordCracker {
    /**
    Fills a database with common passwords and their encrypted correspondant
    @param commonPasswords a list of passwords to add to the database
    @param database the database to fill with password info
    @throws UnsupportedEncodingException thrown if the hash function is unable to hash a password
     */
    public void createDatabase(ArrayList<String> commonPasswords, DatabaseInterface database) {
        if (commonPasswords == null || database == null) {
            throw new IllegalArgumentException("Common Password and/or database is null");
        }
        ArrayList<String> augmentedPasswords = new ArrayList<String>(commonPasswords);
        //Loops until the augmentations stop producing NEW augmentations 
        for(int index = 0; index < augmentedPasswords.size(); index ++) {
            String plainPassword = augmentedPasswords.get(index);
            insertToDatabase(plainPassword,database,augmentedPasswords);
            insertToDatabase(StringExtension.capitalizeFirstLetter(plainPassword),database,augmentedPasswords);
            insertToDatabase(StringExtension.replaceAWithAt(plainPassword),database,augmentedPasswords);
            insertToDatabase(StringExtension.replaceEWithThree(plainPassword),database,augmentedPasswords);
            insertToDatabase(StringExtension.replaceIWithOne(plainPassword),database,augmentedPasswords);
        }
        //Since adding "2018" would always create new augmentations (keeping the loop above)
        //in an infinite loop, once every augmentation has been made, one more pass through the array
        //is done to add 2019 to every password created
        for(int index = 0; index < augmentedPasswords.size(); index ++) {
            insertToDatabase(StringExtension.addYear(augmentedPasswords.get(index)),database,null);
        }  
    }

    /**
    Cracks a password 
    @param encryptedPassword the password to be cracked
    @param database the database used to crack the password
    @return the plain password if found, otherwise it returns empty string
     */
    public String crackPassword(String encryptedPassword, DatabaseInterface database) {
        if(encryptedPassword == null || database == null)
            throw new IllegalArgumentException("Encrypted password and/or database is null");
        String plainPassword = database.decrypt(encryptedPassword);
        if(plainPassword == null)
            plainPassword = "";
        return plainPassword;
    }
    /**
    Inserts plain passwords and their corresponding hash1 value into the given database and places the plain passwords into an array if given.
    @param plainPasswords array of passwords 
    @param database the database to place the plain password and hashed password
    @param augmentedPasswords (optional) the array to place the new plain passwords in
     */
     private void insertToDatabase(String[] plainPasswords, DatabaseInterface database, ArrayList<String> augmentedPasswords){
        if(plainPasswords == null || database == null)
            throw new IllegalArgumentException("array of passwords and/or database is null");
    
        for(int index = 0; index < plainPasswords.length; index++){
                try {
                    String plainPassword = plainPasswords[index];
                    String encryptedPassword = Sha1.hash(plainPassword);
                    String previousPassword = database.save(plainPassword, encryptedPassword);
                    if (previousPassword == null && augmentedPasswords != null)
                        augmentedPasswords.add(plainPassword);
                }
                catch(UnsupportedEncodingException e){
                    e.printStackTrace();
                }
        }
    }
     /**
    Inserts a plain password and its corresponding hash1 value into the given database and places the plain password into an array if given.
    @param plainPassword array of passwords 
    @param database the database to place the plain password and hashed password
    @param augmentedPasswords (optional) the array to place the new plain passwords in
     */
    private void insertToDatabase(String plainPassword, DatabaseInterface database, ArrayList<String> augmentedPasswords){
        if(plainPassword == null || database == null)
            throw new IllegalArgumentException("array of passwords and/or database is null");
        try {
            String encryptedPassword = Sha1.hash(plainPassword);
            String previousPassword = database.save(plainPassword, encryptedPassword);
            if (previousPassword == null && augmentedPasswords != null)
                augmentedPasswords.add(plainPassword);
        }
        catch(UnsupportedEncodingException e){
            System.out.println("Failed to hash a plain password, view the stacktrace below\n\n");
            e.printStackTrace();
        }
    }


   
   
    
   
}