package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TelaDeCheckOut {
    private WebDriver driver;

    public TelaDeCheckOut(WebDriver driver) {
        this.driver = driver;
    }

    public TelaDeCheckOut informarPrimeiroNome(String primeiroNome){
        this.driver.findElement(By.cssSelector("input#first-name")).click();
        this.driver.findElement(By.cssSelector("input#first-name")).sendKeys(primeiroNome);

        return this;
    }

    public TelaDeCheckOut InformarUltimoNome(String ultimoNome){
        this.driver.findElement(By.cssSelector("input#last-name")).click();
        this.driver.findElement(By.cssSelector("input#last-name")).sendKeys(ultimoNome);

        return this;
    }

    public TelaDeCheckOut clicarNoBotaoContinueParaValidarMensagemDeErro(){
        this.driver.findElement(By.cssSelector("input#continue")).click();
        return this;
    }

    public String validarMensagemDeAusenciaDePostalCode(){
        return this.driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
    }

}
