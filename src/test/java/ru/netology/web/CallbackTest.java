package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CallbackTest {
    @Test
    void shouldTestWithValidFirstSurnameSecondName() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id=name] input").setValue("Петрова Ольга");
        form.$("[data-test-id=phone] input").setValue("+79988778899");
        form.$("[data-test-id=agreement]").click();
        form.$(By.className("button")).click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }

    @Test
    void shouldTestWithValidFirstNameSecondSurname() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id=name] input").setValue("Ольга Петрова");
        form.$("[data-test-id=phone] input").setValue("+79988778899");
        form.$("[data-test-id=agreement]").click();
        form.$(By.className("button")).click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }

    @Test
    void shouldTestWithValidHyphenatedName() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id=name] input").setValue("Петрова Анна-Мария");
        form.$("[data-test-id=phone] input").setValue("+79988778899");
        form.$("[data-test-id=agreement]").click();
        form.$(By.className("button")).click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }

    @Test
    void shouldTestWithEmptyName() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id=name] input").setValue("");
        form.$("[data-test-id=phone] input").setValue("+79988778899");
        form.$("[data-test-id=agreement]").click();
        form.$(By.className("button")).click();
        $(By.className("input_invalid")).find(".input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
        String textColor = $(By.className("input_invalid")).find(".input__sub").getCssValue("color");
        assertEquals("rgba(255, 92, 92, 1)", textColor);

    }

    @Test
    void shouldTestWithEmptyPhone() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id=name] input").setValue("Ольга Петрова");
        form.$("[data-test-id=phone] input").setValue("");
        form.$("[data-test-id=agreement]").click();
        form.$(By.className("button")).click();
        $(By.className("input_invalid")).find(".input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
        String textColor = $(By.className("input_invalid")).find(".input__sub").getCssValue("color");
        assertEquals("rgba(255, 92, 92, 1)", textColor);
    }

    @Test
    void shouldTestWithEmptyCheckbox() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id=name] input").setValue("Ольга Петрова");
        form.$("[data-test-id=phone] input").setValue("+79988778899");
        form.$(By.className("button")).click();
        String textColor = $(By.className("checkbox__text")).getCssValue("color");
        assertEquals("rgba(255, 92, 92, 1)", textColor);

    }

    @Test
    void shouldTestWithNameInLatin() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id=name] input").setValue("Olga Petrova");
        form.$("[data-test-id=phone] input").setValue("+79988778899");
        form.$("[data-test-id=agreement]").click();
        form.$(By.className("button")).click();
        $(By.className("input_invalid")).find(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
        String textColor = $(By.className("input_invalid")).find(".input__sub").getCssValue("color");
        assertEquals("rgba(255, 92, 92, 1)", textColor);
    }

    @Test
    void shouldTestWithNameInNumericFormat() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id=name] input").setValue("1111111111");
        form.$("[data-test-id=phone] input").setValue("+79988778899");
        form.$("[data-test-id=agreement]").click();
        form.$(By.className("button")).click();
        $(By.className("input_invalid")).find(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
        String textColor = $(By.className("input_invalid")).find(".input__sub").getCssValue("color");
        assertEquals("rgba(255, 92, 92, 1)", textColor);
    }

    @Test
    void shouldTestWithNameWithApostrophe() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id=name] input").setValue("Д'артаньян Иванов");
        form.$("[data-test-id=phone] input").setValue("+79988778899");
        form.$("[data-test-id=agreement]").click();
        form.$(By.className("button")).click();
        $(By.className("input_invalid")).find(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
        String textColor = $(By.className("input_invalid")).find(".input__sub").getCssValue("color");
        assertEquals("rgba(255, 92, 92, 1)", textColor);
    }

    @Test
    void shouldTestWithNameWithSpecialCharacters() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id=name] input").setValue("М@рия Иванова");
        form.$("[data-test-id=phone] input").setValue("+79988778899");
        form.$("[data-test-id=agreement]").click();
        form.$(By.className("button")).click();
        $(By.className("input_invalid")).find(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
        String textColor = $(By.className("input_invalid")).find(".input__sub").getCssValue("color");
        assertEquals("rgba(255, 92, 92, 1)", textColor);
    }

    @Test
    void shouldTestWithNamAndWithoutSurname() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id=name] input").setValue("Мария");
        form.$("[data-test-id=phone] input").setValue("+79988778899");
        form.$("[data-test-id=agreement]").click();
        form.$(By.className("button")).click();
        $(By.className("input_invalid")).find(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
        String textColor = $(By.className("input_invalid")).find(".input__sub").getCssValue("color");
        assertEquals("rgba(255, 92, 92, 1)", textColor);
    }

    @Test
    void shouldTestWithSpacesInPhoneNumber() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id=name] input").setValue("Мария Иванова");
        form.$("[data-test-id=phone] input").setValue("+7 998 877 88 99");
        form.$("[data-test-id=agreement]").click();
        form.$(By.className("button")).click();
        $(By.className("input_invalid")).find(".input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
        String textColor = $(By.className("input_invalid")).find(".input__sub").getCssValue("color");
        assertEquals("rgba(255, 92, 92, 1)", textColor);
    }

    @Test
    void shouldTestWithParenthesisInPhoneNumber() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id=name] input").setValue("Мария Иванова");
        form.$("[data-test-id=phone] input").setValue("+7(998)8778899");
        form.$("[data-test-id=agreement]").click();
        form.$(By.className("button")).click();
        $(By.className("input_invalid")).find(".input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
        String textColor = $(By.className("input_invalid")).find(".input__sub").getCssValue("color");
        assertEquals("rgba(255, 92, 92, 1)", textColor);
    }

    @Test
    void shouldTestWithDashInPhoneNumber() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id=name] input").setValue("Мария Иванова");
        form.$("[data-test-id=phone] input").setValue("+7-998-877-88-99");
        form.$("[data-test-id=agreement]").click();
        form.$(By.className("button")).click();
        $(By.className("input_invalid")).find(".input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
        String textColor = $(By.className("input_invalid")).find(".input__sub").getCssValue("color");
        assertEquals("rgba(255, 92, 92, 1)", textColor);
    }

    @Test
    void shouldTestWithoutPlusInPhoneNumber() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id=name] input").setValue("Мария Иванова");
        form.$("[data-test-id=phone] input").setValue("89988778899");
        form.$("[data-test-id=agreement]").click();
        form.$(By.className("button")).click();
        $(By.className("input_invalid")).find(".input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
        String textColor = $(By.className("input_invalid")).find(".input__sub").getCssValue("color");
        assertEquals("rgba(255, 92, 92, 1)", textColor);
    }

    @Test
    void shouldTestIf12PhoneNumber() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id=name] input").setValue("Мария Иванова");
        form.$("[data-test-id=phone] input").setValue("+799988778899");
        form.$("[data-test-id=agreement]").click();
        form.$(By.className("button")).click();
        $(By.className("input_invalid")).find(".input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
        String textColor = $(By.className("input_invalid")).find(".input__sub").getCssValue("color");
        assertEquals("rgba(255, 92, 92, 1)", textColor);
    }

    @Test
    void shouldTestIf10PhoneNumber() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id=name] input").setValue("Мария Иванова");
        form.$("[data-test-id=phone] input").setValue("+7999887788");
        form.$("[data-test-id=agreement]").click();
        form.$(By.className("button")).click();
        $(By.className("input_invalid")).find(".input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
        String textColor = $(By.className("input_invalid")).find(".input__sub").getCssValue("color");
        assertEquals("rgba(255, 92, 92, 1)", textColor);
    }

    @Test
    void shouldTestIfPhoneNumberInLatin() {
        open("http://localhost:9999");
        SelenideElement form = $(By.className("form"));
        form.$("[data-test-id=name] input").setValue("Мария Иванова");
        form.$("[data-test-id=phone] input").setValue("qqqqqqq");
        form.$("[data-test-id=agreement]").click();
        form.$(By.className("button")).click();
        $(By.className("input_invalid")).find(".input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
        String textColor = $(By.className("input_invalid")).find(".input__sub").getCssValue("color");
        assertEquals("rgba(255, 92, 92, 1)", textColor);
    }


}


