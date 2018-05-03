package Simulacion;

public class GPIO {
	private Integer pin;
	private Boolean on;
	
	public GPIO() {
		this.setOn(false);
	}
	
	public GPIO(Integer pin, Boolean on) {
		this.setPin(pin);
		this.setOn(on);
	}
	
	public GPIO(Integer pin) {
		this.setOn(false);
		this.setPin(pin);
	}

	public Integer getPin() {
		return pin;
	}

	public void setPin(Integer pin) {
		this.pin = pin;
	}

	public Boolean getOn() {
		return on;
	}

	public void setOn(Boolean on) {
		this.on = on;
	}
}
