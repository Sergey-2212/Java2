package Lesson2;

public class CheckArray {
    public static void main(String[] args) {
        String[][] stringArray =
                {{"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12",},
                {"14", "15", "16", "Seventeen"}};

        try {
            System.out.println(isArrayOk(stringArray));

        } catch (MyArraySizeException e) {
            System.out.println(e.getMessage());
        } catch (MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

    }

    public static int isArrayOk (String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (array.length != 4){ throw new MyArraySizeException("Incorrect up size");}

        for (String[] example : array) {
            if (example.length != 4) {throw new MyArraySizeException("Incorrect down size");}
        }
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {

                try {
                    count += Integer.parseInt(array[i][j]);

                } catch (Exception e) {
                    throw new MyArrayDataException("Wrong data in point [" + i + "][" + j + "].");
                }
            }
        }
        return count;
    }


}
