package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;

public class StepDefinitions extends HomePage {


    @Given("Kullanıcı www.hepsiburada.com adresine gider")
    public void kullanıcı_www_hepsiburada_com_adresine_gider() {
        openMainURL();
    }

    @When("Kullanıcı Tüm Kategoriler -> {string} -> {string} kategorisine gider")
    public void kullanıcıTümKategorilerKategorisineGider(String name1, String name2) {
        goToMain(name1);
        goToSub(name2);
    }

    @And("Kullanıcı Marka filtresinden {string} seçer")
    public void kullanıcıMarkaFiltresindenSeçer(String name) {
        filterBrand(name);
    }

    @And("Kullanıcı Ekran Boyutu filtresinden {string} seçer")
    public void kullanıcıEkranBoyutuFiltresindenSeçer(String size) {
        filterScreenSize(size);
//        acceptCookies();
    }

    @And("Kullanıcı sıralama filtresi kullanmadan en yüksek fiyatlı ürüne tıklar")
    public void kullanıcıSıralamaFiltresiKullanmadanEnYüksekFiyatlıÜrüneTıklar() {
        highestPrice();
    }

    @And("Kullanıcı açılan ürün detay sayfasında Sepete ekle butonuna tıklar")
    public void kullanıcıAçılanÜrünDetaySayfasındaSepeteEkleButonunaTıklar() {
        addToChart();
        getDetayFiyat();
    }

    @Then("Kullanıcı ürünün sepete eklendiğini doğrular")
    public void kullanıcıÜrününSepeteEklendiğiniDoğrular() {
        confirmAddToCart();
    }

    @And("Kullanıcı ürün fiyatının detay sayfasındaki fiyat ile aynı olduğunu doğrular")
    public void kullanıcıÜrünFiyatınınDetaySayfasındakiFiyatIleAynıOlduğunuDoğrular() {
        goToCart();
        getSepetFiyat();
        comparePrices();
    }
}



