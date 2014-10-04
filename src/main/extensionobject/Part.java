package extensionobject;

import java.util.Hashtable;

public abstract class Part {

	Hashtable<String, PartExtension> extensions = new Hashtable<String, PartExtension>();

	public abstract String getPartNumber();

	public abstract String getDescription();

	public void addExtension(String extensionType, PartExtension extension) {
		extensions.put(extensionType, extension);
	}

	public PartExtension getExtension(String string) {

		PartExtension partExtension = extensions.get(string);
		if (partExtension == null) {
			partExtension = new BadPartExtension();
		}
		return partExtension;
	}
}
