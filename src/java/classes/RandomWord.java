/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import entity.Word;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Melnikov
 */
public class RandomWord {
 
    public Word getRandomWord(List<Word> words){
        Random random = new Random();
        int randomIndex = random.nextInt(words.size());
        return words.get(randomIndex);
    }
}
