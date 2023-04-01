package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TelaCarrinhoDeCompra {
    private WebDriver driver;

    public TelaCarrinhoDeCompra(WebDriver driver) {
        this.driver = driver;
    }

    public String validarOValorDoBackpack(){
        return this.driver.findElement(By.cssSelector(".inventory_item_price")).getText();
    }

    public TelaDeCheckOut clicarNoBotaoCheckout(){
        this.driver.findElement(By.cssSelector("button#checkout")).click();

        return new TelaDeCheckOut(this.driver);
    }

}
