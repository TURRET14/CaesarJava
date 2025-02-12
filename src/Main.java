import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner UserInput = new Scanner(System.in);
        System.out.print("Введите Название Исходного Файла: ");
        String FileName = UserInput.nextLine();
        if (FileName.isEmpty()) {
            System.out.print("Введено Некорректное Название Исходного Файла!");
            return;
        }
        System.out.print("Введите Название Выходного Файла: ");
        String OutputFileName = UserInput.nextLine();
        if (OutputFileName.isEmpty()) {
            System.out.print("Введено Некорректное Название Выходного Файла!");
            return;
        }

        System.out.print("Введите Ключ (Число Сдвига): ");
        int Shift = UserInput.nextInt();

        ArrayList<Character> EnglishLetters = new ArrayList<>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'));
        ArrayList<Character> RussianLetters = new ArrayList<>(Arrays.asList('А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я'));

        try(BufferedReader Reader = new BufferedReader(new FileReader(FileName)); BufferedWriter Writer = new BufferedWriter(new FileWriter(OutputFileName))) {
            int NextChar = Reader.read();
            while (NextChar != -1) {
                boolean IsUpperCase = false;
                if (Character.isUpperCase((char)NextChar)) {
                    IsUpperCase = true;
                }
                if (EnglishLetters.contains(Character.toUpperCase((char)NextChar))) {
                    int CurrentShift = Shift % 26;
                    int NewCharID = EnglishLetters.indexOf(Character.toUpperCase((char)NextChar)) + CurrentShift;
                    if (NewCharID > 25) {
                        NewCharID = NewCharID - 26;
                    }
                    else if (NewCharID < 0) {
                        NewCharID = 26 + NewCharID;
                    }
                    if (IsUpperCase) {
                        Writer.write(EnglishLetters.get(NewCharID));
                    }
                    else {
                        Writer.write(Character.toLowerCase(EnglishLetters.get(NewCharID)));
                    }
                }
                else if (RussianLetters.contains(Character.toUpperCase((char)NextChar))) {
                    int CurrentShift = Shift % 33;
                    int NewCharID = RussianLetters.indexOf(Character.toUpperCase((char)NextChar)) + CurrentShift;
                    if (NewCharID > 32) {
                        NewCharID = NewCharID - 33;
                    }
                    else if (NewCharID < 0) {
                        NewCharID = 33 + NewCharID;
                    }
                    if (IsUpperCase) {
                        Writer.write(RussianLetters.get(NewCharID));
                    }
                    else {
                        Writer.write(Character.toLowerCase(RussianLetters.get(NewCharID)));
                    }
                }
                else {
                    Writer.write((char)NextChar);
                }

                NextChar = Reader.read();
            }
        }
        catch (FileNotFoundException Ex) {
            System.out.print("Указанный Исходный Файл Не Найден!");
            return;
        }
        catch (IOException Ex) {
            System.out.print("Произошла Ошибка Чтения/Записи!");
            return;
        }
    }
}