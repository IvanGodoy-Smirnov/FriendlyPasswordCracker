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
        for(int index = 0; index < augmentedPasswords.size(); index ++) {
            String plainPassword = augmentedPasswords.get(index);
            insert(new String[] {plainPassword},database,augmentedPasswords);
            insert(capitalizeFirstLetter(plainPassword),database,augmentedPasswords);
            insert(replaceAWithAt(plainPassword),database,augmentedPasswords);
            insert(replaceEWithThree(plainPassword),database,augmentedPasswords);
            insert(replaceIWithOne(plainPassword),database,augmentedPasswords);
        }
        for(int index = 0; index < augmentedPasswords.size(); index ++) {
            insert(addYear(augmentedPasswords.get(index)),database,null);
        }  
     
        System.out.println(database.size());
    }

    private void insert(String[] plainPasswords, DatabaseInterface database, ArrayList<String> augmentedPasswords){
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
    Capitalizes the first occurence of a letter in the english alphabet
    @param word the word to capitalize the first letter of  
    @return the capitalized word
     */
    public String[] capitalizeFirstLetter(String word) {
        final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder capitalized = new StringBuilder();
        if (word == null)
            throw new IllegalArgumentException("String is null");
        Boolean foundFirstLetter = false;
        for(int index = 0; index < word.length(); index++){
            char currentChar = word.charAt(index);
            if(!foundFirstLetter && Character.isLetter(currentChar)){
                currentChar = Character.toUpperCase(currentChar);
                foundFirstLetter = true;
            }
            capitalized.append(currentChar);
        }
        return new String[] {capitalized.toString()};
    }
    /**
    Replaces all occurences of 'a' with @
    @param return the new string with @ where the letter 'a' or 'A' is
     */
    public String[] replaceAWithAt(String word){
        String[] replacements = new String[] {word.replaceAll("(?i)a","@"),replaceLast(word,'a','@'), word.replaceFirst("(?i)a","@")};
        return replacements;
    }
    /**
    Replaces all occurences of 'e' with 3
    @param return the new string with 3 where the letter 'E' or 'e' is
     */
    public String[] replaceEWithThree(String word){
        String[] replacements = new String[] {word.replaceAll("(?i)e","3"),replaceLast(word,'e','3'), word.replaceFirst("(?i)e","3")};
        return replacements;  
    }
    /**
    Replaces all occurences of 'e' with 1
    @param return the new string with @s where the letter 'i' or 'I' is
     */
    public String[] replaceIWithOne(String word){
        String[] replacements = new String[] {word.replaceAll("(?i)i","1"),replaceLast(word,'i','1'), word.replaceFirst("(?i)i","1")};
        return replacements;  
    }
    /**
    Adds the current year (2018) to the end of a word
     */
    public String[] addYear(String word){
        return new String[] {word + "2018"};
    }

    public String replaceLast(String word, char oldChar, char newChar ){
        StringBuilder stringBuilder = new StringBuilder();
        int lastIndex = -1;
        for(int index = 0; index < word.length(); index++){
            if(oldChar == word.charAt(index) && word.substring(index).indexOf(oldChar) == word.substring(index).lastIndexOf(oldChar)){
                stringBuilder.append(newChar);
            }
            else{
                stringBuilder.append(word.charAt(index));
            }
        }
        return stringBuilder.toString();
    }
}