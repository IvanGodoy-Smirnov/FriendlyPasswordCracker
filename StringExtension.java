/**
This class is an extension of the java String class with methods that allow you to augment 
a string 
 */
public class StringExtension {
    private final static String YEAR_STRING = "2018";
    private final static String A_STRING = "(?i)a"; 
    private final static String E_STRING = "(?i)e";
    private final static String I_STRING = "(?i)i";

    private final static String AT_STRING = "@";
    private final static String THREE_STRING = "3";
    private final static String ONE_STRING = "1";

    private final static char A_CHAR = 'a'; 
    private final static char E_CHAR = 'e';
    private final static char I_CHAR = 'i';

    private final static char AT_CHAR = '@'; 
    private final static char THREE_CHAR = '3';
    private final static char ONE_CHAR = '1';



    /**
    Replaces the last occurence of a character with the specified new character
    @param word the word to augment 
    @param oldChar the char to be replaced
    @param newChar the replacement for the new char
    @return the augmented string
     */
    public static String replaceLast(String word, char oldChar, char newChar ){
        if(word == null)
            throw new IllegalArgumentException("Word is null");
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

    /**
    Capitalizes the first occurence of a letter in the english alphabet
    @param word the word to capitalize the first letter of  
    @return the capitalized word
     */
    public static String capitalizeFirstLetter(String word) {
        final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if(word == null)
            throw new IllegalArgumentException("Word is null");
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
    Adds the current year (2018) to the end of a word
    @param word the word to append 2018 to
     */
    public static String addYear(String word){
        if (word == null)
            return YEAR_STRING;
        return word + YEAR_STRING;
    }

    /**
    Replaces all occurences of 'i' with 1
    @param return the new string with @s where the letter 'i' or 'I' is
     */
    public static String[] replaceIWithOne(String word){
         if(word == null)
            throw new IllegalArgumentException("Word is null");
        String[] replacements = {word.replaceAll(I_STRING,ONE_STRING),replaceLast(word,I_CHAR,ONE_CHAR), word.replaceFirst(I_STRING,ONE_STRING)};
        return replacements;  
    }

    /**
    Replaces all occurences of 'a' with @
    @param return the new string with @ where the letter 'a' or 'A' is
     */
    public static String[] replaceAWithAt(String word){
         if(word == null)
            throw new IllegalArgumentException("Word is null");
        String[] replacements = {word.replaceAll(A_STRING,AT_STRING),StringExtension.replaceLast(word,A_CHAR,AT_CHAR), word.replaceFirst(A_STRING,AT_STRING)};
        return replacements;
    }
     /**
    Replaces all occurences of 'e' with 3
    @param return the new string with 3 where the letter 'e' or 'E' is
     */
    public static String[] replaceEWithThree(String word){
        if(word == null)
            throw new IllegalArgumentException("Word is null");
        String[] replacements = {word.replaceAll(E_STRING,THREE_STRING),StringExtension.replaceLast(word,E_CHAR,THREE_CHAR), word.replaceFirst(E_STRING,THREE_STRING)};
        return replacements;
    }
    
}