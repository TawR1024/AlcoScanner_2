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
        String base_sys = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int systemBase = 36;

        /**
         * @param scanedCode Строка в формате BASE36
         */
        public PDF417Decoder(String scanedCode) {
            inputCode = scanedCode;
        }

        /**
         * @return finalCode Возвращате строку в BASE10
         */
        public String extractCode() {
            String finalCode;
            finalCode = inputCode.substring(4, 19);
            System.out.print(finalCode);

            double power = finalCode.length()-1;
            double found;
            long digit = 0;
            char arrChar[] = finalCode.toCharArray();

            for (int i = 0; i < arrChar.length; ++i) {
                found =  base_sys.indexOf(arrChar[i]);
                long sum = (long)(found * Math.pow(systemBase, power));
                digit += (sum);
                --power;
            }
            finalCode = Long.toString(digit);
            while (finalCode.length() < 19) {
                finalCode = "0".concat(finalCode);
            }
            return finalCode;
        }
}
//9223372036854775807
//0177481000001526569
    // 15N00001CJJRHTDA8MH1NS9111090190097471551531120421912173024240294724
// 16B00004ASDFASDFASDASDHFQ823RASHDUFASHEJDFJ23HJASD823467283ASFDASDFA