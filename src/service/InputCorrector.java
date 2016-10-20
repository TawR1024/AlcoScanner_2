package service;

/**
 * Created by ilya-kulakov on 20.10.16.
 */
public class InputCorrector {

    private String rusAlpha = "йцукенгшщзхъфывапролджэячсмитьбю.";
    private String engAlpha = "qwertyuiop[]asdfghjkl;'zxcvbnm,./";
    private String inputCode;

    public InputCorrector(String cyrilic){
        setInputCode(cyrilic);
        correctInput();
    }


    private void setInputCode(String code){
        inputCode = code;
    }

    private void correctInput(){
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
}
