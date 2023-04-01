package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TelaLogin {
    private WebDriver driver;

    public TelaLogin(WebDriver driver){ /* 2º Prinipio do Page Objects - Tenha um construtor na classe que pegue um navegador de fora e coloque dentro do navegador*/
        this.driver = driver;
    }

    public TelaLogin informarOUsuario(String usuario){
        this.driver.findElement(By.cssSelector("input#user-name")).click();
        this.driver.findElement(By.cssSelector("input#user-name")).sendKeys(usuario);

        return this;
    }

    public TelaLogin informarASenha(String senha){
        this.driver.findElement(By.cssSelector("input#password")).click();
        this.driver.findElement(By.cssSelector("input#password")).sendKeys(senha);

        return this;
    }

    public TelaListaDeProdutos submeterFormularioDeLogin(){
        this.driver.findElement(By.cssSelector("input#login-button")).click();

        return new TelaListaDeProdutos(this.driver);
    }

    public TelaLogin submeterFormularioDeLoginComSenhaInvalida(){
        this.driver.findElement(By.cssSelector("input#login-button")).click();

        return this;
    }

    public String verificarSenhaIncorreta(){
        return this.driver.findElement(By.cssSelector("h3")).getText();
    }

}
