@dias
Feature: Hepsiburada Tablet Kategorisi UI Testi

  Scenario: En yüksek fiyatlı Apple tabletin sepete eklenmesi ve fiyat doğrulaması
    Given Kullanıcı www.hepsiburada.com adresine gider
    When Kullanıcı Tüm Kategoriler -> "Elektronik" -> "Tablet" kategorisine gider
    And Kullanıcı Marka filtresinden "Apple" seçer
    And Kullanıcı Ekran Boyutu filtresinden "13,2 inç" seçer
    And Kullanıcı sıralama filtresi kullanmadan en yüksek fiyatlı ürüne tıklar
    And Kullanıcı açılan ürün detay sayfasında Sepete ekle butonuna tıklar
    Then Kullanıcı ürünün sepete eklendiğini doğrular
    And Kullanıcı ürün fiyatının detay sayfasındaki fiyat ile aynı olduğunu doğrular
