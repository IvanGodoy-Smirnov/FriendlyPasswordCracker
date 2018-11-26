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
                String encryptedPassword = Sha1.hash(plainPassword);
                database.save(plainPassword,encryptedPassword);
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
    @return the plain password if found, otherwise null
     */
    public String crackPassword(String encryptedPassword, DatabaseInterface database) {
        if(encryptedPassword == null || database == null)
            throw new IllegalArgumentException("Encrypted password and/or database is null");
        return database.decrypt(encryptedPassword);
    }
}