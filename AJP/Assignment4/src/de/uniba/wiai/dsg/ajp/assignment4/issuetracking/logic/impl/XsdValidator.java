package de.uniba.wiai.dsg.ajp.assignment4.issuetracking.logic.impl;

import java.io.IOException;
import java.nio.file.Paths;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class XsdValidator {

	private Validator validator;

	public XsdValidator(String pathToXsdFile) throws SAXException {
		SchemaFactory sf = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = sf.newSchema(Paths.get(pathToXsdFile).toFile());
		validator = schema.newValidator();
	}

	public void validate(String path) throws SAXException, IOException {
		validator.validate(new StreamSource(Paths.get(path).toFile()));
	}
}