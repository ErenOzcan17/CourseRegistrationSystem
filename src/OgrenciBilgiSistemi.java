import java.util.*;
abstract class OrtakDersler {
    List<Ders> dersler;

    public OrtakDersler() {
        this.dersler = new ArrayList<>();
        dersler.add(new Ders(101, "Türk Dili"));
        dersler.add(new Ders(102, "Matematik"));
        dersler.add(new Ders(103, "Fizik"));
        dersler.add(new Ders(104, "Atatürk İlkeleri ve İnkılap Tarihi"));
    }


    public abstract void dersEkle(Ders yeniDers);

}


class Yazilim extends OrtakDersler {
    public Yazilim() {
        super();
        dersler.add(new Ders(201, "Algoritma"));
        dersler.add(new Ders(202, "Nesne"));
        dersler.add(new Ders(203, "Gereksinim"));
        dersler.add(new Ders(204, "WEB"));
    }

    @Override
    public void dersEkle(Ders yeniDers) {
        dersler.add(yeniDers);
    }
}

class Mekatronik extends OrtakDersler {
    public Mekatronik() {
        super();
        dersler.add(new Ders(301, "Mekatronik Mühendisliğine Giriş"));
        dersler.add(new Ders(302, "Elektrik Devreleri"));
        dersler.add(new Ders(303, "Bilgisayar Destekli Teknik Resim"));
    }

    @Override
    public void dersEkle(Ders yeniDers) {
        dersler.add(yeniDers);
    }
}
class Ders {
    int ders_kodu;
    String ders_ismi;
    int kayit_sayisi;

    public Ders(int ders_kodu, String ders_ismi) {
        this.ders_kodu = ders_kodu;
        this.ders_ismi = ders_ismi;
        this.kayit_sayisi = 0;
    }
}


public class OgrenciBilgiSistemi {
    static class Ogrenci {
        int ogrenci_numarasi;
        String isim;
        String soyisim;
        int bolum;
        List<String> alinan_dersler;

        public Ogrenci(int ogrenci_numarasi, String isim, String soyisim) {
            this.ogrenci_numarasi = ogrenci_numarasi;
            this.isim = isim;
            this.soyisim = soyisim;
            this.bolum = bolum;
            this.alinan_dersler = new ArrayList<>();
        }
    }

    static class DersEklemeCikarma {
        Map<Integer, Ders> dersler;
        Map<Integer, Ogrenci> ogrenciler;

        public DersEklemeCikarma() {
            this.dersler = new HashMap<>();
            this.ogrenciler = new HashMap<>();

            // Dersleri tanımla
            Yazilim yazilimDersleri = new Yazilim();
            Mekatronik mekatronikDersleri = new Mekatronik();

            for (Ders ders : yazilimDersleri.dersler) {
                dersler.put(ders.ders_kodu, ders);
            }
            for (Ders ders : mekatronikDersleri.dersler) {
                dersler.put(ders.ders_kodu, ders);
            }

            // Öğrencileri tanımla
            ogrenci_ekle(1, "Eren", "Özcan", 1);
            ogrenci_ekle(2, "Yiğit", "Geldi", 1);
            // Diğer öğrencileri ekle...
        }

        public void ogrenci_ekle(int ogrenci_numarasi, String isim, String soyisim,  int bolum) {
            Ogrenci ogrenci = new Ogrenci(ogrenci_numarasi, isim, soyisim);
            ogrenciler.put(ogrenci_numarasi, ogrenci);
        }

        public void DersListesiniGoruntule(int bolum) {
            System.out.println("Mevcut Kurslar:");

            List<Ders> gosterilecekDersler;
            if (bolum == 1) { // Yazılım Bölümü
                gosterilecekDersler = new Yazilim().dersler;
            } else if (bolum == 2) { // Mekatronik Bölümü
                gosterilecekDersler = new Mekatronik().dersler;
            } else {
                System.out.println("Geçersiz bölüm seçimi!");
                return;
            }

            for (Ders ders : gosterilecekDersler) {
                System.out.println(ders.ders_kodu + ": " + ders.ders_ismi +
                        " - Kayıtlı öğrenciler: " + ders.kayit_sayisi);
            }
        }


        public void OgrenciKaydetme(int ogrenci_numarasi, int ders_kodu) {
            if (ogrenciler.containsKey(ogrenci_numarasi) && dersler.containsKey(ders_kodu)) {
                Ogrenci ogrenci = ogrenciler.get(ogrenci_numarasi);
                Ders ders = dersler.get(ders_kodu);

                if (!ogrenci.alinan_dersler.contains(ders.ders_ismi)) {
                    ogrenci.alinan_dersler.add(ders.ders_ismi);
                    ders.kayit_sayisi++;
                    System.out.println(ogrenci.isim + " başarı ile " + ders.ders_ismi + "'e kaydolmuştur");
                } else {
                    System.out.println(ogrenci.isim + " zaten " + ders.ders_ismi + " dersine kayıtlı");
                }
            } else {
                System.out.println("Geçersiz öğrenci ya da ders kodu");
            }
        }

        public void IstasitikleriGoruntule() {
            System.out.println("Ders kayıt istatistikleri:");
            for (Ders ders : dersler.values()) {
                System.out.println(ders.ders_ismi + " - Kayıtlı öğrenci sayısı: " + ders.kayit_sayisi);
            }
        }
    }

    public static void main(String[] args) {

        DersEklemeCikarma dersEklemeCikarma = new DersEklemeCikarma();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Kullanıcı girişi
            System.out.print("Öğrenci numarasını giriniz: ");
            int ogrenci_numarasi = scanner.nextInt();

            System.out.print("Hangi bölümde okuyorsunuz? (1 - Yazılım Mühendisliği, 2 - Mekatronik): ");
            int bolum = scanner.nextInt();

            // Kurs listesini göster
            dersEklemeCikarma.DersListesiniGoruntule(bolum);

            // Kurs seçimi
            System.out.print("Kayıt olmak istediğiniz dersin kodunu giriniz (çıkış için 0 girin): ");
            int ders_kodu = scanner.nextInt();

            if (ders_kodu == 0) {
                dersEklemeCikarma.IstasitikleriGoruntule();
                break;
            }

            // Öğrenciyi kursa kaydet
            dersEklemeCikarma.OgrenciKaydetme(ogrenci_numarasi, ders_kodu);
        }

        // İstatistikleri göster
        dersEklemeCikarma.IstasitikleriGoruntule();
    }
}