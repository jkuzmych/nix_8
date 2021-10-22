package ua.com.alevel.reversestring;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ReverseStringUtil {

    private ReverseStringUtil() { }

//usual reverse
    public static String reverse(String src) {
        char[] array = src.toCharArray();
        StringBuilder result = new StringBuilder();
        for (int i = array.length - 1; i >= 0; i--)
            result.append(array[i]);
        return result.toString();
    }

//reverse(save word order or not)
    public static String reverse(String src, boolean full) {
       if (full) {
            String[] word = src.split("\\s+");
            Matcher spaceFound = Pattern.compile("\\s+").matcher(src);
            StringBuilder result = new StringBuilder();
            for(int i = 0; i < word.length; i++){
                result.append(reverse(word[i]));
                if (spaceFound.find())result.append(" ");
            }
            return result.toString();
        }
        return reverse(src);
    }

//reverse of substring in string
    public static String reverse(String src, String dest, Boolean full) {
        String[] word = src.split(dest);
        if (word.length > 0) {
            StringBuilder result = new StringBuilder();
            Matcher matchword = Pattern.compile(dest).matcher(src);
            for(int i=0;i<word.length; i++){
                result.append(word[i]);
                if (matchword.find())
                    result.append(reverse(dest, full));
            }
            return result.toString();
        }
        return src;
    }

    //reverse between 2 indexes
    public static String reverse(String src, int firstIndex, int lastIndex, Boolean full) {
        StringBuilder result = new StringBuilder();
        if (firstIndex > lastIndex) return src+" reverse impossible(firt index > last index)";
        if (lastIndex > (src.length()))lastIndex = src.length();
        if (firstIndex > (src.length()))firstIndex = src.length();
        if (lastIndex < 0)lastIndex = 0;
        if (firstIndex < 0)firstIndex = 0;
        result.append(src,0, firstIndex).append(reverse(src.substring(firstIndex, lastIndex+1), full)).append(src.substring(lastIndex+1));
        return result.toString();
    }

//reverse between chars
    public static String reverse(String src, char first, char last, Boolean full) {
        int firstIndex=src.indexOf(first);
        int lastIndex=src.lastIndexOf(last);
        return reverse(src,firstIndex ,lastIndex, full);
    }

}