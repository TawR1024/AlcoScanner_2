package service;

import javax.swing.*;

/**
 * Created by ilya-kulakov on 20.10.16.
 * @author ilya-kulakov
 */
public class InputCorrector {

    private String rusAlpha = "йцукенгшщзхъфывапролджэячсмитьбю.";
    private String engAlpha = "qwertyuiop[]asdfghjkl;'zxcvbnm,./";
    private String inputCode;

    public  InputCorrector(String cyrilic){
        setInputCode(cyrilic);
        if (checkLength()==false){
            return;
        }
        correctInput();
    }


    private void setInputCode(String code){
        inputCode = code;
    }

    private void correctInput(){//TODO:изменить реализацию
        char[] ru = rusAlpha.toCharArray();
        char[] en = engAlpha.toCharArray();
        char[] code = inputCode.toCharArray();
        for (int i = 0; i < inputCode.length(); ++i)
            for (int j = 0; j < rusAlpha.length(); ++j) {
                if ((code[i] )==ru[j]){
                    code[i] = (en[j]);

                }
            }
            inputCode = String.copyValueOf(code);
    }

    public String getCorrecredCode(){
        return inputCode;
    }

    public boolean checkLength(){
        System.out.print(inputCode.length());
        if(inputCode.length()<68){
            JOptionPane.showMessageDialog(new JFrame(), "Код продукта считан неверно.\n" +
                            " Повторите ввод.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        inputCode = null;
        return false;
    }
}
