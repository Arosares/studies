package de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum Severity {

	@XmlEnumValue("trivial")
	TRIVIAL,

	@XmlEnumValue("minor")
	MINOR,

	@XmlEnumValue("major")
	MAJOR,

	@XmlEnumValue("critical")
	CRITICAL;

}
