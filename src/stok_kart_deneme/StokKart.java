package stok_kart_deneme;

public class StokKart {
	private String stokKodu;
	private String stokAdi;
	private String stokTipi;
	private String birimi;
	private String barkodu;
	private double kdvTipi;
	private String aciklama;
	private String olusturmaTarihi;
	
	public StokKart(String stokKodu, String stokAdi,String stokTipi,String birimi,String barkodu,double kdvTipi,String aciklama,String olusturmaTarihi) {
		this.setStokKodu(stokKodu);
		this.setStokAdi(stokAdi);
		this.setStokTipi(stokTipi);
		this.setBirimi(birimi);
		this.setBarkodu(barkodu);
		this.setKdvTipi(kdvTipi);
		this.setAciklama(aciklama);
		this.setOlusturmaTarihi(olusturmaTarihi);
	}
	public String getStokKodu() {
		return stokKodu;
	}

	public void setStokKodu(String stokKodu) {
		this.stokKodu = stokKodu;
	}

	public String getStokAdi() {
		return stokAdi;
	}

	public void setStokAdi(String stokAdi) {
		this.stokAdi = stokAdi;
	}

	public String getStokTipi() {
		return stokTipi;
	}

	public void setStokTipi(String stokTipi) {
		this.stokTipi = stokTipi;
	}

	public String getBirimi() {
		return birimi;
	}

	public void setBirimi(String birimi) {
		this.birimi = birimi;
	}

	public String getBarkodu() {
		return barkodu;
	}

	public void setBarkodu(String barkodu) {
		this.barkodu = barkodu;
	}

	public double getKdvTipi() {
		return kdvTipi;
	}

	public void setKdvTipi(double kdvTipi) {
		this.kdvTipi = kdvTipi;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public String getOlusturmaTarihi() {
		return olusturmaTarihi;
	}

	public void setOlusturmaTarihi(String olusturmaTarihi) {
		this.olusturmaTarihi = olusturmaTarihi;
	}


}
