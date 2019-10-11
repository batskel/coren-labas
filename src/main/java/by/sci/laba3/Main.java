package by.sci.laba3;

import java.util.*;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) {
        System.out.println("Используемые слова:");
        Dictionary dictionary = new Dictionary();
        dictionary.getDictionary().stream().forEach(System.out::println);
        String codeDic = getDictionary(dictionary);
        System.out.println("\nСловарь: "+codeDic);
        int i = 0;
        while (i < 5) {
            i++;
            System.out.println("Сгенерированное слово:" + RandomWord(dictionary, codeDic));
        }

    }

    private static String RandomWord(Dictionary dictionary, String distinctStr) {
        if (dictionary.isEmpty())
            return "Нет значений в словаре.";
        int maxLength = dictionary.getDictionary().get(0).length();
        int minLength = dictionary.getDictionary().get(0).length();
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : dictionary.getDictionary()) {
            str = str.toLowerCase();
            stringBuilder.append(str);
            if (str.length() > maxLength)
                maxLength = str.length();
            else if (str.length() < minLength)
                minLength = str.length();
        }
        return OurRule(maxLength, minLength, distinctStr, dictionary);
    }


    private static String getDictionary(Dictionary dictionary) {
        if (dictionary.isEmpty())
            return "Нет значений в словаре.";
        StringBuilder stringBuilder = new StringBuilder();
        int maxLength = dictionary.getDictionary().get(0).length();
        int minLength = dictionary.getDictionary().get(0).length();
        for (String str : dictionary.getDictionary()) {
            str = str.toLowerCase();
            stringBuilder.append(str);
            if (str.length() > maxLength)
                maxLength = str.length();
            else if (str.length() < minLength)
                minLength = str.length();
        }
        System.out.println("Максимум: " + maxLength);
        System.out.println("Минимум: " + minLength);
        return getWordWithDistinctLetter(stringBuilder.toString());
    }


    private static String getWordWithDistinctLetter(String str) {
        Set<Character> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (!set.contains(c)) {
                set.add(c);
                sb.append(c);
            }
        }
        return sb.toString();
    }


    private static String OurRule(int max, int min, String distinctStr, Dictionary dictionary) {
        int lengthWord = Math.round((max + min - 2) / 2);
        Map<Character, Integer> startWord = new HashMap<>();
        Map<Character, Integer> endWord = new HashMap<>();

        for (String str : dictionary.getDictionary()) {
            char[] charStr = str.toCharArray();
            startWord.put(charStr[0],
                    (startWord.get(charStr[0]) != null ?
                            startWord.get(charStr[0]) + 1
                            : 1));

            endWord.put(charStr[charStr.length - 1], (endWord.get(charStr[charStr.length - 1]) != null ?
                    endWord.get(charStr[charStr.length - 1]) + 1
                    : 1));
        }


        Character startLetter = getLetter(startWord);
        Character endLetter = getLetter(endWord);

        char[] distinctChar = distinctStr.toCharArray();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(startLetter);

        for (int i = 1; i <= lengthWord - 1; i++) {
            stringBuilder.append(getLetter(distinctChar));

        }
        stringBuilder.append(endLetter);

        return stringBuilder.toString();
    }

    private static String mabyThisRule(int max, int min, Dictionary dictionary) {
        int lengthWord = Math.round((max + min) / 2);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i <= lengthWord; i++) {
            Map<Character, Integer> wordS = new HashMap<>();

            for (String str : dictionary.getDictionary()) {
                char[] charStr = str.toCharArray();
                if (charStr.length > i) {
                    wordS.put(charStr[i], (wordS.get(charStr[i]) != null ? wordS.get(charStr[i]) + 1 : 1));
                }
            }
            stringBuilder.append(getLetter(wordS));
        }
        return stringBuilder.toString();
    }

    private static Character getLetter(Map<Character, Integer> startWord) {
        Map<Character, Integer> startWordSorted =
                startWord.entrySet().stream()
                        .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v2, LinkedHashMap::new));


        Set<Character> characters = new HashSet<>();
        int maxAccount = -1;
        for (Map.Entry<Character, Integer> entry : startWordSorted.entrySet()) {
            if (maxAccount <= entry.getValue())
                maxAccount = entry.getValue();
            else
                break;
            characters.add(entry.getKey());
        }

        Random rand = new Random();
        int value = rand.nextInt(characters.size());

        return (Character) characters.toArray()[value];
    }

    private static Character getLetter(char[] distinctChar) {
        Random rand = new Random();
        int value = rand.nextInt(distinctChar.length);
        return distinctChar[value];
    }

}
