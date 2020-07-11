package sample.kurs;

//import ru.jjd.base.lesson17.ditask.container.DIContainer;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Properties;
import java.util.Scanner;

public class Client {
    private final static String propertyFile = "kurs.properties";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя пользователя");
        String name = scanner.nextLine();//определение имени пользователя для чата
        System.out.println("Вводите сообщения");
        Properties properties = getConfig();//получение значений для создания сокета из property файла
        String ip = properties.getProperty("ip");
        int port = Integer.parseInt(properties.getProperty("port"));
        try {
            SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(ip, port));
            //Создание потока для считывания сообщений, приходящих с сервера
            new Thread(new ClientReaderThread(socketChannel)).start();
            //Создание потока для считывания сообщений из консоли и отправки их на сервер
            new Thread(new ClientWriterThread(socketChannel, name)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Properties getConfig(){//метод для считывания property файла
        Properties result = null;
//        try (InputStream input = DIContainer.class.getClassLoader().getResourceAsStream(propertyFile)){
//            result = new Properties();
//            result.load(input);
//        } catch (IOException e){
//            e.printStackTrace();
//        }
        return result;
    }
}

