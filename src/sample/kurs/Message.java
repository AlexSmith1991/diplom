package sample.kurs;

import java.io.Serializable;

//класс для создания сообщений, которыми будут обмениваться клиент и сервер
public class Message implements Serializable {
    private String name;
    private String text;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
