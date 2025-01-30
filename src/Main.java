import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner UserInput = new Scanner(System.in);
        System.out.print("Введите Название Файла: ");
        String FileName = UserInput.nextLine();
        FileInputStream TextFile;
        ArrayList<Character> FileContents = new ArrayList<>();
        try {
            TextFile = new FileInputStream(FileName);
            InputStreamReader Reader = new InputStreamReader(TextFile, "UTF-8");
            int NextChar = Reader.read();
            while (NextChar != -1) {
                FileContents.add((char)NextChar);
                NextChar = Reader.read();
            }
        }
        catch (FileNotFoundException Ex) {
            System.out.print("FileNotFoundException");
            return;
        }
        catch (IOException Ex) {
            System.out.print("IO Exception");
            return;
        }

        System.out.print("Введите Ключ (Число Сдвига): ");
        int Shift = UserInput.nextInt();

        ArrayList<Character> EnglishLetters = new ArrayList<>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G',
                'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S',
                'T', 'U', 'V', 'W', 'X', 'Y', 'Z'));
        ArrayList<Character> RussianLetters = new ArrayList<>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G',
                'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S',
                'T', 'U', 'V', 'W', 'X', 'Y', 'Z'));

        ArrayList<Character> Encrypted = new ArrayList<>();
        for (int Count = 0; Count < FileContents.size(); Count = Count + 1) {

            if (EnglishLetters.contains(Character.toUpperCase(FileContents.get(Count)))) {
                int CurrentShift = Shift % 26;
                int NewCharID = EnglishLetters.indexOf(Character.toUpperCase(FileContents.get(Count))) + CurrentShift;
                if (NewCharID > 25) {
                    NewCharID = NewCharID - 26;
                }
                else if (NewCharID < 0) {
                    NewCharID = 26 + NewCharID;
                }
                Encrypted.add(EnglishLetters.get(NewCharID));
                System.out.print(EnglishLetters.get(NewCharID));

            }
            else if (RussianLetters.contains(Character.toUpperCase(FileContents.get(Count)))) {
                int CurrentShift = Shift % 33;
                int NewCharID = RussianLetters.indexOf(Character.toUpperCase(FileContents.get(Count))) + CurrentShift;
                if (NewCharID > 32) {
                    NewCharID = NewCharID - 33;
                }
                else if (NewCharID < 0) {
                    NewCharID = 33 + NewCharID;
                }
                Encrypted.add(RussianLetters.get(NewCharID));
                System.out.print(RussianLetters.get(NewCharID));
            }
        }
    }
}