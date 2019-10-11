package by.sci.laba3;

import java.util.Arrays;
import java.util.List;


public class Dictionary {
    private List<String> dictionary;

    public Dictionary() {
        dictionary = Arrays.asList(
                "Fire",
                "Viking",
                "Lion",
                "Colors",
                "Sign",
                "Hearts");
    }

    public Dictionary(List<String> dictionary) {
        this.dictionary = dictionary;
    }

    public List<String> getDictionary() {
        return dictionary;
    }

    public Integer getCount() {
        return dictionary.size();
    }

    public Boolean isEmpty() {
        return dictionary.isEmpty();
    }
}
