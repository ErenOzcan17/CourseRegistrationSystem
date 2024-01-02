abstract class Ders {
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

class DersSene1 extends Ders {
    public DersSene1(int ders_kodu, String ders_ismi) {
        super(ders_kodu, ders_ismi);
    }

    @Override
    int getSene() {
        return 1;
    }
}

class DersSene2 extends Ders {
    public DersSene2(int ders_kodu, String ders_ismi) {
        super(ders_kodu, ders_ismi);
    }

    @Override
    int getSene() {
        return 2;
    }
}