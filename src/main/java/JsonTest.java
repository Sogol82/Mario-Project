import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

public class JsonTest {
    public static void main(String args[]){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String st = "Hello World!";
        int[] array = {1,2,3,4,5,6};

         int[] readArray;
        try {
            FileWriter fileWriter = new FileWriter("string.json");

            objectMapper.writeValue(fileWriter, array);


            FileReader fileReader = new FileReader("string.json");
            readArray = objectMapper.readValue(fileReader,int[].class);

            System.out.println(Arrays.toString(readArray));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

