package EgaisConnector;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by ilya-kulakov on 19.10.16.
 */
public class SendRequest {

    /**Строка с URL базовго ресурса, для запроса инофрмации*/
    private String baseURL = "http://www.xn--80affoam1c.xn--p1ai/api/testscan.php?barcode=";

    /**Строка - конкатенация базовой строки и кода запроса*/
    private String finalUrl;

    /**Сеттер конечного URL*/
    private void setPDF417code(String code){
        finalUrl = baseURL + code;
    }
    /**Конструктор
     * @param PDF417Code Строка содержащая считанный PDF417*/
    public SendRequest(String PDF417Code){
        setPDF417code(PDF417Code);
    }

    /**Запрашивает инфомрацию и усервера
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
