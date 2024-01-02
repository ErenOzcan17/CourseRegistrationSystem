abstract class Ogrenci {
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

class OgrenciSene1 extends Ogrenci {
    public OgrenciSene1(int ogrenci_numarasi, String isim, String soyisim, String bolum) {
        super(ogrenci_numarasi, isim, soyisim, bolum);
    }

    @Override
    int getSene() {
        return 1;
    }
}
class OgrenciSene2 extends Ogrenci {
    public OgrenciSene2(int ogrenci_numarasi, String isim, String soyisim, String bolum) {
        super(ogrenci_numarasi, isim, soyisim, bolum);
    }

    @Override
    int getSene() {
        return 2;
    }
}