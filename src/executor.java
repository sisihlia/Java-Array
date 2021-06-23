import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class executor {

    public boolean isUnique (String text) {
        //with O(n)
        int MAX_CHAR=128; // use 256 for extended ASCII
        if (text.length() > MAX_CHAR) return false;
        boolean [] check = new boolean [MAX_CHAR];
        for(int i=0; i<text.length(); i++) {
            char c=text.charAt(i);
            int x = (int) c;
            if (check[x]==true) return false;    /* If the value is already true, string has duplicate characters, return false */
            check[x]=true;
        }
       return true; //means all characters are unique
    }


    public boolean isUnique_n2(String text) {
        //with O(n2) with double iterations to compare each character
        for (int i=0; i<text.length(); i++) {
            for(int j =i+1; j < text.length(); j++) {
                if (text.charAt(i)==text.charAt(j)) return false;
            }
        }
        return true;
    }

    public boolean isPermutated (String s1, String s2) {
        /*with O(n2)
         A Permutation of a string is another string that contains same characters, only the order of characters can be different.
         For example, “abcd” and “dabc” are Permutation of each other */

        int n1=s1.length();
        int n2=s2.length();
        if (n1!=n2) return false; //if the length is not the same, they are not permutated

        //Convert the string to array
        char [] c1=s1.toCharArray();
        char [] c2=s2.toCharArray();
        //Sort both arrays
        Arrays.sort(c1);
        Arrays.sort(c2);
        //Compare the character between both sorted arrays
        for (int i=0; i<n1; i++) {
            if (c1[i]!=c2[i]) return false;
        }
        return true;
    }

    public String replaceSpacesWithPercentage20 (String s) {
        char [] ch=s.toCharArray();
        int numOfSpaces=0;

        System.out.println(ch.length + "============"+       s.length());
        System.out.println(Arrays.toString(ch));
        //count number of spaces
        for(int i=0; i<s.length(); i++) {
            if (ch[i] == ' ') numOfSpaces++;
        }
        /*create new array with the size of of original array and number of spaces multiplied by the number of characters to be replaced
        2 is because we count a slot for one space from the original array*/
        char [] myarr= new char [s.length() + numOfSpaces*2];

       // int myarr_size= myarr.length;
       // System.out.println("myarr size==="+ myarr_size);
//        int i=0;
//        while(i<14) {
//            if (ch[i] == ' ') {
//                System.out.println("i====else up "+i);
//                myarr[i] = '%';
//                myarr[i + 1] = '2';
//                myarr[i + 2] = '0';
//                System.out.println("i====else "+i);
//
//            }else {
//                myarr[i] = ch[i];
//                i++;
//            }
//
//        }
        int index=0, i=0;
        int trueLength=s.length();
        index=trueLength + numOfSpaces *2;
        for(i=trueLength-1;i>=0; i--) {
            if (ch[i] == ' ') {
                ch[index-1] ='0';
                ch[index-2] ='2';
                ch[index-3] ='%';
                index=index-3;
            }else {
                ch[index-1] = ch[i];   // got java.lang.ArrayIndexOutOfBoundsException: Index 17 out of bounds for length 14 !!! check later
                index--;
            }
        }
        System.out.println(Arrays.toString(ch));
        // instant solution
        char []toarr=(s.replaceAll("\\s","%20")).toCharArray();
        System.out.println(Arrays.toString(toarr));
        return "hello";
    }


    public boolean isPolidromepermutated (String s) {
       /* For a word to be palindrome, it should have the same character when traversed from beginning and end..so, it should be repeated atleast twice.
          If the word has odd number of total characters, it will a single character in the middle.
          So, we can check the length of the String and if its even, check if it has 2 off each character or not.
          If the length is odd, we need to check if it has 2 of each character, and only one character that isn’t repeated.*/
        char[] arr = s.replaceAll("\\s","").toLowerCase().toCharArray();
        Map<Character, Integer> mymap = new HashMap<>();
        //count the occurenace of each character
        for(char c:arr) {
            int count=1;
            if(mymap.containsKey(c)) {
                count=mymap.get(c);
                count++;
            }
            mymap.put(c,count);
        }
        boolean oddFound=false; // assume that there is no oddValue
        // iterate through the hashmap and return false if more than one odd found
        for(char ch:mymap.keySet()) {
            int value=mymap.get(ch);
            System.out.println("" + ch + ":" + value);
            if (value % 2 == 1) {
                if (oddFound) { // the second check when oddFound is true means there is more than one character that has an odd count
                    return false;
                }
                oddFound=true;
            }
        }
        return true;
    }

    public boolean oneEditReplace(String s1, String s2) {
        boolean foundDiff=false;
        for (int i=0; i<s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (foundDiff) {
                    return false;
                }
                foundDiff = true;
            }
        }
        return true;
    }

    public boolean isOneEditAway (String s1, String s2) {
        // Find lengths of given strings
        int n1=s1.length();
        int n2=s2.length();
        int count=0; //coount of edits

        // If difference between lengths is more than 1, then strings can't be at one distance
        if ((Math.abs(n1-n2)) > 1)  return false;
        int i=0, j=0;
        while(i<n1 && j<n2) {
            if (s1.charAt(i) != s2.charAt(j)) {
                if (count==1) return false;
                // If length of one string is more, then only possible edit is to remove a character
                if (n1>n2) i++;
                else if (n1<n2) j++;
                else {
                    /* If length is same, then only possible edit
                    is to  change a character. Therefore, move ahead in both strings */
                    i++;
                    j++;
                }
                // Increment count of edits
                count++;
            }
            else {
                i++;
                j++;
            }
        }
        // If last character is extra in any string
        if (i < n1 || j < n2)
            count++;
        return count == 1;
    }

    public String compressString (String s) {
        //String compressString;
        /*With String object on each concatenation, a new copy of the string is created, and the two strings are copied over, character
        by character. The first iteration requires us to copy x characters, The second iteration requires copying 2x
        characters. The third iteration requires 3x, and so on. The total time therefore isO(x + 2x + . . . + nx).

        StringBuilder can help you avoid this problem. StringBuilder simply creates a resizable array of all the strings,
        copying them back to a string only when necessary
       */
        StringBuilder compressString = new StringBuilder();
        int consecutiveCount=0;
        for (int i=0; i<s.length(); i++) {
            consecutiveCount++;
            if ((i == s.length()-1) || s.charAt(i)!=s.charAt(i+1)) {  // when it reacher the last element and the element is different from the previous one

                //compressString+=s.charAt(i)+""+consecutiveCount;
                compressString.append(s.charAt(i));
                compressString.append(consecutiveCount);
                consecutiveCount=0;
            }
        }
        return compressString.toString();
    }
    public static void main (String [] args) {
        executor ex= new executor();
        String input="Geeks";
        //System.out.println(ex.isPolidromepermutated("car racet"));
        //System.out.println(ex.isOneEditAway("pale","bae"));
        String exp="aabcccccaaab";
        ex.compressString(exp);
    }
}
