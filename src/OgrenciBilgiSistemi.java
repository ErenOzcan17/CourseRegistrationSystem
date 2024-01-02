import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class OgrenciBilgiSistemi {
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
        public void dersListesiniGoruntule() {

        }
    }

    static class OgrenciTanimla {
        Map<Integer, Ogrenci> ogrenciler;

        public OgrenciTanimla() {
            this.ogrenciler = new HashMap<>();
            // Öğrencileri başlangıçta tanımla
            ogrenciEkle(new OgrenciSene1(1, "Eren", "Özcan", "Bilgisayar"));
            ogrenciEkle(new OgrenciSene1(3, "Onur", "Malay", "Makine Mühendisliği"));
            ogrenciEkle(new OgrenciSene1(5, "Tuğba", "Çelikten", "Kimya Mühendisliği"));

            ogrenciEkle(new OgrenciSene2(2, "Yiğit", "Geldi", "Elektrik Mühendisliği"));
            ogrenciEkle(new OgrenciSene2(4, "Osman", "Güzel", "Endüstri Mühendisliği"));
        }

        //bu metod ogreniyi hashmapin oğrenciler kısmına kaydediyor
        public void ogrenciEkle(Ogrenci ogrenci) {
            ogrenciler.put(ogrenci.ogrenci_numarasi, ogrenci);
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
                System.out.println("Geçersiz ders kodu");
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
            try {
                System.out.print("Öğrenci numarası giriniz: ");
                int ogrenci_numarasi = scanner.nextInt();
                // bu komut boş satırı okur. eğer boş satır okunmazsa boşluk kalır ve şifre ile birlikte okunup yanlış değer girmeye sebep olur
                scanner.nextLine();
                // Şifre kontrolü
                if (ogrenciTanimla.ogrenciler.containsKey(ogrenci_numarasi)) {
                    Ogrenci ogrenci = ogrenciTanimla.ogrenciler.get(ogrenci_numarasi);
                    System.out.println("Şifrenizi giriniz: ");
                    String girilenSifre = scanner.nextLine();
                    if (girilenSifre.equals(ogrenci.Sifre)) {
                        System.out.println("Giriş başarılı");

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
                    } else {
                        System.out.println("Yanlış şifre");
                    }
                } else {
                    System.out.println("Geçersiz öğrenci numarası");
                }
            } catch (InputMismatchException e) {
                System.out.println("Geçersiz giriş. Lütfen bir tam sayı girin.");
                scanner.nextLine();
            }
        }

        // İstatistikleri göster
        DigerMainMetodislemleri.istatistikleriGoruntule();
    }
}
