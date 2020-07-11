package sample.kurs;

//абстрактный класс для потоков
abstract public class KursThread implements Runnable {
    @Override
    public void run() {
        try {
            init();
            while (!isInterrupted()){
                loop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    protected void init() throws Exception{}//запускается при создании потока один раз
    protected void loop() throws Exception{}//запускается циклично пока поток не будет помечен для остановки
    protected void stop() throws Exception{}//запускается после остановки потока, предназначен для закрытия соединений
    protected Boolean isInterrupted(){
        return Thread.currentThread().isInterrupted();
    }
}
