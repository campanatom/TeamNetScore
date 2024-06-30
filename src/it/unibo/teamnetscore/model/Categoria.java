package it.unibo.teamnetscore.model;

public enum Categoria {
    CALCIO_A_CINQUE("Calcio a cinque"),
    CALCIO_A_SETTE("Calcio a sette"),
    CALCIO_A_UNDICI("Calcio a undici");

	private String value;
	
	Categoria(String string) {
		this.value = string;
	}
	
	public String getValue()
	{
		return this.value;
	};
}
