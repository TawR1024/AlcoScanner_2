package service;

import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

/**
 * Created by ilya-kulakov on 28.11.16.
 */
public class HtmlParserTest {
//    String input = "Наименование: Водка \"Грей Гуз\"</br>Алко-код: 0150367000002605631</br>Код вида: 200</br>Крепость: 40</br>Объем: 0.5</br></br>Производитель: Бакарди-Март.Пр. С.А.С.</br>FSRAR_ID: 050000003910</br>Полн. наим.: \"Бакарди-Мартини Продакшн С.А.С.\"</br>ИНН: </br>КПП: </br>Адрес: 11 Рут дю Лубарет, 16130 Гензак ла Паллю, Франция</br></br>Импортер: ООО \"Бакарди Рус\"</br>FSRAR_ID: 010000006144</br>Полн. наим.: Общество с ограниченной ответственностью \"Бакарди Рус\"</br>ИНН: 7706244940</br>КПП: 501703001</br>Адрес: РОССИЯ,143521,МОСКОВСКАЯ ОБЛ,Истринский р-н,Лучинское с/п,Давыдовское д,Дачная ул,стр. 2,литера Б,1 этаж, помещение № I, номер на плане 17</br>"
        public  static HtmlParser htmlParser;
    @BeforeClass
    public static void beforeClass(){
        String input = "\n" +
                "Наименование: Вино географического наименования \"Мерло\" сухое красное</br>Алко-код: 0177481000001526569</br>Код вида: 400</br>Крепость: 13.5</br>Объем: 0.75</br></br>Производитель: \"ВиньяСанПедроТарапаСА\"</br>FSRAR_ID: 050000012628</br>Полн. наим.: \"Винья Сан Педро Тарапака С.А.\"</br>ИНН: </br>КПП: </br>Адрес: Ав. Витакура 2670, Пизо 16, Коммуна Лас Кондес, Сантьяго, Чили.</br></br>Импортер: ООО \"БИГ\"</br>FSRAR_ID: 010000212051</br>Полн. наим.: Общество с ограниченной ответственностью \"БИГ\"</br>ИНН: 7727534914</br>КПП: 774345001</br>Адрес: РОССИЯ,,МОСКВА Г,,,,Коровинское ш, д. 35,, | стр. 4, здание назначение: нежилое, этаж 4, помещение № 1, комната № 26 (S=230,0 кв.м)</br>";
        htmlParser = new HtmlParser(input);
    }

    @Test
    public void prepareStirngTest() throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        Method method = HtmlParser.class.getDeclaredMethod("prepareStirng",void.class);
        method.setAccessible(true);
        method.invoke(htmlParser);
        String preparedStr =htmlParser.getBuffer();
        assertThat(preparedStr, notNullValue());
        assertEquals(preparedStr, "\n" +
                "Наименование: Вино географического наименования \"Мерло\" сухое красное \n" +
                "Алко-код: 0177481000001526569 \n" +
                "Код вида: 400 \n" +
                "Крепость: 13.5 \n" +
                "Объем: 0.75 \n" +
                " \n" +
                "Производитель: \"ВиньяСанПедроТарапаСА\" \n" +
                "FSRAR_ID: 050000012628 \n" +
                "Полн. наим.: \"Винья Сан Педро Тарапака С.А.\" \n" +
                "ИНН:  \n" +
                "КПП:  \n" +
                "Адрес: Ав. Витакура 2670, Пизо 16, Коммуна Лас Кондес, Сантьяго, Чили. \n" +
                " \n" +
                "Импортер: ООО \"БИГ\" \n" +
                "FSRAR_ID: 010000212051 \n" +
                "Полн. наим.: Общество с ограниченной ответственностью \"БИГ\" \n" +
                "ИНН: 7727534914 \n" +
                "КПП: 774345001 \n" +
                "Адрес: РОССИЯ,,МОСКВА Г,,,,Коровинское ш, д. 35,, | стр. 4, здание назначение: нежилое, этаж 4, помещение № 1, комната № 26 (S=230,0 кв.м) \n");

    }
}