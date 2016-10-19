package Workbench;

/**
 * Created by ilya-kulakov on 19.10.16.
 *
 * Класс реализующий расщифровку строки
 * получаемой при чтении штрихкода формата PDF417
 * @author ilya-kulakov 
 * @version 2.0
 *
 */
public class PDF417Decoder {

        public String inputCode;
        private String base_sys = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        /**
         * @param scanedCode Строка в формате BASE36
         */
        public PDF417Decoder(String scanedCode) {
            inputCode = scanedCode;
        }

        /**
         * @return finalCode Возвращате строку в BASE10
         * @// TODO: 19.10.16 Проверить алгоритм вычисления. При извлечении код есть ошибка в конечных разрядах
         */
        public String extractCode() {
            String finalCode;
            finalCode = inputCode.substring(4, 19);

            int power = finalCode.length()-1;
            int found = 0;
            long digit = 0;
            char arrChar[] = finalCode.toCharArray();

            for (int i = 0; i < finalCode.length(); ++i) {
                found = base_sys.indexOf(arrChar[i]);
                digit += found * Math.pow(36, power);
                --power;
            }
            finalCode = Long.toString(digit);
            while (finalCode.length() < 19) {
                finalCode = "0".concat(finalCode);
            }
            return finalCode;
        }
}
