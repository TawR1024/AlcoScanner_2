package service;

/**
 *
 * Корректирует считанный код.
 * <p>Код акцизы может быть считан сканером или вписан руками {@see Workbench.Workcbench#PDF417codeField}</p>
 * <p>Корректируется код введённый кирилицей.</p>
 *
 * @author Ilya Kulakov Eltech/group/4308
 * @version 2.0 final
 */
public class InputCorrector {

    /**
     * Строка с кирилическими символами
     */
    private String rusAlpha = "йцукенгшщзхъфывапролджэячсмить";

    /**
     * Строка с латинскими символами эквивалентными русским {@see service.InputCorrector#rusAlpha}
     */
    private String engAlpha = "qwertyuiop[]asdfghjkl;'zxcvbnm";

    /**
     * Содержит строку переданную в конструктор {@see service.InputCorrector#InputCorrector}
     */
    private String inputCode;

    /**
     * Конструктор класса
     *
     * @param cyrilic строка содержащая Возможно кирилические символы.
     * Вызываыемы методы:
     *                <br>{@see WorkBench.Workbench#setInputCode переданная строка}</br>
     *                <br>{@see WorkBench.Workbench#correctInput корректирует полученную строку}</br>
     */
    public InputCorrector(String cyrilic){
        setInputCode(cyrilic);
        correctInput();
    }

    private void setInputCode(String code){
        inputCode = code;
    }

    /**
     * Заменяет все кирилические символы их клавиатурными эквивалентами
     */
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
}
