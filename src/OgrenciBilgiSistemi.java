import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class OgrenciBilgiSistemi {
    static class Ders {
        int ders_kodu;
        String ders_ismi;
        int kayit_sayisi;

        public Ders(int ders_kodu, String ders_ismi) {
            this.ders_kodu = ders_kodu;
            this.ders_ismi = ders_ismi;
            this.kayit_sayisi = 0;
        }
    }

    static class Ogrenci {
        int ogrenci_numarasi;
        String isim;
        String soyisim;
        List<String> alinan_dersler;

        public Ogrenci(int ogrenci_numarasi, String isim, String soyisim) {
            this.ogrenci_numarasi = ogrenci_numarasi;
            this.isim = isim;
            this.soyisim = soyisim;
            this.alinan_dersler = new ArrayList<>();
        }
    }

    static class DersEklemeCikarma {
        Map<Integer, Ders> dersler;
        Map<Integer, Ogrenci> ogrenciler;

        public DersEklemeCikarma() {
            this.dersler = new HashMap<>();
            this.ogrenciler = new HashMap<>();

            // Öğrencileri başlangıçta tanımla
            ogrenci_ekle(1, "Eren", "Özcan");
            ogrenci_ekle(2, "Yiğit", "Geldi");
            ogrenci_ekle(3, "Onur", "Malay");
            ogrenci_ekle(4, "Osman", "Güzel");
            ogrenci_ekle(5, "Tuğba", "Çelikten");

            // Kursları başlangıçta tanımla
            ders_ekle(101, "Kalkülüs1");
            ders_ekle(102, "Kalkülüs2");
            ders_ekle(103, "Fizik1");
            ders_ekle(104, "Fizik2");
            ders_ekle(105, "Algoritma1");
            ders_ekle(106, "Algoritma2");
            ders_ekle(107, "Nesne");
            ders_ekle(108, "Gereksinim");
            ders_ekle(109, "WEB");
            ders_ekle(110, "İş Sağlığı");
        }

        public void ders_ekle(int ders_kodu, String ders_ismi) {
            Ders ders = new Ders(ders_kodu, ders_ismi);
            dersler.put(ders_kodu, ders);
        }

        public void ogrenci_ekle(int ogrenci_numarasi, String isim, String soyisim) {
            Ogrenci ogrenci = new Ogrenci(ogrenci_numarasi, isim, soyisim);
            ogrenciler.put(ogrenci_numarasi, ogrenci);
        }

        public void DersListesiniGoruntule() {
            System.out.println("Mevcut Kurslar:");
            for (Ders ders : dersler.values()) {
                System.out.println(ders.ders_kodu + ": " + ders.ders_ismi +
                        " - Kayıtlı öğrenciler: " + ders.kayit_sayisi);
            }
        }

        public void OgrenciKaydetme(int ogrenci_numarasi, int ders_kodu) {
            if (ogrenciler.containsKey(ogrenci_numarasi) && dersler.containsKey(ders_kodu)) {
                Ogrenci ogrenci = ogrenciler.get(ogrenci_numarasi);
                Ders ders = dersler.get(ders_kodu);

                ogrenci.alinan_dersler.add(ders.ders_ismi);
                ders.kayit_sayisi++;

                System.out.println(ogrenci.isim + " başarı ile " + ders.ders_ismi + "'e kaydolmuştur");
            } else {
                System.out.println("Geçersiz öğrenci ya da ders kodu");
            }
        }

        public void IstasitikleriGoruntule() {
            System.out.println("Ders kayıt durumu");
            for (Ders ders : dersler.values()) {
                System.out.println(ders.ders_ismi + " - kayıtlı öğrenci sayısı " + ders.kayit_sayisi);
            }
        }
    }

    public static void main(String[] args) {
        DersEklemeCikarma DersEklemeCikarma = new DersEklemeCikarma();
        Scanner scanner = new Scanner(System.in);

        while (true) {

            // Kullanıcı girişi
            System.out.print("öğrenci numarası giriniz: ");
            int ogrenci_numarasi = scanner.nextInt();

            // Kurs listesini göster
            DersEklemeCikarma.DersListesiniGoruntule();

            // Kurs seçimi
            System.out.print("Kayıt olmak istediğiniz dersin kodunu giriniz (çıkış için 0 girin): ");
            int ders_kodu = scanner.nextInt();

            if (ders_kodu == 0) {
                break;
            }

            // Öğrenciyi kursa kaydet
            DersEklemeCikarma.OgrenciKaydetme(ogrenci_numarasi, ders_kodu);
        }

        // İstatistikleri göster
        DersEklemeCikarma.IstasitikleriGoruntule();
    }
}