package com.example.programmerscatjava;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String poemText = readPoem(this, "poem.txt");


        String poemName = getPoemName(poemText);
        TextView poemNameTextView = findViewById(R.id.poem_name);
        poemNameTextView.setText(poemName);


        String poem = getPoemText(poemText);
        TextView poemTextView = findViewById(R.id.poem_text);
        poemTextView.setText(poem);
    }

    /**
     * Method finds and returns first paragraph, that will be poem name.
     *
     * @param poemText full poem text, where first paragraph means its name
     * @return first paragraph
     */
    private String getPoemName(String poemText) {
        StringBuilder stringBuilder = new StringBuilder();

        for (char c : poemText.toCharArray()) {
            if (c == '\n') {
                break;
            } else {
                stringBuilder.append(c);
            }
        }

        return stringBuilder.toString();
    }

    /**
     * Method that takes poem and returns its text without poem name.
     * Returns all text after firs - '\n' - char
     *
     * @param poemText full poem text, where first paragraph means poem name,
     *                 all text after first paragraph is poem text
     * @return all text after first '\n' (newline)
     */
    private String getPoemText(String poemText) {
        char[] chars = poemText.toCharArray();
        String result = null;

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '\n') {
                char[] c = new char[chars.length - i + 1]; //+1 it means do not include '\n' char

                //+1 and -1 means do not include '\n' char
                System.arraycopy(chars, i + 1, c, 0, chars.length - i - 1);
                result = new String(c);
                break;
            }
        }


        return result;
    }

    /**
     * Method that reads poem from file that situated in assets folder.
     *
     * @param activity current activity, that will show poem
     * @param poem     poem file name, must be situated in src/main/assets folder
     * @return poem as string
     */
    private String readPoem(Activity activity, String poem) {

        try (InputStream inputStream = activity.getAssets().open(poem)) {
            int currentByte;
            ArrayList<Byte> bytes = new ArrayList<>();


            while ((currentByte = inputStream.read()) > -1) {
                bytes.add((byte) currentByte);
            }


            byte[] b = new byte[bytes.size()];

            for (int i = 0; i < bytes.size(); i++) {
                b[i] = bytes.get(i);
            }


            return new String(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
