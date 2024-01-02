import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OgrenciBilgiSistemi {

    static abstract class Ders {
        int ders_kodu;
        String ders_ismi;
        int kayit_sayisi;

        public Ders(int ders_kodu, String ders_ismi) {
            this.ders_kodu = ders_kodu;
            this.ders_ismi = ders_ismi;
            this.kayit_sayisi = 0;
        }

        abstract int getSene();
    }

    static class DersSene1 extends Ders {
        public DersSene1(int ders_kodu, String ders_ismi) {
            super(ders_kodu, ders_ismi);
        }

        @Override
        int getSene() {
            return 1;
        }
    }

    static class DersSene2 extends Ders {
        public DersSene2(int ders_kodu, String ders_ismi) {
            super(ders_kodu, ders_ismi);
        }

        @Override
        int getSene() {
            return 2;
        }
    }

    static abstract class Ogrenci {
        int ogrenci_numarasi;
        String isim;
        String soyisim;
        String bolum;

        public Ogrenci(int ogrenci_numarasi, String isim, String soyisim, String bolum) {
            this.ogrenci_numarasi = ogrenci_numarasi;
            this.isim = isim;
            this.soyisim = soyisim;
            this.bolum = bolum;
        }

        abstract int getSene();
    }

    static class OgrenciSene1 extends Ogrenci {
        public OgrenciSene1(int ogrenci_numarasi, String isim, String soyisim, String bolum) {
            super(ogrenci_numarasi, isim, soyisim, bolum);
        }

        @Override
        int getSene() {
            return 1;
        }
    }

    static class OgrenciSene2 extends Ogrenci {
        public OgrenciSene2(int ogrenci_numarasi, String isim, String soyisim, String bolum) {
            super(ogrenci_numarasi, isim, soyisim, bolum);
        }

        @Override
        int getSene() {
            return 2;
        }
    }

    static class DersTanimla {
        Map<Integer, Ders> dersler;

        public DersTanimla() {
            this.dersler = new HashMap<>();
            // Dersleri başlangıçta tanımla
            dersEkle(new DersSene1(101, "Kalkülüs1"));
            dersEkle(new DersSene1(102, "Kalkülüs2"));
            dersEkle(new DersSene1(103, "Fizik1"));
            dersEkle(new DersSene1(104, "Fizik2"));
            dersEkle(new DersSene1(105, "Algoritma1"));
            dersEkle(new DersSene1(106, "Algoritma2"));

            dersEkle(new DersSene2(201, "Nesne"));
            dersEkle(new DersSene2(202, "Gereksinim"));
            dersEkle(new DersSene2(203, "WEB"));
            dersEkle(new DersSene2(204, "İş Sağlığı"));
        }

        public void dersEkle(Ders ders) {
            dersler.put(ders.ders_kodu, ders);
        }

        public void dersListesiniGoruntule(int sene) {
            System.out.println("Mevcut Dersler:");
            for (Ders ders : dersler.values()) {
                if (ders.getSene() == sene) {
                    System.out.println(ders.ders_kodu + ": " + ders.ders_ismi + " - Kayıtlı öğrenciler: " + ders.kayit_sayisi);
                }
            }
        }
    }

    static class OgrenciTanimla {
        Map<Integer, Ogrenci> ogrenciler;

        public OgrenciTanimla() {
            this.ogrenciler = new HashMap<>();
            // Öğrencileri başlangıçta tanımla
            ogrenciEkle(new OgrenciSene1(1, "Eren", "Özcan", "Bilgisayar Mühendisliği"));
            ogrenciEkle(new OgrenciSene1(3, "Onur", "Malay", "Makine Mühendisliği"));
            ogrenciEkle(new OgrenciSene1(5, "Tuğba", "Çelikten", "Kimya Mühendisliği"));

            ogrenciEkle(new OgrenciSene2(2, "Yiğit", "Geldi", "Elektrik Mühendisliği"));
            ogrenciEkle(new OgrenciSene2(4, "Osman", "Güzel", "Endüstri Mühendisliği"));
        }

        public void ogrenciEkle(Ogrenci ogrenci) {
            ogrenciler.put(ogrenci.ogrenci_numarasi, ogrenci);
        }

        public void ogrenciListesiniGoruntule() {
            System.out.println("Mevcut Öğrenciler:");
            for (Ogrenci ogrenci : ogrenciler.values()) {
                System.out.println(ogrenci.ogrenci_numarasi + ": " + ogrenci.isim + " " + ogrenci.soyisim +
                        " - Sene: " + ogrenci.getSene());
            }
        }
    }

    static class DersOgrenciEslestirme {
        Map<Integer, Ogrenci> ogrenciler;
        DersTanimla dersTanimla;

        public DersOgrenciEslestirme(Map<Integer, Ogrenci> ogrenciler, DersTanimla dersTanimla) {
            this.ogrenciler = ogrenciler;
            this.dersTanimla = dersTanimla;
        }

        public void ogrencininDersiniAldigiMetod(int ogrenci_numarasi, int ders_kodu) {
            if (ogrenciler.containsKey(ogrenci_numarasi) && dersTanimla.dersler.containsKey(ders_kodu)) {
                Ogrenci ogrenci = ogrenciler.get(ogrenci_numarasi);
                Ders ders = dersTanimla.dersler.get(ders_kodu);

                dersTanimla.dersEkle(ders);
                ders.kayit_sayisi++;

                System.out.println(ogrenci.isim + " başarı ile " + ders.ders_ismi + "'e kaydolmuştur");
            } else {
                System.out.println("Geçersiz öğrenci ya da ders kodu");
            }
        }
    }

    static class DigerMainMetodislemleri {
        static DersTanimla dersTanimla;

        public static void istatistikleriGoruntule() {
            System.out.println("Ders kayıt durumu");
            for (Ders ders : dersTanimla.dersler.values()) {
                System.out.println(ders.ders_ismi + " - Kayıtlı öğrenci sayısı " + ders.kayit_sayisi);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DersTanimla dersTanimla = new DersTanimla();
        OgrenciTanimla ogrenciTanimla = new OgrenciTanimla();
        DersOgrenciEslestirme dersOgrenciEslestirme = new DersOgrenciEslestirme(ogrenciTanimla.ogrenciler, dersTanimla);

        while (true) {
            // Kullanıcı girişi
            System.out.print("Öğrenci numarası giriniz: ");
            int ogrenci_numarasi = scanner.nextInt();

            // Kurs listesini göster
            dersTanimla.dersListesiniGoruntule(ogrenciTanimla.ogrenciler.get(ogrenci_numarasi).getSene());

            // Kurs seçimi
            System.out.print("Kayıt olmak istediğiniz dersin kodunu giriniz (çıkış için 0 girin): ");
            int ders_kodu = scanner.nextInt();

            if (ders_kodu == 0) {
                break;
            }

            // Öğrenciyi kursa kaydet
            dersOgrenciEslestirme.ogrencininDersiniAldigiMetod(ogrenci_numarasi, ders_kodu);
        }

        // İstatistikleri göster
        DigerMainMetodislemleri.istatistikleriGoruntule();
    }
}
