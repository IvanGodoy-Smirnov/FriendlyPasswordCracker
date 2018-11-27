
public class DatabaseMine implements DatabaseInterface {
    private class Entry {
        private String key;
        private String value;
        public Entry(String key, String value){
            this.key = key;
            this.value = value;
        }
        public void setKey(String newKey){
            key = newKey;
        }
        public void setValue(String newValue){
            value = newValue;
        }
        public String getKey() {
            return key;
        }
        public String getValue(){
            return value;
        }
    }
    private class HashMap{
        private Entry[] hashtable;
        private int TABLE_SIZE = 173021;
        private int numberOfDisplacements = 0;
        private int numberOfProbes = 0;
        public HashMap(){
            hashtable = new Entry[TABLE_SIZE];
        }
        public HashMap(int N){
            if( N < 0)
                throw new IllegalArgumentException("'N' must be positive");
            TABLE_SIZE = N;
            hashtable = new Entry[N];
        }
        public void put(String key, String value){
            int entryLocation = hashFunction(key);
            if(entryLocation < 0 || entryLocation >= hashtable.length)
                throw new IndexOutOfBoundsException("hash function returned index out of range");
            boolean isReplaced = false;
            while(hashtable[entryLocation] != null && !hashtable[entryLocation].getKey().equals(key)){
                entryLocation = (entryLocation + 1) % TABLE_SIZE;
                numberOfProbes += 1;
            }
            numberOfProbes +=1;
            if(entryLocation != hashFunction(key) && hashtable[entryLocation] == null)
                numberOfDisplacements +=1;
            Entry newEntry = new Entry(key,value);
            hashtable[entryLocation] = newEntry;
        }
        public String get(String key){
            int entryLocation = hashFunction(key);
            String value = null;
            if(entryLocation < 0 || entryLocation >= hashtable.length)
                throw new IndexOutOfBoundsException("hash function returned index out of range");
            boolean isFound = false;
            while(hashtable[entryLocation] != null && !isFound) {
                if(hashtable[entryLocation].getKey().equals(key)){
                    isFound = true;
                    value = hashtable[entryLocation].getValue();
                }
                entryLocation = (entryLocation + 1) % TABLE_SIZE;
            }
            return value;
        }
        public int size(){
            int count = 0;
            for(int index = 0; index < hashtable.length; index++){
                if(hashtable[index] != null)
                    count +=1;
            }
            return count;
        }

        public int getInitialIndexes() {
            return TABLE_SIZE;
        }

        public int numberOfDisplacements() {
            return numberOfDisplacements;
        }

        public double averageProbes() {
            return size()/(double) numberOfProbes;
        }

        public double loadFactor(){
            return size()/(double) TABLE_SIZE;
        }
        private int hashFunction(String key) {
            int address=key.hashCode()%TABLE_SIZE;
           return (address>=0)?address:(address+TABLE_SIZE);
        }

        
        
    }
     private HashMap database;
    /**
    Constructs a standard database
     */
    public DatabaseMine() {
        database = new HashMap();
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
        System.out.println("Size is " + size() + " passwords\n" + 
                        "Intial Number of Indexes When Created: " + database.getInitialIndexes() + "\n" +
                        "Load Factor: " + database.loadFactor() + "\n" + 
                        "Average Number of Probes: " + database.averageProbes() + "\n" + 
                        "Number of displacements (due to collisions): " + database.numberOfDisplacements );
    }
}