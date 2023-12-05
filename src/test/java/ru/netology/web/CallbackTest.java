package ru.netology.web;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CallbackTest {
    @Test
    void shouldTestWithValidFirstSurnameSecondName()  {
        open("http://localhost:9999");
        SelenideElement form=$(By.className("form"));
        form.$("[data-test-id=name] input").setValue("Петрова Ольга");
        form.$("[data-test-id=phone] input").setValue("+79988778899");
        form.$("[data-test-id=agreement]").click();
        form.$(By.className("button")).click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }

    @Test
    void shouldTestWithValidFirstNameSecondSurname()  {
        open("http://localhost:9999");
        SelenideElement form=$(By.className("form"));
        form.$("[data-test-id=name] input").setValue("Ольга Петрова");
        form.$("[data-test-id=phone] input").setValue("+79988778899");
        form.$("[data-test-id=agreement]").click();
        form.$(By.className("button")).click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }

    @Test
    void shouldTestWithValidHyphenatedName()  {
        open("http://localhost:9999");
        SelenideElement form=$(By.className("form"));
        form.$("[data-test-id=name] input").setValue("Петрова Анна-Мария");
        form.$("[data-test-id=phone] input").setValue("+79988778899");
        form.$("[data-test-id=agreement]").click();
        form.$(By.className("button")).click();
        $("[data-test-id=order-success]").shouldHave(Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }

//    @Test
//    void shouldTestWithNullNameAndSurname()  {
//        open("http://localhost:9999");
//        SelenideElement form=$(By.className("form"));
//        form.$("[data-test-id=name] input").setValue("");
//        form.$("[data-test-id=phone] input").setValue("+79988778899");
//        form.$("[data-test-id=agreement]").click();
//        form.$(By.className("button")).click();
//        $(By.className("input__sub")).shouldHave(Condition.exactText("Поле обязательно для заполнения"));
//
//    }

}
