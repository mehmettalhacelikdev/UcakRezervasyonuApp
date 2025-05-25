# ✈️ Uçak Bilet Rezervasyon Konsol Uygulaması

Bu proje, Java OOP (Nesneye Dayalı Programlama) prensiplerine uygun olarak geliştirilmiş bir **uçak bilet rezervasyon sistemidir**. Konsol tabanlıdır ve kullanıcıların uçuşları listeleyip seçim yapmasını, ardından rezervasyon işlemi gerçekleştirmesini sağlar. Tüm veriler dosya tabanlı olarak (JSON) saklanır.

---

## 📌 Proje Özeti

Proje kapsamında dört ana model sınıfı oluşturulmuştur:

1. **Uçak (Airplane)**
   - Model
   - Marka
   - Seri No
   - Koltuk Kapasitesi

2. **Lokasyon (Location)**
   - Ülke
   - Şehir
   - Havaalanı
   - Durum (Aktif/Pasif)

3. **Uçuş (Flight)**
   - Kalkış Lokasyonu
   - Varış Lokasyonu
   - Uçuş Saati
   - Uçak

4. **Rezervasyon (Reservation)**
   - Uçuş
   - Ad, Soyad, Yaş
   - Rezervasyon No

---

## ⚙️ Teknik Detaylar

- **Programlama Dili:** Java 17
- **Yapılar:**
  - Abstract Class kullanımı (`BaseEntity`)
  - Interface kullanımı (`Savable`)
  - Class yapısı ile modelleme
- **Veri Kaydetme:** `JSON` dosya formatında saklanır.
- **OOP Kullanımı:** Tüm sınıflar ilişkisel olarak modellenmiş ve sorumluluklar ayrılmıştır.
- **Konsol Arayüzü:** Kullanıcı dostu bir menü sistemi ile etkileşim sağlanır.

---

## 🖥️ Konsol Ekranı Örneği

```plaintext
=== Uçak Bilet Rezervasyon Sistemine Hoş Geldiniz ===

1. Uçakları Listele
2. Lokasyonları Listele
3. Uçuşları Listele
4. Rezervasyon Yap
5. Rezervasyonları Listele
6. Çıkış

Seçiminiz: 
