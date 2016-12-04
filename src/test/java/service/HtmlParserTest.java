package service;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ilya-kulakov on 28.11.16.
 */
public class HtmlParserTest {
    String[] expected = {"Водка \"ВАЛЕНКИ СИЛЬВЕР\"",
            "0003719000001263202",
            "200",
            "40",
            "0.5",
            " ООО \"Торговый Дом \"Медведь\"",
            "010000212044",
            "Общество с ограниченной ответственностью \"Торговый Дом \"Медведь\"",
            "7115500115",
            "711545002",
            "РОССИЯ,301733,ТУЛЬСКАЯ ОБЛ,Кимовский р-н,муниципальное образование Пронское,Пронь п: здание основного производства,,лит А; спиртохранилище, лит Ш, Ш1; здание арт. скважины № 4, лит. Т; здание арт. скважины № 3 лит. Р; цех приготовления водки,лит. Н; административное здание, лит. Е; нежилое здание - ремонтно-техническая база, лит. Б; нежилое здание - склад № 2, лит. К; склад ТМЦ, лит. О,",
            " ",
            "",
            "",
            "",
            "",
            ""};
    static HtmlParser htmlParser;


    @BeforeClass
    public static void Setup(){
       String input = "Наименование: Водка \"ВАЛЕНКИ СИЛЬВЕР\"</br>Алко-код: 0003719000001263202</br>Код вида: 200</br>Крепость: 40</br>Объем: 0.5</br></br>Производитель: ООО "+
                "\"Торговый Дом \"Медведь\"</br>FSRAR_ID: 010000212044</br>Полн. наим.: Общество с ограниченной ответственностью \"Торговый Дом \"Медведь\"</br>ИНН: 7115500115</br>КПП:"+
                " 711545002</br>Адрес: РОССИЯ,301733,ТУЛЬСКАЯ ОБЛ,Кимовский р-н,муниципальное образование Пронское,Пронь п: здание основного производства,,лит А; спиртохранилище, лит Ш,"+
                " Ш1; здание арт. скважины № 4, лит. Т; здание арт. скважины № 3 лит. Р; цех приготовления водки,лит. Н; административное здание, лит. Е; нежилое здание - ремонтно-техническая"+
                " база, лит. Б; нежилое здание - склад № 2, лит. К; склад ТМЦ, лит. О,</br></br>Импортер: </br>FSRAR_ID: </br>Полн. наим.: </br>ИНН: </br>КПП: </br>Адрес: </br>";
         htmlParser = new HtmlParser(input);
        assertNotNull("Объект не создан", htmlParser);
    }
    @Test
    public void Testparsing() throws Exception {
        String[] result = htmlParser.parsing();
        System.out.print("\n");
        for (String str:result) {
            System.out.println(str);
        }
        System.out.println("res:"+ result);
        assertArrayEquals("Результат распарсили неверно!\n",expected, result);
    }


}