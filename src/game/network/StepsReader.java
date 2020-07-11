package game.network;

import game.ChipColor;
import game.GameDice;

import java.io.IOException;

public class StepsReader implements Runnable{
    private Connection connection1;
    private Connection connection2;
    private int whiteDice;
    private int blackDice;

    public StepsReader(Connection connection1, Connection connection2) {
        this.connection1 = connection1;
        this.connection2 = connection2;
        whiteDice = 0;
        blackDice = 0;
    }

    private void gameStart(){

        while (whiteDice == blackDice) {
            whiteDice = GameDice.diceThrow();
            blackDice = GameDice.diceThrow();
        }
        InitialSettings initialSettings1 = new InitialSettings(ChipColor.WHITE, whiteDice, blackDice);
        InitialSettings initialSettings2 = new InitialSettings(ChipColor.BLACK, whiteDice, blackDice);

        try {
            connection1.getOutputStream().writeObject(initialSettings1);
            connection2.getOutputStream().writeObject(initialSettings2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loop(){
        ExchangeStep exchangeStep;
        if (whiteDice > blackDice){
            while (!Thread.currentThread().isInterrupted()){
                try {
                    exchangeStep = (ExchangeStep) connection1.getInputStream().readObject();
                    connection2.getOutputStream().writeObject(exchangeStep);
                    exchangeStep = (ExchangeStep) connection2.getInputStream().readObject();
                    connection1.getOutputStream().writeObject(exchangeStep);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        } else {
            while (!Thread.currentThread().isInterrupted()){
                try {
                    exchangeStep = (ExchangeStep) connection2.getInputStream().readObject();
                    connection1.getOutputStream().writeObject(exchangeStep);
                    exchangeStep = (ExchangeStep) connection1.getInputStream().readObject();
                    connection2.getOutputStream().writeObject(exchangeStep);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    @Override
    public void run() {
        gameStart();
        loop();
        try {
            connection1.close();
            connection2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
