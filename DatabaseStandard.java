import java.util.HashMap;

public class DatabaseStandard implements DatabaseInterface {
    private HashMap<String,String> database;
    /**
    Constructs a standard database
     */
    public DatabaseStandard() {
        database = new HashMap<String,String>();
    }

    /**
    Saves a plain password and it's encrypted version in the database
    @param plainPassword the unencryped password
    @param encryptedPassword the encrypted version of the plainPassword
    @return The string that was placed or null if it's the first time adding this key
     */
    public String save(String plainPassword, String encryptedPassword){
        if (plainPassword == null || encryptedPassword == null) {
            throw new IllegalArgumentException("Password key/value cannot be null");
        }
        String replacedPassword = database.get(encryptedPassword);
        database.put(encryptedPassword,plainPassword);
        return replacedPassword;
    }
    /**
    Decrypts an encrypted password 
    @param encryptedPassword the password to decrypt
    @return the decrypted password or null if the password could not be decrypted
     */
	public String decrypt(String encryptedPassword) {
        return database.get(encryptedPassword);
    }
	
    /**
    Returns the size of the database
    @return the size of the database
     */
    public int size() {
        return database.size();
    }
	
    /**
    Prints the stats of the database (size and initial # of indexes when created)
     */
    public void printStatistics(){
        System.out.println("Size is " + size() + " passwords\n" + "Intial Number of Indexes When Created: 0");
    } // print statistics based on type of Database
}