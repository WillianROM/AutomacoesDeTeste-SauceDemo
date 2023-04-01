package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TelaListaDeProdutos {
    private WebDriver driver;

    public TelaListaDeProdutos(WebDriver driver) {
        this.driver = driver;
    }

    public String verificarSeEstaNoSiteProducts(){
        return this.driver.findElement(By.cssSelector(".title")).getText();
    }



    public TelaCarrinhoDeCompra clicarNoBotaoAddToCartDoProdutoBackpack(){
        this.driver.findElement(By.cssSelector("button#add-to-cart-sauce-labs-backpack")).click();

        return new TelaCarrinhoDeCompra(this.driver);
    }

    public TelaCarrinhoDeCompra clicarNoBotaoCarrinhoDeCompra(){
        this.driver.findElement(By.cssSelector("a.shopping_cart_link")).click();

        return new TelaCarrinhoDeCompra(this.driver);
    }

}
