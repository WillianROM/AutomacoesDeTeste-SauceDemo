package modulos.produtos;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import paginas.TelaLogin;

import java.time.Duration;

@DisplayName("Testes Web no site saucedemo")
public class SauceDemoTests {
    private WebDriver driver;

    @BeforeEach
    public void beforeEach(){
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        this.driver = new ChromeDriver(options);



        // Vou maximizar a tela
        this.driver.manage().window().maximize();

        // Vou definir um tempo de espera padrão de 5 segundos
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Navegar para a página da Lojinha Web
        this.driver.get("https://www.saucedemo.com/");
    }

    @Test
    @DisplayName("Acessar o site com sucesso")
    public void testLoginRealizadoComSucesso(){

        String mensagemApresentada = new TelaLogin(driver)
                .informarOUsuario("standard_user")
                .informarASenha("secret_sauce")
                .submeterFormularioDeLogin()
                .verificarSeEstaNoSiteProducts();


        Assertions.assertEquals("Products", mensagemApresentada);
    }


    @Test
    @DisplayName("Acessar o site com senha invalida")
    public void testLoginComSenhaInvalida(){

        String mensagemApresentada = new TelaLogin(driver)
                .informarOUsuario("standard_user")
                .informarASenha("123456789")
                .submeterFormularioDeLoginComSenhaInvalida()
                .verificarSenhaIncorreta();

        Assertions.assertEquals("Epic sadface: Username and password do not match any user in this service", mensagemApresentada);
    }


    @Test
    @DisplayName("Acessar o site com usuario em branco")
    public void testLoginComLoginEmBranco(){

        String mensagemApresentada = new TelaLogin(driver)
                .informarOUsuario("")
                .informarASenha("secret_sauce")
                .submeterFormularioDeLoginComSenhaInvalida()
                .verificarSenhaIncorreta();

        Assertions.assertEquals("Epic sadface: Username is required", mensagemApresentada);
    }

    @Test
    @DisplayName("Acessar o site com a senha em branco")
    public void testLoginComSenhaEmBranco(){

        String mensagemApresentada = new TelaLogin(driver)
                .informarOUsuario("standard_user")
                .informarASenha("")
                .submeterFormularioDeLoginComSenhaInvalida()
                .verificarSenhaIncorreta();

        Assertions.assertEquals("Epic sadface: Password is required", mensagemApresentada);
    }

    @Test
    @DisplayName("Validar o preço do Backpack")
    public void testValidarOPrecoDoBackPack(){

        String mensagemApresentada = new TelaLogin(driver)
                .informarOUsuario("standard_user")
                .informarASenha("secret_sauce")
                .submeterFormularioDeLogin()
                .clicarNoBotaoAddToCartDoProdutoBackpack()
                .validarOValorDoBackpack();

        Assertions.assertEquals("$29.99", mensagemApresentada);
    }


    @Test
    @DisplayName("Validar mensagem da tela de checkout sem informar o Postal Code")
    public void testValidarMensagemDaTelaDeCheckoutSemInformarOPostalCode(){

        String mensagemApresentada = new TelaLogin(driver)
                .informarOUsuario("standard_user")
                .informarASenha("secret_sauce")
                .submeterFormularioDeLogin()
                .clicarNoBotaoCarrinhoDeCompra()
                .clicarNoBotaoCheckout()
                .informarPrimeiroNome("Maria")
                .InformarUltimoNome("Silva")
                .clicarNoBotaoContinueParaValidarMensagemDeErro()
                .validarMensagemDeAusenciaDePostalCode();

        Assertions.assertEquals("Error: Postal Code is required", mensagemApresentada);
    }


    @AfterEach
    public void afterEach(){
        this.driver.quit();
    }
}
