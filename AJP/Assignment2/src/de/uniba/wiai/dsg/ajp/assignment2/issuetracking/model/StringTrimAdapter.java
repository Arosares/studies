package de.uniba.wiai.dsg.ajp.assignment2.issuetracking.model;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class StringTrimAdapter extends XmlAdapter<String, String> {


	@Override
	public String marshal(String v) throws Exception {
		if (v.contains(" ")){
			v = v.replace(" ", "");
		}
		return v;
	}
	@Override
	public String unmarshal(String v) throws Exception {
		if (v.contains(" ")){
			v = v.replace(" ", "");
		}
		return v;
	}
	public String validate(String v) throws Exception {
		if (v.contains(" ")){
			v = v.replace(" ", "");
		}
		return v;
	}
}