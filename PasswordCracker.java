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
        for(int index = 0; index < commonPasswords.size(); index ++) {
            String plainPassword = commonPasswords.get(index);
            try {
                //Unaugmented password
                String encryptedPassword = Sha1.hash(plainPassword);
                database.save(plainPassword,encryptedPassword);

                //Capitalizing first letter augmentation
                encryptedPassword = Sha1.hash(capitalizeFirstLetter(plainPassword));
                database.save(capitalizeFirstLetter(plainPassword), encryptedPassword);

                //Replacing a with @ augmentation
                encryptedPassword = Sha1.hash(replaceAWithAt(plainPassword));
                database.save(replaceAWithAt(plainPassword), encryptedPassword);

                //Replacing e with 3 augmentation
                encryptedPassword = Sha1.hash(replaceEWithThree(plainPassword));
                database.save(replaceEWithThree(plainPassword), encryptedPassword);

                //Replacing i with 1 augmentation
                encryptedPassword = Sha1.hash(replaceIWithOne(plainPassword));
                database.save(replaceIWithOne(plainPassword), encryptedPassword);

                //Appending '2018' augmentation 
                encryptedPassword = Sha1.hash(addYear(plainPassword));
                database.save(addYear(plainPassword), encryptedPassword);
            }
            catch (UnsupportedEncodingException e){
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
    public String capitalizeFirstLetter(String word) {
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
        return capitalized.toString();
    }
    /**
    Replaces all occurences of 'a' with @
    @param return the new string with @ where the letter 'a' or 'A' is
     */
    public String replaceAWithAt(String word){
        String newWord = word.replace('a','@');
        newWord = newWord.replace('A','@');
        return newWord;
    }
    /**
    Replaces all occurences of 'e' with 3
    @param return the new string with 3 where the letter 'E' or 'e' is
     */
    public String replaceEWithThree(String word){
        String newWord = word.replace('e','3');
        newWord = newWord.replace('E','3');
        return newWord;    
    }
    /**
    Replaces all occurences of 'e' with 1
    @param return the new string with @s where the letter 'i' or 'I' is
     */
    public String replaceIWithOne(String word){
        String newWord = word.replace('i','1');
        newWord = newWord.replace('I','1');
        return newWord;   
    }
    /**
    Adds the current year (2018) to the end of a word
     */
    public String addYear(String word){
        return word + "2018";
    }
}