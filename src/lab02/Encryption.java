package lab02;

public class Encryption{
    private static final int ALPHA_START = 65;
    private static final int ALPHA_LENGTH = 26;

    public static String encrypt(int key, String text){
        //Newletter = ((OriginalLetter + 1 - 65) % 26) + 65
        char[] charArray = text.toCharArray();
        String ans = "";
        for(int i=0;i<charArray.length;i++){
            ans += (char) (((charArray[i] + key - ALPHA_START) % ALPHA_LENGTH) + ALPHA_START);
            key+=charArray[i];
        }
        return ans;
    }

    public static String decrypt(int key, String text){
        char[] charArray = text.toCharArray();
        String ans = "";
        for(int i = 0; i < charArray.length; i++){
            char newLetter = (char) (((charArray[i] - key + ALPHA_LENGTH  - ALPHA_START) % ALPHA_LENGTH) + ALPHA_START);
            ans += newLetter;
            key += newLetter;
            key %= ALPHA_LENGTH;
        }
        return ans;
    }

    public static void main(String[] args){
        System.out.println(encrypt(11, "Eats egg"));
        System.out.println(decrypt(11, "PIUFLIHG"));
    }
}
