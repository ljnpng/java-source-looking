package io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

public class IoTest {

    @Test
    public void test_string_to_file() throws Exception{
        String str = "Hello World";
        String fileName = "src/test/resources/io/StringToFile.txt";
        byte[] bytes = str.getBytes();
        try (OutputStream out = new FileOutputStream(fileName)) {
            out.write(bytes);
        }

        try (InputStream in = new FileInputStream(fileName)) {
            StringBuilder result = new StringBuilder();
            int data = in.read();
            while (data != -1) {
                result.append((char) data);
                data = in.read();
            }
            Assertions.assertEquals(str, result.toString());
        }
    }

    @Test
    void test_string_to_file_decorate_with_buffer() throws Exception {
        String str = "Hello World";
        String fileName = "src/test/resources/io/StringToFile.txt";
        byte[] bytes = str.getBytes();
        try (OutputStream out = new BufferedOutputStream(new FileOutputStream(fileName))) {
            out.write(bytes);
        }

        try (InputStream in = new BufferedInputStream(new FileInputStream(fileName))) {
            StringBuilder result = new StringBuilder();
            int data = in.read();
            while (data != -1) {
                result.append((char) data);
                data = in.read();
            }
            Assertions.assertEquals(str, result.toString());

        }
    }


    @Test
    public void test_file_to_file() throws Exception{
        String fileName = "src/test/resources/io/FileToFile.txt";
        String fileName2 = "src/test/resources/io/FileToFile2.txt";
        try (InputStream in = new FileInputStream(fileName);
             OutputStream out = new FileOutputStream(fileName2)) {
            int data = in.read();
            while (data != -1) {
                out.write(data);
                data = in.read();
            }
        }

        try (InputStream in = new FileInputStream(fileName2)) {
            int data = in.read();
            while (data != -1) {
                System.out.print((char) data);
                data = in.read();
            }
        }
    }

    @Test
    public void test_file_to_buffer() throws Exception{
        String fileName = "src/test/resources/io/FileToFile.txt";
        try (InputStream in = new FileInputStream(fileName)) {
            byte[] buffer = new byte[1024];
            int length = in.read(buffer);
            while (length != -1) {
                for (int i = 0; i < length; i++) {
                    System.out.print((char) buffer[i]);
                }
                length = in.read(buffer);
            }
        }
    }

    @Test
    void test_Data_To_File_To_Data() throws Exception {
        String fileName = "src/test/resources/io/DataToFile.txt";
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(fileName))) {
            out.writeBoolean(true);
            out.writeByte((byte) 1);
            out.writeChar('A');
            out.writeDouble(3.14159);
            out.writeFloat(2.71828f);
            out.writeInt(123456789);
            out.writeLong(1234567890L);
            out.writeShort((short) 12345);
            out.writeUTF("Hello World");
        }

        try (DataInputStream in = new DataInputStream(new FileInputStream(fileName))) {
            Assertions.assertTrue(in.readBoolean());
            Assertions.assertEquals(1, in.readByte());
            Assertions.assertEquals('A', in.readChar());
            Assertions.assertEquals(3.14159, in.readDouble());
            Assertions.assertEquals(2.71828f, in.readFloat());
            Assertions.assertEquals(123456789, in.readInt());
            Assertions.assertEquals(1234567890L, in.readLong());
            Assertions.assertEquals(12345, in.readShort());
            Assertions.assertEquals("Hello World", in.readUTF());
        }
    }

    @Test
    void test_input_read_vs_readNBytes() throws Exception {
        String fileName = "src/test/resources/io/FileToFile.txt";
        try (InputStream in = new FileInputStream(fileName)) {
            byte[] buffer = new byte[1024];
            int length = in.read(buffer);
            while (length != -1) {
                for (int i = 0; i < length; i++) {
                    System.out.print((char) buffer[i]);
                }
                length = in.read(buffer);
            }
        }

        try (InputStream in = new FileInputStream(fileName)) {
            byte[] buffer = new byte[1024];
            // will read 1024 bytes
            // if the file is less than 1024 bytes, it will block until the file is closed
            // or the file is 1024 bytes
            int length = in.readNBytes(buffer, 0, 1024);
            while (length != -1) {
                for (int i = 0; i < length; i++) {
                    System.out.print((char) buffer[i]);
                }
                length = in.readNBytes(buffer, 0, 1024);
            }
        }
    }

    @Test
    void test_byte_array_input_stream() throws Exception {
        String str = "Hello World";
        byte[] bytes = str.getBytes();
        try (InputStream in = new ByteArrayInputStream(bytes)) {
            byte[] result = new byte[5];
            int read = in.read(result, 0, 5);
            Assertions.assertEquals(5, read);
            Assertions.assertEquals("Hello", new String(result));
        }
    }

    @Test
    void test_char_reader() {
        String str = "Hello World";
        try (Reader reader = new StringReader(str)) {
            char[] result = new char[5];
            int read = reader.read(result, 0, 5);
            Assertions.assertEquals(5, read);
            Assertions.assertEquals("Hello", new String(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    void test_object_to_file_to_object() throws Exception {
        String fileName = "src/test/resources/io/ObjectToFile.txt";
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(new Person("John", "Doe"));
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            Person o = (Person) in.readObject();
            Assertions.assertEquals("John", o.getFirstName());
            Assertions.assertEquals("Doe", o.getLastName());
        }
    }

    private static class  Person implements Serializable {
        private String firstName;
        private String lastName;

        public Person() {
        }

        public Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }
        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }
    }
}
