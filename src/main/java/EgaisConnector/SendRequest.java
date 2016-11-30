package EgaisConnector;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/*
    Created by ilya-kulakov on 19.10.16.
 */
/**
 *
 *
 * Посылает запрос к общей базе, заправшивая, товар по коду акцизной марки.
 * @author Ilya Kulakov eltech/group/4308 10/19/16
 * @version  3.1
 *
 */
public class SendRequest{

    /**Содержит сформированый URL */
    private String finalUrl;

    /**Сеттер конечного URL*/
    private void setPDF417code(String code){
        String baseURL = "http://www.xn--80affoam1c.xn--p1ai/api/testscan.php?barcode=";
        finalUrl = baseURL + code;
    }

    /**Конструктор
     * @param PDF417Code Строка содержащая считанный PDF417
     * */
    public SendRequest(String PDF417Code){
        setPDF417code(PDF417Code);
    }

    /**Запрашивает инфомрацию и усервера.
     *  Ссылка для запроса формируется {@see EgaisConnector.SendRequest#finalUrl}
     * Создаёт входной буфер. Буфер содержит строку - ответ.
     *<p>{@code BufferedReader inputBuffer = new BufferedReader(new InputStreamReader(egais.openStream(),"UTF-8"));}</p>
     * Кодировка в конце обеспечивает правильную интерпритацию символов. Ответ от сервера приходит в  KOi8
     *
     * @return inputLine Строка содержащая HTML ответ сервера. теги html и body удалены*/
    public String getInfo()throws Exception{
        URL egais = new URL(finalUrl);
        //кодировка выставлена верно
        BufferedReader inputBuffer = new BufferedReader(new InputStreamReader(egais.openStream(),"UTF-8"));
        String inputLine;
        inputLine = inputBuffer.readLine();
        inputBuffer.close();
        return inputLine;
    }

}
