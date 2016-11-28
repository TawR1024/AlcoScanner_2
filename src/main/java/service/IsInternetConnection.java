package service;

import javax.swing.*;

/**
 * Created by ilya-kulakov on 19.10.16.
 *
 * @author ilya-kulakov
 * @version 2.1
 * @// TODO: 19.10.16 Создаёться более 2х объектов паттерна Singleton
 *
 * Класс осуществляет проверку доступности резурса ЕГАИС или состония подключения к интернету.
 */
public  class IsInternetConnection implements Runnable {
    /**
     *
     * Содержит адрес ресурса доступность которого следует проверить
     *
     * */
    private static String host;
    /**
     * IP корнеового DNS сервера
     * */
    private static  String rootDNSip = "198.41.0.4";
    /**
     * Строка с сообзением об ошибке*
     */
    private final  String egaisError = "Ресурс егаисик.рф в данный момент не доступен.\n Попробуйте позже";
    /**Строка с с сообщение об ошибке*/
    private final String conectionErr = " Вданный момент отсутсвует интернет соединение.\nРабота может быть затруднена.\n" +
            "Обратитесь к системному администратору";
    /**Строка содержащая текущее сообщение об ошибке, в зависимости от парметров проверки*/
    private  String errorMessg;
    /**Поле сосдежит статус состояния проверяемого ресурса*/
    private boolean connectionAvaible;

    private static IsInternetConnection ourInstance = new IsInternetConnection();

    public static IsInternetConnection getInstance() {
        return ourInstance;
    }

    /**Устанавливает адерс проверяемого ресурса.
     * @param domain если параметр пустой, то устанавливается адрес корневого DNS
     *               иначе устанавливается адрес переданный как параметр
     * */
    private void setDomain(String[] domain){

        if(domain.length == 0){
            host = rootDNSip;
            errorMessg = conectionErr;
        }else{
            host = domain[0];
            errorMessg = egaisError;
        }
    }
    /**Конструктор
     * @param hostUrl Strig содержит адрес проверяемого ресурса. Может быть пустым*/
    public IsInternetConnection(String ... hostUrl){
        if(getInstance()!=null){
          //  System.out.print("Instance Error. More then one object detected");
        }
        setDomain(hostUrl);
    }

    /**Пингует 3 раза нужный ресурс. Для пинга запускается процесс в командной строке
     * Платформонезависим отслеживается вплатформа выполнения.
     * Если процесс не вернул ошибку - нет пинга
     * @return true/false в зависимости от доступности ресурса.*/
    private static boolean isReachableByPing() {
        try{
            String cmd = "";
            if(System.getProperty("os.name").startsWith("Windows")) {
                // For Windows
                cmd = "ping -n 3 " + host;
            } else {
                // For Linux and OSX
                cmd = "ping -c 3 " + host;
            }
            Process myProcess = Runtime.getRuntime().exec(cmd);
            myProcess.waitFor();

            if(myProcess.exitValue() == 0) {
                return true;
            } else {
                return false;
            }
        } catch( Exception e ) {
            e.printStackTrace();
            return false;
        }
    }

    /**Устанавливает значение @var connectionAvaible*/
    private void checkConnection(){
        if(isReachableByPing() == false){
            connectionAvaible = false;
        }
        else{
            connectionAvaible = true;
        }
    }

    /**Геттер возвращает состояние ресурса*/
    private boolean getStatus(){
        return connectionAvaible;
    }

    /**Выводит ошибку если сервис не доступе
     * @todo Добавить проброс исключения, для корректного завершения выполнения следующего кода.*/
    public void connectionStatus(){
        if(getStatus()==false){
            JOptionPane.showMessageDialog(new JFrame(),errorMessg,
                    "No Internet Connection",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
    /**Перегруженный метод класса Runnable*/
    public void run() {
        checkConnection();
        connectionStatus();
    }


}
