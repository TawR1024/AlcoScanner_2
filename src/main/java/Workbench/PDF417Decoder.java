package Workbench;

/**
 * Created by ilya-kulakov on 19.10.16.
 *
 * <p></>Класс реализующий расшифровку строки получаемой при чтении штрихкода формата PDF417</p>
 * @author Ilya Kulakov Eltech/group/4308 10/19/16
 * @version 2.0
 */
class PDF417Decoder {

    /**
     * Строка содержащая код в формате BASE36
     */
    private final String inputCode;

    /**
     * Констуктор класса PDF417Decoder
     *
     * @param scanedCode Строка в формате BASE36
     */
    public PDF417Decoder(String scanedCode) {
        inputCode = scanedCode;
    }

    /**
     * Извлекает из переданной на вход строки код продукции.
     * Возвращает строку содержащую отформатированный код продукта.
     *
     * @return finalCode Возвращате строку в BASE10
     */
    public String extractCode() {
        String finalCode;
        double found;
        long digit = 0;
        try {
            finalCode = inputCode.substring(4, 19);
            double power = finalCode.length() - 1;
            char arrChar[] = finalCode.toCharArray();

            for (int i = 0; i < arrChar.length; ++i) {
                /*
      Строка содержащая все цифры 36-ричной системы счисления
     */
                String base_sys = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                found = base_sys.indexOf(arrChar[i]);
                /*
      Основание системы
     */
                int systemBase = 36;
                long sum = (long) (found * Math.pow( systemBase, power));
                digit += (sum);
                --power;
            }
        } catch (ArrayIndexOutOfBoundsException outOfRange) {
                outOfRange.printStackTrace();
            return null;
        }
        finalCode = Long.toString(digit);
        while (finalCode.length() < 19) {
            finalCode = "0".concat(finalCode);
        }
        return finalCode;
    }
}