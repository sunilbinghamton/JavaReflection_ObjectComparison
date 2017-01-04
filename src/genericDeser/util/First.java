package genericDeser.util;

public class First {

	private int IntValue;
	private String StringValue;
	private float FloatValue;
	private short ShortValue;	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(FloatValue);
		result = prime * result + IntValue;
		result = prime * result + ShortValue;
		result = prime * result + ((StringValue == null) ? 0 : StringValue.hashCode());
		return result;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		First other = (First) obj;
		if (Float.floatToIntBits(FloatValue) != Float.floatToIntBits(other.FloatValue))
			return false;
		if (IntValue != other.IntValue)
			return false;
		if (ShortValue != other.ShortValue)
			return false;
		if (StringValue == null) {
			if (other.StringValue != null)
				return false;
		} else if (!StringValue.equals(other.StringValue))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "First [IntValue=" + IntValue + ", StringValue=" + StringValue + ", FloatValue=" + FloatValue
				+ ", ShortValue=" + ShortValue + "]";
	}
	
	public int getIntValue() {
		return IntValue;
	}
	public void setIntValue(int intValue) {
		IntValue = intValue;
	}
	public String getStringValue() {
		return StringValue;
	}
	public void setStringValue(String stringValue) {
		StringValue = stringValue;
	}
	public float getFloatvalue() {
		return FloatValue;
	}
	public void setFloatValue(float floatvalue) {
		FloatValue = floatvalue;
	}
	public short getShortValue() {
		return ShortValue;
	}
	public void setShortValue(short shortValue) {
		ShortValue = shortValue;
	}
	
	
}
