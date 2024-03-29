package service;

import javax.swing.*;

/**
 * Created by ilya-kulakov on 20.10.16.
 */
public class HtmlParser {

    /**Содержит входную строку*/
    private String inputBuffer;
    //**Массив с конснстантными размерами полей формы*/
       // @// TODO: 20.10.16 Изменить костыль
    private int lengthArr[];
    /**индекс начала чтения информации в поле*/
    private int startIndex=0;
    /**конечный индекс чтения информации в поле*/
    private int endIndex = 0;
    private String[] str = new String[17]; //{" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "}; //инициализация иначе не \

    /**
     *
     * @param input строка полученная из входного буфера
     */
    public  HtmlParser(String input){
        inputBuffer = input;
        lengthArr = new int[]{14,10,10,10,7,15,10,13,5,5,7,10,10,13,5,5,7};
    }

    /**Исключает из HTML текста теги типа <br></br>*/
    private void prepareStirng(){
        if(checkInfo()==false)
            return;
        inputBuffer = inputBuffer.replace("</br>", "\n");
      //  inputBuffer = inputBuffer.replace("И","И");
    }

    /**Извлечение информации из начального текста*/
    private void reedInfoFields(){
        int i = 0;
        /*@// TODO: 20.10.16 Проверить выражение снизу */
        //while(i < lengthArr.length && ((startIndex + lengthArr[i])<inputBuffer.length())){
        while(i < lengthArr.length){
            startIndex = lengthArr[i] + endIndex;
            endIndex = inputBuffer.indexOf("\n",startIndex);
            if(endIndex== startIndex){
                str[i] = "";
            }
            str[i] = inputBuffer.substring(startIndex, endIndex);
            endIndex +=1;
            ++i;
        }
    }

    public String[] parsing(){
        prepareStirng();
        reedInfoFields();
        return str;
    }

    private boolean checkInfo(){
        System.out.print(inputBuffer);
        if(inputBuffer.contains("Barcode") || inputBuffer.contains("\n")|| inputBuffer.contains("Алко-код продукции")){//
            JOptionPane.showMessageDialog(new JFrame(), "В базе ЕГАИС нет информации по данному товару",
                    "EGAIS dataBase Error",
                    JOptionPane.ERROR_MESSAGE);

            return  false;
        }else {
            return true;
        }
    }
}
