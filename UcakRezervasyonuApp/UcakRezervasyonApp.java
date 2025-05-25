// Paket ve importlar
import java.io.*;
import java.util.*;

// Uçak sınıfı
class Ucak {
    String model;
    String marka;
    String seriNo;
    int kapasite;

    public Ucak(String model, String marka, String seriNo, int kapasite) {
        this.model = model;
        this.marka = marka;
        this.seriNo = seriNo;
        this.kapasite = kapasite;
    }

    @Override
    public String toString() {
        return marka + " " + model + " - Seri No: " + seriNo + " - Kapasite: " + kapasite;
    }
}

// Lokasyon sınıfı
class Lokasyon {
    String ulke;
    String sehir;
    String havaalani;
    boolean aktif;

    public Lokasyon(String ulke, String sehir, String havaalani, boolean aktif) {
        this.ulke = ulke;
        this.sehir = sehir;
        this.havaalani = havaalani;
        this.aktif = aktif;
    }

    @Override
    public String toString() {
        return havaalani + " (" + sehir + ", " + ulke + ") - " + (aktif ? "Aktif" : "Pasif");
    }
}

// Uçuş sınıfı
class Ucus {
    Lokasyon lokasyon;
    String saat;
    Ucak ucak;
    List<Rezervasyon> rezervasyonlar = new ArrayList<>();

    public Ucus(Lokasyon lokasyon, String saat, Ucak ucak) {
        this.lokasyon = lokasyon;
        this.saat = saat;
        this.ucak = ucak;
    }

    public boolean rezerveEt(Rezervasyon r) {
        if (rezervasyonlar.size() < ucak.kapasite) {
            rezervasyonlar.add(r);
            return true;
        } else {
            return false;
        }
    }

    public int kalanKoltukSayisi() {
        return ucak.kapasite - rezervasyonlar.size();
    }

    @Override
    public String toString() {
        return ucak.toString() + "\n" + lokasyon.toString() + "\nSaat: " + saat + "\nKalan Koltuk: " + kalanKoltukSayisi();
    }
}

// Rezervasyon sınıfı
class Rezervasyon {
    Ucus ucus;
    String ad;
    String soyad;
    int yas;

    public Rezervasyon(Ucus ucus, String ad, String soyad, int yas) {
        this.ucus = ucus;
        this.ad = ad;
        this.soyad = soyad;
        this.yas = yas;
    }

    @Override
    public String toString() {
        return ad + " " + soyad + ", Yaş: " + yas + "\nUçuş Bilgisi:\n" + ucus.toString();
    }
}

public class UcakRezervasyonApp {
    static List<Ucak> ucaklar = new ArrayList<>();
    static List<Lokasyon> lokasyonlar = new ArrayList<>();
    static List<Ucus> ucuslar = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        verileriHazirla();

        boolean devam = true;
        while (devam) {
            System.out.println("\nUçuşlar:");
            for (int i = 0; i < ucuslar.size(); i++) {
                System.out.println((i + 1) + ". " + ucuslar.get(i));
            }
            System.out.print("\nRezerve etmek istediğiniz uçuş numarasını seçin: ");
            int secim = scanner.nextInt();
            scanner.nextLine();

            if (secim > 0 && secim <= ucuslar.size()) {
                Ucus secilenUcus = ucuslar.get(secim - 1);

                if (secilenUcus.kalanKoltukSayisi() == 0) {
                    System.out.println("Bu uçuşta boş koltuk kalmamış.");
                } else {
                    System.out.print("Adınız: ");
                    String ad = scanner.nextLine();
                    System.out.print("Soyadınız: ");
                    String soyad = scanner.nextLine();
                    System.out.print("Yaşınız: ");
                    int yas = scanner.nextInt();
                    scanner.nextLine();

                    Rezervasyon rezervasyon = new Rezervasyon(secilenUcus, ad, soyad, yas);
                    if (secilenUcus.rezerveEt(rezervasyon)) {
                        System.out.println("Rezervasyon başarılı!");
                        rezervasyonuKaydet(rezervasyon);
                    } else {
                        System.out.println("Rezervasyon başarısız. Uçakta boş koltuk kalmamış.");
                    }
                }
            } else {
                System.out.println("Geçersiz seçim.");
            }

            System.out.print("Yeni bir rezervasyon yapmak ister misiniz? (1: Evet, 0: Hayır): ");
            int cevap = scanner.nextInt();
            scanner.nextLine();
            devam = (cevap == 1);
        }

        System.out.println("Uygulama sonlandırıldı.");
    }

    static void verileriHazirla() {
        Ucak u1 = new Ucak("737", "Boeing", "SN123", 3);
        Lokasyon l1 = new Lokasyon("Türkiye", "İstanbul", "IST", true);
        Ucus ucus1 = new Ucus(l1, "10:00", u1);

        ucaklar.add(u1);
        lokasyonlar.add(l1);
        ucuslar.add(ucus1);
    }

    static void rezervasyonuKaydet(Rezervasyon r) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("rezervasyonlar.csv", true))) {
            writer.println(r.ad + "," + r.soyad + "," + r.yas + "," + r.ucus.ucak.seriNo + "," + r.ucus.saat);
        } catch (IOException e) {
            System.out.println("Dosyaya yazma hatası: " + e.getMessage());
        }
    }
}
