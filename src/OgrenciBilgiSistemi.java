import java.util.*;
abstract class SecmeliDersler {
    List<Ders> dersler;

    public SecmeliDersler() {
        //seçmeli deersler
        this.dersler = new ArrayList<>();
        dersler.add(new Ders(101, "Türk Dili"));
        dersler.add(new Ders(102, "Matematik"));
        dersler.add(new Ders(103, "Fizik"));
        dersler.add(new Ders(104, "Atatürk İlkeleri ve İnkılap Tarihi"));
    }


    public abstract void dersEkle(Ders yeniDers);

}


class DerslerSinif1 extends SecmeliDersler {
    public DerslerSinif1() {
        super();
        //1. sınıf
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

class DerslerSinif2 extends SecmeliDersler {
    public DerslerSinif2() {
        super();
        //2. sınıf dersleri
        dersler.add(new Ders(301, "DerslerSinif2 Mühendisliğine Giriş"));
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

class Ogrenci {
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


public class OgrenciBilgiSistemi {

    static class OgrenciSene1 extends Ogrenci {
        String bolum;

        public OgrenciSene1(int ogrenci_numarasi, String isim, String soyisim, String bolum) {
            super(ogrenci_numarasi, isim, soyisim);
            this.bolum = bolum;
        }
    }

    static class OgrenciSene2 extends Ogrenci {
        String bolum;

        public OgrenciSene2(int ogrenci_numarasi, String isim, String soyisim, String bolum) {
            super(ogrenci_numarasi, isim, soyisim);
            this.bolum = bolum;
        }
    }

    static class OgrenciTanimla {
        Map<Integer, Ders> dersler;
        Map<Integer, Ogrenci> ogrenciler;

        public OgrenciTanimla() {
            this.dersler = new HashMap<>();
            this.ogrenciler = new HashMap<>();

            // Ogrencileri Tanımla
            ogrenciTanimlaSene1();
            ogrenciTanimlaSene2();

            // Dersleri tanımla
            DerslerSinif1 derslerSinif1 = new DerslerSinif1();
            DerslerSinif2 derslerSinif2 = new DerslerSinif2();

            for (Ders ders : derslerSinif1.dersler) {
                dersler.put(ders.ders_kodu, ders);
            }
            for (Ders ders : derslerSinif2.dersler) {
                dersler.put(ders.ders_kodu, ders);
            }

        }
        private void ogrenciEkle(Ogrenci ogrenci) {
            ogrenciler.put(ogrenci.ogrenci_numarasi, ogrenci);
        }

        private void ogrenciTanimlaSene1() {
            ogrenciEkle(new OgrenciSene1(1, "Eren", "Özcan", "Bilgisayar Mühendisliği"));
            ogrenciEkle(new OgrenciSene1(2, "Onur", "Malay", "Makine Mühendisliği"));
            ogrenciEkle(new OgrenciSene1(3, "Tuğba", "Çelikten", "Kimya Mühendisliği"));
        }

        private void ogrenciTanimlaSene2() {
            ogrenciEkle(new OgrenciSene2(4, "Yiğit", "Geldi", "Elektrik Mühendisliği"));
            ogrenciEkle(new OgrenciSene2(5, "Osman", "Güzel", "Endüstri Mühendisliği"));
        }



        public void DersListesiniGoruntule(int bolum) {
            System.out.println("Mevcut Kurslar:");

            List<Ders> gosterilecekDersler;
            if (bolum == 1) { // Yazılım Bölümü
                gosterilecekDersler = new DerslerSinif1().dersler;
            } else if (bolum == 2) { // DerslerSinif2 Bölümü
                gosterilecekDersler = new DerslerSinif2().dersler;
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

        OgrenciTanimla dersEklemeCikarma = new OgrenciTanimla();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Kullanıcı girişi
            // bu kısımda ogrenci numarası ile dersleri eşleştirmek için ogrencisene1 deki ogrencileri yazılım dersleri ile
            // ogrencisene2 dekileri mekatronikle eşleştiriyorum deneme amaçlı
            System.out.print("Öğrenci numarasını giriniz: ");
            int ogrenci_numarasi = scanner.nextInt();
            if (ogrenci_numarasi <= 3){
                int bolum = 1;
                dersEklemeCikarma.DersListesiniGoruntule(bolum);
            }
            //şu 2 metod ders listesini gösteriyor
            else {
                int bolum = 2;
                dersEklemeCikarma.DersListesiniGoruntule(bolum);
            }


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