abstract class Ogrenci {
    int ogrenci_numarasi;
    String isim;
    String soyisim;
    String Sifre;

    public Ogrenci(int ogrenci_numarasi, String isim, String soyisim, String Sifre) {
        this.ogrenci_numarasi = ogrenci_numarasi;
        this.isim = isim;
        this.soyisim = soyisim;
        this.Sifre = Sifre;
    }

    abstract int getSene();
}

class OgrenciSene1 extends Ogrenci {
    public OgrenciSene1(int ogrenci_numarasi, String isim, String soyisim, String Sifre) {
        super(ogrenci_numarasi, isim, soyisim, Sifre);
    }

    @Override
    int getSene() {
        return 1;
    }
}
class OgrenciSene2 extends Ogrenci {
    public OgrenciSene2(int ogrenci_numarasi, String isim, String soyisim, String Sifre) {
        super(ogrenci_numarasi, isim, soyisim, Sifre);
    }

    @Override
    int getSene() {
        return 2;
    }
}
class SifreUzunlukException extends Exception {
    public SifreUzunlukException(String message) {
        super(message);
    }
}